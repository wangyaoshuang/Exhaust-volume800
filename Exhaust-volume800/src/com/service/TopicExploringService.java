package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TopicExploringDao;
import com.dao.ChapterDao;

@Service
@Transactional(readOnly = false)
public class TopicExploringService {
	
	@Resource
	private TopicExploringDao TopicExploringDao;
	
	public String getCourse(int chapterId){
		return this.TopicExploringDao.selectCourse(chapterId);
	}
}
