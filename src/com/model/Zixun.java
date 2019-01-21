package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//社区资讯
@Entity
@Table(name="t_Zixun")
public class Zixun {


	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	private String ztitle;//资讯标题
	
	@Column(name="zcontent", columnDefinition="TEXT")
	private String zcontent;//资讯内容
	
	private String ctime;//添加时间

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

	public String getZtitle() {
		return ztitle;
	}

	public void setZtitle(String ztitle) {
		this.ztitle = ztitle;
	}

	public String getZcontent() {
		return zcontent;
	}

	public void setZcontent(String zcontent) {
		this.zcontent = zcontent;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
	
	
	
	
}
