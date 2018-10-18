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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exam")
public class Exam implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String e_time;
	private String title;
	private Teacher teacher;
	private double desired;
	private Set<Sort> sorts=new HashSet<Sort>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getE_time() {
		return e_time;
	}
	public void setE_time(String e_time) {
		this.e_time = e_time;
	}
	public double getDesired() {
		return desired;
	}
	public void setDesired(double desired) {
		this.desired = desired;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)
	@JoinColumn(name = "t_id")
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@OneToMany(mappedBy="exam",fetch=FetchType.EAGER)
	public Set<Sort> getSorts() {
		return sorts;
	}
	public void setSorts(Set<Sort> sorts) {
		this.sorts = sorts;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public boolean equals(Object obj) {
		Exam e=(Exam)obj;
		if(e.getId()==this.id)
			return true;
		else
			return false;
	}
	
	
	
}
