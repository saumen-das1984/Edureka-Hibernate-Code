package com.hibernate.criteria.xml.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.hibernate.criteria.xml.model.AnotherEmployee;
import com.hibernate.criteria.xml.model.Employee;
import com.hibernate.criteria.xml.util.HibernateUtil;


@SuppressWarnings("deprecation")
public class DataManager {
	public static void main(String[] args) {
		//basicCreteria();
		//creteriaWithRestriction();
		//createProjections();
		createAnotherEmployee();
		//createFetch();
		//createDetached();
	}
	
	private static void basicCreteria() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Employee.class);
			
			@SuppressWarnings("unchecked")
			List<Object> list = crit.list();
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
	
	private static void creteriaWithRestriction() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Employee.class);
			crit.add(Restrictions.idEq(7000));
			
			@SuppressWarnings("unchecked")
			List<Object> list = crit.list();
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
	
	private static void createProjections() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Employee.class);
			//crit.add(Restrictions.idEq(7000));
			crit.setProjection(Projections.rowCount());
			
			@SuppressWarnings("unchecked")
			List<Object> list = crit.list();
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
	
	private static void createAnotherEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			AnotherEmployee emp1 = new AnotherEmployee();
			AnotherEmployee emp2 = new AnotherEmployee();
			
			List<String> emp1Skills = new ArrayList<>();
			emp1Skills.add("Batting");
			emp1Skills.add("Blowing");
			emp1Skills.add("Keeping");
			emp1.setSkills(emp1Skills);
			
			List<String> emp2Skills = new ArrayList<>();
			emp2Skills.add("Java");
			emp2Skills.add("Node.js");
			emp2Skills.add("Angular");
			emp2.setSkills(emp2Skills);
			
			session.save(emp1);
			session.save(emp2);

			transaction.commit();
			System.out.println("Details Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static void createFetch() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(AnotherEmployee.class);
			
			@SuppressWarnings("unchecked")
			List<Object> list = crit.list();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				AnotherEmployee anotherEmp = (AnotherEmployee) obj;
				List<String> skills = anotherEmp.getSkills();
				for (String skill : skills) {
					System.out.println("Employee Skill : "+skill);
				}
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
	
	private static void createDetached() {
		DetachedCriteria deCrit = DetachedCriteria.forClass(Employee.class);
		deCrit.add(Property.forName("empId").eq(9000));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			List<Object> list = deCrit.getExecutableCriteria(session).list();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				System.out.println("Employee - DetachedCriteria : "+obj);
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
