package com.sankalp.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sankalp.entites.Category;
import com.sankalp.entites.Product;
import com.sankalp.entites.User;


public class HibernateConnection {
	private static SessionFactory sessionFactory;
	static {
		try {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Category.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/shopping?useSSL=false");
		configuration.setProperty("hibernate.connection.password", "root");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		
//		oracle Database Configuration
//		configuration.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
//		configuration.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe");
//		configuration.setProperty("hibernate.connection.password", "oracle");
//		configuration.setProperty("hibernate.connection.username", "System");
//		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.show_sql", "true");
sessionFactory=configuration.buildSessionFactory();
	 } catch (Exception e) {
		  System.out.println(HibernateConnection.class.getCanonicalName()+"->"+e.toString()); 
		  } }

	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;

	}
}
