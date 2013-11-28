package cn.btttech.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.btttech.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {  
	@Autowired
    private SessionFactory sessionFactory;  
  
  
    public Session getSession() {  
        return sessionFactory.getCurrentSession();  
    }  
  
    @Override  
    public <T> void save(T t) {  
        getSession().save(t);  
    }  
  
    @Override  
    public <T> void delete(T t) {  
        getSession().delete(t);  
    }  
  
    @Override  
    public <T> void delete(Class<T> entityClass, Integer id) {  
        getSession().delete(get(entityClass, id));  
    }  
  
    @Override  
    public <T> void update(T t) {  
        getSession().update(t);  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public <T> T get(Class<T> entityClass, Integer id) {  
        return (T) getSession().get(entityClass, id);  
    }  
  
    @Override  
    public <T> List<T> findAll(String hql, Class<T> entityClass) {  
        return findAll(hql, entityClass, new Object[] {});  
    }  
  
    @Override  
    public <T> List<T> findAll(String hql, Class<T> entityClass, Object param) {  
        return findAll(hql, entityClass, new Object[] { param });  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public <T> List<T> findAll(String hql, Class<T> entityClass,   
            Object[] params) {  
        Query query = getSession().createQuery(hql);  
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }  
        return (List<T>) query.list();  
    }  
  
    @Override  
    public <T> List<T> findByPage(final String hql, Class<T> entityClass,  
            final int firstResult, final int maxResult) {  
        return findByPage(hql, entityClass, new Object[] {}, firstResult,  
                maxResult);  
    }  
  
    @Override  
    public <T> List<T> findByPage(final String hql, Class<T> entityClass,  
            final Object param, final int firstResult, final int maxResult) {  
        return findByPage(hql, entityClass, new Object[] { param },  
                firstResult, maxResult);  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public <T> List<T> findByPage(final String hql, Class<T>     
            entityClass, final Object[] params, final int firstResult,   
            final int maxResult) {  
        Query query = getSession().createQuery(hql);  
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }  
        System.out.println(firstResult+" "+maxResult);
        //mysql 起始是从0开始的
        query.setFirstResult(firstResult-1);  
        query.setMaxResults(maxResult);  
        return (List<T>) query.list();  
    }

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int findByPageCount(String hql, Class<?> entityClass) {
		return findByPageCount(hql, entityClass, new Object[] {});
	}

	@Override
	public int findByPageCount(String hql, Class<?> entityClass, Object param) {
		return findByPageCount(hql, entityClass, new Object[] { param });
	}

	@Override
	public int findByPageCount(String hql, Class<?> entityClass, Object[] params) {
		Query query = getSession().createQuery(hql);  
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }  
        
		return ((Long)query.uniqueResult()).intValue();
	}  
  
}