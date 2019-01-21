package com.dao;

import java.util.List;

import com.model.Caina;






public interface CainaDao  {
	
	
	
	public void insertBean(Caina bean);
	
	public void deleteBean(Caina bean);
	
	public void updateBean(Caina bean);

	public Caina selectBean(String where);
	
	public List<Caina> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
