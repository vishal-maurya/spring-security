package com.ttnd.springSecurity.Daos.DaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttnd.springSecurity.Daos.UserDao;
import com.ttnd.springSecurity.entities.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User getUser(String userName, String password) {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(User.class);
		Disjunction dt = Restrictions.disjunction();
		Criterion criterion = Restrictions.like("userName", userName);
		Criterion criterion2 = Restrictions.like("password", password);
		dt.add(criterion).add(criterion2);
		crit.add(dt);
		List result = crit.list();
		session.close();
		if (result.isEmpty() || result.get(0) == null) {
			return null;
		} else {
			return (User) result.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String userName) {
		List<User> users = new ArrayList<User>();
		users = sessionFactory.getCurrentSession().createQuery("from User where userName=?")
				.setParameter(0, userName).list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
