package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//采纳
@Entity
@Table(name="t_Caina")
public class Caina {


	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
	
	@ManyToOne
	@JoinColumn(name="huifuid")
	private Huifu huifu;//关联的回复，外键
	
	private String ctime;//采纳时间
	
	private String status1;//求助者确认完成状态  未确认  确认完成
	
	private String status2;//帮助者确认完成状态  未确认  确认完成
	
	private String pj1;//求助者评价
	
	private String pj2;//帮助者评价

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

	public Huifu getHuifu() {
		return huifu;
	}

	public void setHuifu(Huifu huifu) {
		this.huifu = huifu;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getPj1() {
		return pj1;
	}

	public void setPj1(String pj1) {
		this.pj1 = pj1;
	}

	public String getPj2() {
		return pj2;
	}

	public void setPj2(String pj2) {
		this.pj2 = pj2;
	}
	
	
	
	
	
	
	
}
