package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Chapter;
import com.service.ChooseCourseService;

@Controller
@RequestMapping("choose")
public class ChooseCourseController {
	@Resource 
	private ChooseCourseService chooseCourseService;
	
	@RequestMapping(value="course",method=RequestMethod.GET	)
	public String trfindChapter(int id,HttpSession session,HttpServletRequest request,HttpServletResponse reponse){
		List <Chapter> chapterList = new ArrayList<Chapter>();
		chapterList = chooseCourseService.chaptersList(id);
		for(int i=0;i<chapterList.size();i++){
			System.out.println("查询出来的章节是："+chapterList.get(i).getName());
		}
		session.setAttribute("chapterList",chapterList);
		return "exam/choose";
	}
}
