package com.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Dao {
	Session session=null;
	Transaction t=null;
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Employee.class);
		configObj.addClass(Model.Category.class);
		configObj.addClass(Model.Lesson.class);
		configObj.addClass(Model.Lessonsignup.class);
		configObj.addClass(Model.Member.class);
		configObj.addClass(Model.Orderdetail.class);
		configObj.addClass(Model.Orderlist.class);
		configObj.addClass(Model.Product.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	public void addObj(Object obj) {
		try {
			session=configGo();
			t=session.beginTransaction();
			session.save(obj);

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			t.commit();

		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				t.rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public void updateObj(Object obj) {
		try {
			session=configGo();
			t=session.beginTransaction();
			session.update(obj);

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			t.commit();

		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				t.rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public void deleteObj(Object obj) {
		try {
			session=configGo();
			t=session.beginTransaction();
			session.delete(obj);

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			t.commit();
			
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				t.rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
}
