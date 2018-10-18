package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="teacher")
public class Teacher implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private String email;
	private String hiredate;
	private String address;
	private String phone;
//	private byte[] photo;
	private String photo;
	private Set<Role> roles=new HashSet<Role>();
	private Set<Course> courses=new HashSet<Course>();
	private Set<Exam> exams=new HashSet<Exam>();
	private List<Recorder> Recorders=new ArrayList<Recorder>();
	
	@Id
	@GenericGenerator(strategy="assigned",name="login")
	@GeneratedValue(generator="login")
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToMany(
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE},	        
	        targetEntity = Role.class,
	        fetch=FetchType.EAGER
	        )
	@JoinTable(
	        name="teacherrolealt",
	        joinColumns=@JoinColumn(name="t_id"),
	        inverseJoinColumns=@JoinColumn(name="r_id")
	    )
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToMany(
	        targetEntity=com.entity.Course.class,
	        cascade={CascadeType.PERSIST, CascadeType.MERGE},
	        fetch=FetchType.EAGER
	    )
	@JoinTable(
	        name="teachercoursealt",
	        joinColumns=@JoinColumn(name="t_id"),
	        inverseJoinColumns=@JoinColumn(name="c_id")
	    )
	public Set<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.EAGER)
	public Set<Exam> getExams() {
		return exams;
	}
	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}
	
	@OneToMany(mappedBy="teacher")
	public List<Recorder> getRecorders() {
		return Recorders;
	}
	public void setRecorders(List<Recorder> recorders) {
		Recorders = recorders;
	}
	
}
