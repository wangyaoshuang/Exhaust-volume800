package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ChooseCourseDao;
import com.entity.Chapter;



@Service
@Transactional(readOnly=false)
public class ChooseCourseService {
	@Resource
	private ChooseCourseDao chooseCourseDao;
	
	public List<Chapter> chaptersList(int id){
		List chapslist = new ArrayList<Chapter>();
		chapslist = chooseCourseDao.FindChapters(id);
		return chapslist;
	}
}
