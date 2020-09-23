package com.sankalp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sankalp.entites.Category;
import com.sankalp.entites.Product;
import com.sankalp.entites.User;

public class Dao {

	private static Dao dao = new Dao();

	private Dao() {

	}

	public static Dao geDao() {
		return dao;
	}

	public User insert(User user) {
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(user);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return user;
	}

	public Category addCategory(Category category) {
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(category);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return category;
	}

	public Product addProduct(Product product) {
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(product);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return product;
	}

	
	public List<Category> getCategoryList() {
		List<Category> list = new ArrayList<>();
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Category.class);
			list = criteria.list();
		} catch (Exception e) {
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return list;
	}

	public List<Product> getProductList() {
		List<Product> list = new ArrayList<>();
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
		} catch (Exception e) {
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return list;
	}
	
	public List<Product> getProductCategoryList(int cid) {
		List<Product> list = new ArrayList<>();
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			 Query query=session.createQuery("from Product as p where p.category.categoryId=: id");
			 query.setParameter("id", cid);
			list = query.list();
		} catch (Exception e) {
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return list;
	}

	public List<User> getUserList() {
		List<User> list = new ArrayList<>();
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			list = criteria.list();
		} catch (Exception e) {
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return list;
	}
//public Integer update(Model m) {
//Transaction transaction = null;
//int i=0;
//try  {
//	Session session = HibernateConnection.getSessionFactory().openSession();
//	  // start a transaction
//  transaction = session.beginTransaction();
// String hql="UPDATE Model set ename=:ename,esalary = :esalary WHERE eid = :eid"; 
// Query query=session.createQuery(hql);
// query.setString("ename", m.getEname());
// query.setInteger("esalary", m.getEsalary());
// query.setInteger("eid", m.getEid());
// i=query.executeUpdate();
//  // commit transaction
//  transaction.commit();
//} catch (Exception e) {
//  if (transaction != null) {
//      transaction.rollback();
//  }
//	System.out.println(Dao.class.getCanonicalName()+"->"+e.toString());
//}
//	return i;
//}
	/*
	 * public Model update(Model m) { Transaction transaction = null; try { Session
	 * session = HibernateConnection.getSessionFactory().openSession(); // start a
	 * transaction transaction = session.beginTransaction(); // save the student
	 * object session.update(m); // commit transaction transaction.commit(); } catch
	 * (Exception e) { if (transaction != null) { transaction.rollback(); }
	 * System.out.println(Dao.class.getCanonicalName()+"->"+e.toString()); } return
	 * m; }
	 * 
	 * public List<Model> getList() { List<Model> list=new ArrayList<>(); try {
	 * Session session=HibernateConnection.getSessionFactory().openSession();
	 * Criteria criteria = session.createCriteria(Model.class); list=
	 * criteria.list(); } catch (Exception e) {
	 * System.out.println(Dao.class.getCanonicalName()+"->"+e.toString()); } return
	 * list; }
	 * 
	 * // // //public List<Model> getList() { // List<Model> list=new ArrayList<>();
	 * // try { // String hql = "FROM Model"; // Session
	 * session=HibernateConnection.getSessionFactory().openSession(); // Query query
	 * = session.createQuery(hql); // list= query.list(); // } catch (Exception e) {
	 * // System.out.println(Dao.class.getCanonicalName()+"->"+e.toString()); // }
	 * // return list; //}
	 * 
	 * 
	 * public Integer remove(Model m) { Transaction transaction = null; int i=0; try
	 * { Session session = HibernateConnection.getSessionFactory().openSession(); //
	 * start a transaction transaction = session.beginTransaction(); String
	 * hql="delete from Model where eid= :eid"; Query
	 * query=session.createQuery(hql); query.setParameter("eid", m.getEid()); //
	 * query.setInteger("eid", m.getEid()); i=query.executeUpdate(); // commit
	 * transaction transaction.commit(); } catch (Exception e) { if (transaction !=
	 * null) { transaction.rollback(); }
	 * System.out.println(Dao.class.getCanonicalName()+"->"+e.toString()); } return
	 * i; } public Model getById(Model m) { Transaction transaction = null;
	 * 
	 * try { Session session =
	 * HibernateConnection.getSessionFactory().openSession(); // start a transaction
	 * transaction = session.beginTransaction(); String
	 * hql="from Model where eid= :eid"; Query query=session.createQuery(hql);
	 * 
	 * query.setInteger("eid", m.getEid()); m=(Model) query.uniqueResult(); //
	 * commit transaction transaction.commit(); } catch (Exception e) { if
	 * (transaction != null) { transaction.rollback(); }
	 * System.out.println(Dao.class.getCanonicalName()+"->"+e.toString()); } return
	 * m; }
	 */
	public User emailCheck(User u) {
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object

			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("userEmail", u.getUserEmail()));
			u = (User) cr.uniqueResult();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return u;
	}

	public Category categorybyId(int cid) {
		Category cat = null;
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			cat = session.get(Category.class, cid);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return cat;
	}

	public User login(User u) {
		Transaction transaction = null;
		try {
			Session session = HibernateConnection.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object

			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("userEmail", u.getUserEmail()));
			cr.add(Restrictions.eq("userPassword", u.getUserPassword()));
			u = (User) cr.uniqueResult();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(Dao.class.getCanonicalName() + "->" + e.toString());
		}
		return u;
	}

}
