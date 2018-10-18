package com.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Exam;
import com.entity.Question;
import com.entity.QuestionType;
import com.entity.Regulation;
import com.entity.Teacher;
import com.service.ExamService;
import com.service.QuestionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("exam")
public class ExamController {

	@Resource
	private ExamService examService;

	@Resource
	private QuestionService questionService;
	
	@RequestMapping("toAddPointValue")
	public String toAddHandChoosePointValue(HttpServletRequest request){
		List<QuestionType> types = questionService.findAllQuestionType();
		request.getSession().setAttribute("types", types);
		Exam exam=(Exam)request.getSession().getAttribute("exam");
		exam=examService.selectExamById(exam.getId());
		request.setAttribute("exam", exam);
		return "exam/hand_addPointValue";
	}
	
	@RequestMapping("addPointValue")
	public String addHandChoosePointValue(HttpServletRequest request){
		
		List<Integer> intList=new ArrayList<Integer>();
		if(request.getParameter("score1")!=null&&request.getParameter("score1")!=""){
			Integer score1=new Integer(request.getParameter("score1"));
			intList.add(score1);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score2")!=null&&request.getParameter("score2")!=""){
			Integer score2=new Integer(request.getParameter("score2"));
			intList.add(score2);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score3")!=null&&request.getParameter("score3")!=""){
			Integer score3=new Integer(request.getParameter("score3"));
			intList.add(score3);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score4")!=null&&request.getParameter("score4")!=""){
			Integer score4=new Integer(request.getParameter("score4"));
			intList.add(score4);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score5")!=null&&request.getParameter("score5")!=""){
			Integer score5=new Integer(request.getParameter("score5"));
			intList.add(score5);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score6")!=null&&request.getParameter("score6")!=""){
			Integer score6=new Integer(request.getParameter("score6"));
			intList.add(score6);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score7")!=null&&request.getParameter("score7")!=""){
			Integer score7=new Integer(request.getParameter("score7"));
			intList.add(score7);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score8")!=null&&request.getParameter("score8")!=""){
			Integer score8=new Integer(request.getParameter("score8"));
			intList.add(score8);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score9")!=null&&request.getParameter("score9")!=""){
			Integer score9=new Integer(request.getParameter("score9"));
			intList.add(score9);
		}else{
			intList.add(null);
		}
		if(request.getParameter("score10")!=null&&request.getParameter("score10")!=""){
			Integer score10=new Integer(request.getParameter("score10"));
			intList.add(score10);
		}else{
			intList.add(null);
		}
		
		Exam exam=(Exam)request.getSession().getAttribute("exam");
		exam=examService.selectExamById(exam.getId());
		examService.addPointToExam(exam,intList);
		
		return "redirect:/exam/listExam";
	}

	@RequestMapping("newExam")
	public String newExam(HttpServletRequest request,
			@RequestParam(name = "course_id", defaultValue = "0") String course_id) {
		Teacher t = (Teacher) request.getSession().getAttribute("teacher");

		Exam exam = examService.hand_NewExam(t);
		request.getSession().setAttribute("exam", exam);
		request.setAttribute("exam", exam);

		return "redirect:/question/allQuestion?course_id=" + course_id;
	}

	@RequestMapping("removeQuestion")
	@ResponseBody
	public void removeQuestionToExam(HttpServletRequest request) {
		Integer question_id = new Integer(request.getParameter("question_id").toString());
		Exam exam = (Exam) request.getSession().getAttribute("exam");
		examService.removeQuestion(exam, question_id);
	}

	@RequestMapping("addQuestion")
	@ResponseBody
	public void addQuestionToExam(HttpServletRequest request, HttpSession session) {
		Integer question_id = new Integer(request.getParameter("question_id").toString());
		Exam exam = (Exam) session.getAttribute("exam");
		examService.addQuestion(exam, question_id);
	}

	@RequestMapping("changeTitle")
	public void saveTitle(HttpServletRequest request) {
		String title = request.getParameter("title");
		String examId = request.getParameter("examId");
		examService.saveTitle(new Integer(examId), title);
	}

	@RequestMapping("changeQuestion")
	public void changeExam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String question_id_s = request.getParameter("questionid");
		String e_id_s = request.getParameter("e_id");
		Question q = examService.changeQuestion(new Integer(e_id_s), new Integer(question_id_s));

