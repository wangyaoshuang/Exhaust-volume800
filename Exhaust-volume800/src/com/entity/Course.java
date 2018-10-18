package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int courseTime;
	private String coursePic;
	private String jieshao;
	private Set<Teacher> teachers=new HashSet<Teacher>();
	private Set<Chapter> chapters=new HashSet<Chapter>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	        mappedBy = "courses",
	        targetEntity = Teacher.class,
	        fetch=FetchType.EAGER
	    )
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	@OneToMany(mappedBy="course",fetch=FetchType.EAGER)
	public Set<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}
	@Override
	public boolean equals(Object obj) {
		Course c=(Course)obj;
		if(c.getId()==this.id){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public int hashCode() {
		return this.id;
	}
	public int getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(int courseTime) {
		this.courseTime = courseTime;
	}
	
	public String getJieshao() {
		return jieshao;
	}
	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}
	public String getCoursePic() {
		return coursePic;
	}
	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}
	
}
