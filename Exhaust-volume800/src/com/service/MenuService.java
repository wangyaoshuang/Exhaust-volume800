package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuDao;
import com.dao.RolePowerDao;
import com.entity.Chapter;
import com.entity.Course;
import com.entity.Menu;
import com.entity.Power;
import com.entity.Role;
import com.entity.Teacher;

@Service
@Transactional(readOnly=true)
public class MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Resource
	private RolePowerDao rolePowerDao;
	
	public List<Course> selectAllCourseByTeacher(Teacher t){
		return rolePowerDao.findCoursesByTeacher(t);
	}
	
	public List<Chapter> selectAllChapter(){
		return rolePowerDao.findAllChapter();
	}
	
	public List<Menu> selectAllMenuByRole(Role r){
		List<Power> lists=rolePowerDao.findPowersByRole(r);
		return menuDao.findAllMenuByPowers(lists);
	}
	
	public List<Menu> selectParentMenuByRole(Role r){
		List<Power> lists=rolePowerDao.findPowersByRole(r);
		return menuDao.findParentMenuByPowers(lists);
	}
}
