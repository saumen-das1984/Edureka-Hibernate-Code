package com.hibernate.sql.anno.app;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.sql.anno.model.Employee;
import com.hibernate.sql.anno.util.HibernateUtil;


@SuppressWarnings("deprecation")
public class DataManager {
	public static void main(String[] args) {
		basicNativeScalar();
		basicNativeEntity();
		sqlNamedQuery();
	}
	
	private static void basicNativeScalar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			String sql = "select * from Employee";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Object> list = sqlQuery.list();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				Object[] result = (Object[]) obj;
				System.out.println(result[0]+":"+result[1]+":"+result[2]+":"+result[3]);
			}

			transaction.commit();
			System.out.println("Details Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static void basicNativeEntity() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			String sql = "select * from Employee";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Employee.class);
			
			@SuppressWarnings("unchecked")
			List<Object> list = sqlQuery.list();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				System.out.println(obj);
			}

			transaction.commit();
			System.out.println("Details Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void sqlNamedQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Query<Employee> namedSqlQuery = session.createNamedQuery("namedEmployeeQuery", 
					Employee.class);
			namedSqlQuery.setInteger("empId", 1003);
			
			@SuppressWarnings("unchecked")
			List<Employee> list = namedSqlQuery.getResultList();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				System.out.println(obj);
			}

			transaction.commit();
			System.out.println("Details Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
