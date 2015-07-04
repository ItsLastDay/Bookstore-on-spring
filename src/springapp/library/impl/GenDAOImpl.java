package springapp.library.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springapp.library.dao.GenDAO;

import com.googlecode.genericdao.dao.hibernate.original.GeneralDAOImpl;

@Repository("genDao")
public class GenDAOImpl extends GeneralDAOImpl implements GenDAO {
	@Autowired
    @Override 
    public void setSessionFactory(SessionFactory sessionFactory) {
    	super.setSessionFactory(sessionFactory);
    }
}
