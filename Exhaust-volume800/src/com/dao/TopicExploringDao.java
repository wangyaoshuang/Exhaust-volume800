package com.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Chapter;
import com.entity.Course;
import com.entity.Question;
import com.entity.QuestionType;
import com.model.Page;

@Repository
public class TopicExploringDao {

	@Resource
	private SessionFactory sessionFactory;
	public String selectCourse(int chapterId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Chapter where id="+chapterId);
		Chapter ch = (Chapter)query.uniqueResult();
		String course = ch.getCourse().getName();
		return course;
	}
		
}
