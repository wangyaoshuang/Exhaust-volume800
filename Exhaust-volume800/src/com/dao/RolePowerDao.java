package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Chapter;
import com.entity.Course;
import com.entity.Power;
import com.entity.Role;
import com.entity.Teacher;

@Repository
public class RolePowerDao {

	@Resource
	private SessionFactory sessionFactory;

	public List<Course> findAllCourse(){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Course");
		List<Course> lists=query.list();
		return lists;
	}
	
	public List<Course> findCoursesByTeacher(Teacher t){
		List<Course> allCourses=this.findAllCourse();
		Set<Course> courses=t.getCourses();
		List<Course> lists=new ArrayList<Course>();
		for(int i=0;i<allCourses.size();i++){
			if(courses.contains((Course)allCourses.get(i))){
				lists.add(allCourses.get(i));
			}
		}
		return lists;
		
	}
	
	public List<Power> findPowersByRole(Role r){
		List<Power> allPowers=this.findAllPowers();
		List<Power> lists=new ArrayList<Power>();
		Set<Power> powers=r.getPowers();
		for(int i=0;i<allPowers.size();i++){
			if(powers.contains((Power)allPowers.get(i))){
				lists.add(allPowers.get(i));
			}
		}
		return lists;
	}
	
	public List<Power> findAllPowers(){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Power");
		List<Power> lists=query.list();
		return lists;
	}
	
	public List<Chapter> findAllChapter(){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter");
		List<Chapter> lists=query.list();
		return lists;
	}
}
