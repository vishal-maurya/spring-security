package com.ttnd.springSecurity.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserRoles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579285075427251145L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int id;
	
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="TYPE",unique=true,nullable=false,length=30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
