package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Chapter;

@Repository
public class ChooseCourseDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public List<Chapter> FindChapters(int id){
		List <Chapter> listChapters = new ArrayList<Chapter>();
		String hql = "from Chapter where c_id = "+ id;
		listChapters = sessionFactory.getCurrentSession().createQuery(hql).list();
		return listChapters;
	} 
	
	
}