		JSONArray jsons = new JSONArray();
		if (q != null) {

			JSONObject jsonObject_id = new JSONObject();
			jsonObject_id.put("id", q.getId());
			jsons.add(jsonObject_id);

			JSONObject jsonObject_content = new JSONObject();
			jsonObject_content.put("content", q.getContent());
			jsons.add(jsonObject_content);

			JSONObject jsonObject_a = new JSONObject();
			jsonObject_a.put("a", q.getA());
			jsons.add(jsonObject_a);

			JSONObject jsonObject_b = new JSONObject();
			jsonObject_b.put("b", q.getB());
			jsons.add(jsonObject_b);

			JSONObject jsonObject_c = new JSONObject();
			jsonObject_c.put("c", q.getC());
			jsons.add(jsonObject_c);

			JSONObject jsonObject_d = new JSONObject();
			jsonObject_d.put("d", q.getD());
			jsons.add(jsonObject_d);

			JSONObject jsonObject_answer = new JSONObject();
			jsonObject_answer.put("solution", q.getAnswer().getSolution());
			jsons.add(jsonObject_answer);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsons.toString());

	}

	@RequestMapping("moveup")
	public void moveup(HttpServletRequest request) {
		String exam_need = request.getParameter("exam_need");
		String e_id_s = request.getParameter("e_id");
		String sequence_s = exam_need.substring(0, exam_need.indexOf(","));
		String q_id_s = exam_need.substring(exam_need.indexOf(",") + 1);
		examService.questionMoveUp(new Integer(sequence_s), new Integer(q_id_s), new Integer(e_id_s));
	}

	@RequestMapping("movedn")
	public void movedn(HttpServletRequest request) {
		String exam_need = request.getParameter("exam_need");
		String e_id_s = request.getParameter("e_id");
		String sequence_s = exam_need.substring(0, exam_need.indexOf(","));
		String q_id_s = exam_need.substring(exam_need.indexOf(",") + 1);
		examService.questionMoveDn(new Integer(sequence_s), new Integer(q_id_s), new Integer(e_id_s));
	}

	@RequestMapping("listExam")
	public String listExam(HttpServletRequest request, HttpSession session) {
		Teacher t = (Teacher) session.getAttribute("teacher");
		List<Exam> examlist = examService.findExamsByTeacher(t.getId());
		request.setAttribute("exams", examlist);
		return "exam/listExam";
	}

	@RequestMapping("previewExam")
	public String previewExam(@RequestParam("e_id") Integer e_id, HttpServletRequest request,HttpSession session) {
		Exam e = examService.selectExamById(e_id);
		List<HashMap<QuestionType, Integer>> types = questionService.selectQuestionTypeByExam(e);
		session.setAttribute("types", types);
		session.setAttribute("exam", e);
		return "exam/preview";
	}

	@RequestMapping("deleteExamById")
	public String deleteExam(@RequestParam("e_id") Integer id, HttpServletRequest request, HttpSession session) {
		System.out.println(id);
		examService.deleteExam(id);
		Teacher t = (Teacher) session.getAttribute("teacher");
		List<Exam> examlist = examService.findExamsByTeacher(t.getId());
		request.setAttribute("exams", examlist);
		return "exam/listExam";
	}

	@RequestMapping(value = "createExam", method = RequestMethod.POST)
	public String createExam(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		
		Teacher teacher=(Teacher)session.getAttribute("teacher");
		

		
		/*
		 * 获得各题型所占的分值
		 */
		LinkedHashMap map1 = new LinkedHashMap();
		String checkboxs = request.getParameter("splits");
		String checkbox[] = checkboxs.split(",");
		/*
		 * 集合每个题型的分和每个题型的个数
		 */
		List list1=new ArrayList();
		List list2=new ArrayList();
		for(int i=1;i<11;i++){
			String temppointValues = request.getParameter("pointValue" +i);
			String tempnums = request.getParameter("num" + i);
			if(temppointValues.equals(null) || temppointValues==""){
				list1.add(0);
			}else{
				Integer temp = Integer.parseInt(temppointValues);
				list1.add(temp);
			}
			list2.add(tempnums);
		}
		
		
		int questionNum[]=new int[11];
		for (int i = 0; i < checkbox.length; i++) {
			Integer nums = 0;
			Integer pointValues = 0;
			String tempnums = request.getParameter("num" + checkbox[i]);
			String temppointValues = request.getParameter("pointValue" + checkbox[i]);
			if (null != tempnums && !"".equals(tempnums)) {
				questionNum[Integer.parseInt(checkbox[i])] =Integer.parseInt(tempnums);
				nums = Integer.parseInt(tempnums);
			}
			if (null != temppointValues && !"".equals(temppointValues)) {
				pointValues = Integer.parseInt(temppointValues);
			}
			int totalValue = nums * pointValues;
			if (null != checkbox[i] && !"".equals(checkbox[i])) {
			map1.put((Integer.parseInt(checkbox[i])), totalValue);
			}
		}
		for(int q=0;q<questionNum.length;q++){
			System.out.println(questionNum[q]);
		}
		/*
		 * 各难易程度所占的分值
		 */
		/*
		 * 获得期望值
		 */
		Double averageScore = Double.parseDouble(request.getParameter("averageScore"));
		LinkedHashMap map2 = new LinkedHashMap();
		NormalDistribution normalDistribution = new NormalDistribution(averageScore, 10);
		double d[] = new double[5];
		double sum = 0;
		for (int i = 1; i < 5; i++) {
			d[i] = normalDistribution.cumulativeProbability(100 - 25 * i, 125 - 25 * i);
			sum += d[i];
		}
		double sum2 = 0;
		for (int j = 1; j < 5; j++) {
			d[j] = d[j] + (1 - sum) * (d[j] / sum);
			sum2 += d[j];
		}
		/*
		 * 各章节所获得的分值 这章节中个难易程度所占的分
		 */
		String chapters = request.getParameter("chapters");
		String chapter[] = chapters.split(",");
		Integer tchapter[] = new Integer[chapter.length + 1];
		int[][] a = new int[chapter.length + 1][5];
		List listNum=new ArrayList();
		for (int i = 0; i < chapter.length; i++) {
			tchapter[i + 1] = Integer.parseInt(chapter[i]);
			if(tchapter[i+1]!=0){
				listNum.add(i+1);
			}
			
//			System.out.println("不为0的章节"+listNum);
			for (int j = 1; j < d.length; j++) {
				DecimalFormat df = new DecimalFormat("#");
				String tchapters = df.format(tchapter[i + 1] * d[j]);
				if (null != tchapters && !"".equals(tchapters)) {
					a[i + 1][j] = Integer.parseInt(tchapters);
				}
			}
		}
	
		/*
		 * 2.生成试卷
		 */
		int id = 0;
		int ids = 0;
		Iterator iter = map1.keySet().iterator();
		List<Integer> list = new ArrayList<Integer>();
		o:while (iter.hasNext()) {
			int key =  (int) iter.next();
			int val = (int)map1.get(key);
			t:while(val!=0){
			x:for (int i = 1; i < a.length; i++) {
				for (int j = 1; j < a[i].length; j++) {
					//减分值得操作
					String temppointValues = request.getParameter("pointValue" + key);
					if (null != temppointValues && !"".equals(temppointValues)) {
						if(Integer.parseInt(temppointValues)<=a[i][j]){
							id = 0;
							id = this.examService.exam(key, i, j,list);
							if(id>0){
								val-=Integer.parseInt(temppointValues);
								map1.put(key, val);
								a[i][j]-=Integer.parseInt(temppointValues);
								questionNum[key]--;
								System.out.println(questionNum[key]);
								list.add(id);
							}else{
								String panduan = "错误";
								request.setAttribute("panduan",panduan);
								return "forward:/exam/choose.jsp";
							}
						}
					}
					//
					if(val==0){
						break x;
					}
					int vals=0;
					for(int m=1;m<a.length;m++){
						for(int n=1;n<a[m].length;n++){
							if(a[m][n]<Integer.parseInt(temppointValues))
								vals++;
						}
					}
					if(vals==(a.length-1)*4){
						break t;
					}
				}//1for
			}
			
			}
		}
		for(int m=1;m<questionNum.length;m++){
			while(questionNum[m]!=0){
				id=0;
				Random rand = new Random();
				id = this.examService.nextExam(m,listNum,list);
				if(id>0){
					questionNum[m]--;
					list.add(id);
				}else{
					String panduan = "错误";
					request.setAttribute("panduan",panduan);
					return "forward:/exam/choose.jsp";
				}
					
			}
		}
		examService.generatePaper(teacher,list,list1,averageScore);
		return "redirect:/exam/listExam";
	}
	@RequestMapping(value = "regulation")
	public String regulation(HttpServletRequest request,HttpSession session){
		int repeat=this.examService.getRegulation();
		request.setAttribute("regulation",repeat);
		return "forward:/exam/regulationlist.jsp";
	}
	@RequestMapping(value = "editregulation",method = RequestMethod.GET)
	public String editregulation(@RequestParam("regulation") int regulation,HttpServletRequest request){
		request.setAttribute("regulation", regulation);
		return "forward:/exam/regulationform.jsp";
	}
	@RequestMapping(value = "editregulation",method=RequestMethod.POST)
	public String editregulation(@RequestParam("repeat") Integer repeat){
		this.examService.editregulation(repeat);
		return "redirect:regulation";
	}
}
