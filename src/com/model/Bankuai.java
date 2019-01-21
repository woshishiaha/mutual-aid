package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 板块
 */
@Entity
@Table(name="t_bankuai")
public class Bankuai {


	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	private String bankuaiming;//版块名称
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getBankuaiming() {
		return bankuaiming;
	}

	public void setBankuaiming(String bankuaiming) {
		this.bankuaiming = bankuaiming;
	}

	
	
	
	
}
