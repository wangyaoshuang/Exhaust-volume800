package com.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Course;
import com.entity.Role;
import com.entity.Teacher;
import com.model.Page;
import com.service.TeachermanageService;

@Controller
@RequestMapping("Teachermanage")
public class TeachermanageController {
 
	
  @Resource
  private TeachermanageService teachermanageService;
  private String filename = "";
  
  @RequestMapping("toAdd")
	public String toAdd( Teacher c,HttpServletRequest request){
	    List<Course> courses=new ArrayList<>();
	    courses=teachermanageService.findCourse();
		request.setAttribute("teac", c);
		request.setAttribute("action", "add");
		request.setAttribute("courses1", courses);
		return "teacher/form";
	}
  //添加老师的信息
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String addTeacher(HttpServletRequest request, HttpServletResponse response,
          HttpSession httpSession) {
	  
    //将老师的信息保存到数据库中
	System.out.println("add");
    String photpname = filename; 
    Teacher teacher = new Teacher();
    String id = request.getParameter("id");
    Integer t_id = new Integer(id);
    String name = request.getParameter("name");
    String password = request.getParameter("password");
  //获得密码之后也进行Md5加密，判断是否和数据库中加密之后存储的相同，如果相同，密码正确，否则错误
  		MessageDigest md;
  		String Md5Password = null;
  		try {
  			md = MessageDigest.getInstance("MD5");
  			// 计算md5函数
  			md.update(password.getBytes());
  			Md5Password = new BigInteger(1, md.digest()).toString(16);
  			//System.out.println("登录页面加密之后的密码是："+Md5Password);
  		} catch (NoSuchAlgorithmException e1) {
  			e1.printStackTrace();
  		}
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String hi_time = request.getParameter("hiredate");
    teacher.setId(t_id);
    teacher.setName(name);
    teacher.setPassword(Md5Password);
    teacher.setEmail(email);
    teacher.setAddress(address);
    teacher.setPhone(phone);
    teacher.setHiredate(hi_time);
    teacher.setPhoto(filename);
    //给老师添加权限
    String[] role=request.getParameterValues("roles");
    List <Role>roles=this.teachermanageService.findRole();
    for(Role r:roles){
    	for(int i=0;i<role.length;i++){
    		System.out.println(role[0]);
    	if(role[i].equals(r.getName())){
    		teacher.getRoles().add(r); 
    		
    		r.getTeachers().add(teacher);
    	 }
    	}
    }
    //给老师添加课程
    String[] courseId=request.getParameterValues("coursesId");
    try{
    	if(courseId.length!=0){	
    		 int[] ci=new int[1000];
    		  for(int i=0;i<courseId.length;i++){
    			  ci[i]= new Integer(courseId[i]);
    		  }
    		    List<Course> course=this.teachermanageService.getCourse(ci);
    		    for(Course c:course){
    		    	teacher.getCourses().add(c);
    		    	c.getTeachers().add(teacher);
    		    }   
    		    this.teachermanageService.addTeacher(teacher);
    	}else{
    		this.teachermanageService.addTeacher(teacher);
    		
    	}
    }catch(Exception e){
    	this.teachermanageService.addTeacher(teacher);
    	
    }  
    return "redirect:list";      
  }

  @RequestMapping(value="edit",method=RequestMethod.GET)
	public String toEdit(@RequestParam("id") int teacherId,
			HttpServletRequest request){
	    System.out.println("edit get"+teacherId);
	    List<Course> courses=new ArrayList<>();
	    courses=teachermanageService.findCourse();
		Teacher t=this.teachermanageService.getTeacher(teacherId);
		request.setAttribute("teac", t);
		request.setAttribute("action", "edit");
		request.setAttribute("courses1", courses);
		return "teacher/form";
	}
  
  /**
   * 修改老师信息
   * @param t
   * @param request
   * @return
   */
  @RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(Teacher t,HttpServletRequest request){
	  System.out.println("edit post");
	  if(t.getName()==null){
	    	return "redirect:list";
	    }
	  String xuanze=request.getParameter("xuanze");
	  if(xuanze==null){
		  return "redirect:list";
	    }
	    String[] courseId=request.getParameterValues("coursesId");
	    try{
	    	if(courseId.length==0){
	    		return "redirect:list";
	    	}
	    }catch(Exception e){
	    	return "redirect:list";
	    }
	    
	    int[] ci=new int[1000];
		  for(int i=0;i<courseId.length;i++){
			  ci[i]= new Integer(courseId[i]);
		  }
		  List<Course> course=this.teachermanageService.getCourse(ci);
	    if(xuanze.equals("tianjia")){
	    	for(Course c:course){
		    	t.getCourses().add(c);
		    	c.getTeachers().add(t);
		    }
	    }
	    if(xuanze.equals("shanchu")){
	    	
	    	teachermanageService.clearCourse(t.getId());
	    }
		this.teachermanageService.editTeacher(t,ci);
		return "redirect:list";
	}

  @RequestMapping(value="del",method=RequestMethod.GET)
	public String toDelete(@RequestParam("id") int teacherId,
			HttpServletRequest request){
	  
		Teacher t=this.teachermanageService.getTeacher(teacherId);
		this.teachermanageService.deleteTeacher(t);
		return "redirect:list";
	}
  
  
  @RequestMapping(value="del2",method=RequestMethod.GET)
  public String toDelete2(@RequestParam(name="pageNum", defaultValue="1") int pageNum,
			@RequestParam(name="searchParam",defaultValue="") String searchParam,HttpServletRequest request,HttpSession session,
			Model model){
	 
		Page<Teacher> page;
		if(searchParam==null || "".equals(searchParam)){
			page=this.teachermanageService.listTeacher(pageNum,5,null);
		}else{
			page=this.teachermanageService.listTeacher(pageNum, 5, new Object[]{searchParam});
		}
		request.setAttribute("page", page);
		request.setAttribute("searchParam", searchParam);
		session.setAttribute("action2", "del2");
		return "teacher/list";
	}
  
  @RequestMapping(value="edit2",method=RequestMethod.GET)
  public String edit2(@RequestParam(name="pageNum", defaultValue="1") int pageNum,
			@RequestParam(name="searchParam",defaultValue="") String searchParam,HttpServletRequest request,HttpSession session,
			Model model){
	    System.out.println("edit2");
		Page<Teacher> page;
		if(searchParam==null || "".equals(searchParam)){
			page=this.teachermanageService.listTeacher(pageNum,5,null);
		}else{
			page=this.teachermanageService.listTeacher(pageNum, 5, new Object[]{searchParam});
		}
		request.setAttribute("page", page);
		request.setAttribute("searchParam", searchParam);
		session.setAttribute("action2", "edit2");
		return "teacher/list";
	}
  
     
	@RequestMapping("list")
	public String list(@RequestParam(name="pageNum", defaultValue="1") int pageNum,
			@RequestParam(name="searchParam",defaultValue="") String searchParam,
			HttpServletRequest request,HttpSession session,
			Model model){
		System.out.println("list");
		Page<Teacher> page;
		try {
			searchParam = new String(searchParam.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(searchParam==null || "".equals(searchParam)){
			page=this.teachermanageService.listTeacher(pageNum,5,null);
		}else{
			page=this.teachermanageService.listTeacher(pageNum, 5, new Object[]{searchParam});
		}
		session.setAttribute("page", page);
		request.setAttribute("searchParam", searchParam);
		session.setAttribute("action2", "list");
		request.setAttribute("skipNum",pageNum);
		return "teacher/list";
	}
}
