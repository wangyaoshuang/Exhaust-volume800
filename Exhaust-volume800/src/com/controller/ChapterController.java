package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Chapter;
import com.entity.Course;
import com.model.Page;
import com.service.ChapterService;
import com.service.CourseService;

@Controller
@RequestMapping("chapter")
public class ChapterController {
	
	private int pageSize=5;
	
	@Resource
	private ChapterService chapterService;
	
	@Resource
	private CourseService courseService;
	
	@RequestMapping("toAdd")
	public String toAdd(Chapter ch,HttpServletRequest request){
		request.setAttribute("chapter", ch);
		request.setAttribute("action", "add");
		List<Course> courses=courseService.findAllCourses();
		request.setAttribute("courselist", courses);
		return "chapter/form";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Chapter ch,HttpServletRequest request){
		Integer c_id=new Integer(request.getParameter("c_id"));
		this.chapterService.addChapter(ch,c_id);
		request.setAttribute("action", "select");
		return "forward:/chapter/list";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String toEdit(@RequestParam("id") int chapterId,
			HttpServletRequest request){
		Chapter ch=this.chapterService.getChapterId(chapterId);
		List<Course> courses = courseService.findAllCourses();
		request.setAttribute("chapter", ch);
		request.setAttribute("action", "edit");
		request.setAttribute("courselist", courses);
		
		return "chapter/form";
	}
	
	@RequestMapping(value="toEdit")
	public String editList(HttpServletRequest request){
		List<Chapter> lists=new ArrayList<Chapter>();
		request.setAttribute("action", "edit");
		return "forward:/chapter/list";
	}
	
	@RequestMapping(value="toDelete")
	public String toDelete(HttpServletRequest request,@RequestParam(name="searchParam",defaultValue="")String searchParam){
		request.setAttribute("searchParam", searchParam);
		request.setAttribute("action", "delete");		
		return "forward:/chapter/list";
	}
	
	
	@RequestMapping(value="edit")
	public String edit(HttpServletRequest request,@RequestParam(name="searchParam",defaultValue="")String searchParam){
		Integer id=new Integer(request.getParameter("id"));
		String name=request.getParameter("name");
		Integer chapterOrder =new Integer(request.getParameter("chapterOrder"));
		Integer c_id=new Integer(request.getParameter("c_id"));
		Chapter ch=new Chapter();
		ch.setId(id);
		ch.setName(name);
		ch.setChapterOrder(chapterOrder);
		ch.setCourse(courseService.getCourseId(c_id));
		this.chapterService.editChapter(ch);
		
		request.setAttribute("searchParam", searchParam);
		
		request.setAttribute("action", "edit");
		
		return "forward:/chapter/list";
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam("id") int chapterId,
			HttpServletRequest request,@RequestParam(name="searchParam",defaultValue="")String searchParam){
		if(!this.chapterService.deleteChapter(chapterId)){
			request.getSession().setAttribute("prompt", "false");
		}
		request.getSession().setAttribute("searchParam", searchParam);
		
		request.setAttribute("action", "delete");
		
		return "forward:/chapter/list";
	}
	
	@RequestMapping(value="list")
	public String list(@RequestParam(name="pageNo", defaultValue="1") int pageNo,HttpServletRequest request){
		
		String searchParam=request.getParameter("searchparam");
		if(searchParam==null){
			try{
				searchParam=request.getAttribute("searchparam").toString();
			}catch(Exception e){
				searchParam="";
			}
		}
		String action=null;
		try{
			action=request.getAttribute("action").toString();
		}catch(Exception e){
			action="select";
		}
		
		Page page=null;
		if(searchParam==""){
			page=chapterService.listChapter(pageNo-1, pageSize);
		}else {
			page=chapterService.listChapter(pageNo-1, pageSize, searchParam);
		}
		request.setAttribute("page", page);
		request.setAttribute("searchParam", searchParam);
		request.setAttribute("action", action);
		return "chapter/list";
		
	}
//	
	@RequestMapping(value="allDelete")
	public String allDelete(@RequestParam(name="idlist", defaultValue="") String str,HttpServletRequest request){
		String[] strs = str.split(",");
		for(int i=0;i<strs.length;i++){
			this.chapterService.deleteChapter(new Integer(strs[i]));
		}
		return "forward:/chapter/list";
	}
//	@RequestMapping(value="list")
//	public String list(@RequestParam(name="pageNo", defaultValue="1") int pageNo,
//						@RequestParam(name="searchparam",defaultValue="") String searchparam,
//						HttpServletRequest request){
//		String action=null;
//		String prompt=null;
//		try{
//			action=request.getAttribute("action").toString();
//		}catch(Exception e){
//			action="select";
//		}
//		try{
//			prompt=request.getAttribute("prompt").toString();
//		}catch(Exception e){
//			prompt="true";
//		}
//		System.out.println("searchparam:"+searchparam);
//		Page page=chapterService.queryForPage(pageNo-1, PageSize,searchparam);
//		
//		request.setAttribute("page", page);
//		request.setAttribute("action", action);
//		request.setAttribute("prompt", prompt);
//		request.setAttribute("skipNum",pageNo);
//		
//		return "course/list";
//	}
	
	
}
