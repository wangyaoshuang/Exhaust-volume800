package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.CourseDao;
import com.dao.TeachermanageDao;
import com.entity.Course;
import com.entity.Role;
import com.entity.Teacher;
import com.model.Page;

@Service
@Transactional(readOnly=false)
@RequestMapping("TeachermanageService")
public class TeachermanageService {
     @Resource
     private TeachermanageDao teachermanageDao;
     
     //得到所有权限
     public List findRole(){
    	 List<Role> roles=new ArrayList();
    	 roles=teachermanageDao.findRole();
    	 return roles;
     }
     
     
     //得到所有课程
     public List findCourse(){
    	 List<Course> courses=new ArrayList<>();
    	 courses=teachermanageDao.findCourse();
    	 return courses;
     }
     
     //寰楀埌鎵�瑕佹坊鍔犵殑璇剧▼
     public List getCourse(int[] coursesId){
    	 List<Course> courses=teachermanageDao.getCourse(coursesId);
    	 return courses;
     }
     
     @RequestMapping("addTeacher")
     public void addTeacher(Teacher t){
    	 
 		this.teachermanageDao.saveTeacher(t);
 	}
     
     
     
     @Transactional(readOnly=true)
 	public Teacher getTeacher(int teacherId){
 		return this.teachermanageDao.getTeacher(teacherId);
 	}
     
     public void editTeacher(Teacher t,int[] ci){
 		this.teachermanageDao.updateTeacher(t,ci);
 	}
     
     public void deleteTeacher(Teacher t){
 		Teacher pdb=this.teachermanageDao.getTeacher(t.getId());
 		Set<Course> courses=pdb.getCourses();
 		courses.remove(pdb);
 		pdb.setCourses(null);
 		
 		this.teachermanageDao.deleteTeacher(pdb);
 		
 	}
     
     public void deleteTeacher(int id){
    	 this.teachermanageDao.deleteTeacher(id);
     }
     
     @Transactional(readOnly=true)
 	public Page<Teacher> listTeacher(int pageNum,int pageSize,Object[] params){
 		return this.teachermanageDao.findTeacher(pageNum, pageSize, params);
 	}

    public void clearRole(int id){
    	teachermanageDao.clearCourse(id);
    }
     
	public void clearCourse(int id) {
		teachermanageDao.clearCourse(id);
	}
}
