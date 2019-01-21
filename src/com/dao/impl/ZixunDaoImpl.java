package com.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.ZixunDao;
import com.model.Zixun;












public class ZixunDaoImpl extends HibernateDaoSupport implements  ZixunDao{


	public void deleteBean(Zixun bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Zixun bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Zixun selectBean(String where) {
		List<Zixun> list = this.getHibernateTemplate().find("from Zixun " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zixun "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zixun> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zixun>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zixun> list = session.createQuery("from Zixun "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zixun bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
