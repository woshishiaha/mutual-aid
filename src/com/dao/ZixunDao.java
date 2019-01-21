package com.dao;

import java.util.List;

import com.model.Zixun;






public interface ZixunDao  {
	
	
	
	public void insertBean(Zixun bean);
	
	public void deleteBean(Zixun bean);
	
	public void updateBean(Zixun bean);

	public Zixun selectBean(String where);
	
	public List<Zixun> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
