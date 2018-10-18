package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="regulation")
public class Regulation {
	private int id;
	private int yearrepeat;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYearrepeat() {
		return yearrepeat;
	}

	public void setYearrepeat(int yearrepeat) {
		this.yearrepeat = yearrepeat;
	}
		
	
}
