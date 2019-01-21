package com.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.CainaDao;
import com.model.Caina;












public class CainaDaoImpl extends HibernateDaoSupport implements  CainaDao{


	public void deleteBean(Caina bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Caina bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Caina selectBean(String where) {
		List<Caina> list = this.getHibernateTemplate().find("from Caina " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Caina "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Caina> selectBeanList(final int start,final int limit,final String where) {
		return (List<Caina>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Caina> list = session.createQuery("from Caina "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Caina bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
