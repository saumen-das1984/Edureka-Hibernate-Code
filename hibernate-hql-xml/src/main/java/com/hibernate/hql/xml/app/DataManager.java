package com.hibernate.hql.xml.app;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.hql.xml.model.Employee;
import com.hibernate.hql.xml.util.HibernateUtil;


@SuppressWarnings("deprecation")
public class DataManager {
	public static void main(String[] args) {
		retrieveEmployee();
	}
	
	private static void retrieveEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
//			@SuppressWarnings("rawtypes")
//			Query query = session.createQuery("from Employee as employee");
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("select employee.empName from Employee as employee");
			
			@SuppressWarnings("unchecked")
			List<Object> list = query.list();
			System.out.println("Size of Employee List : "+list.size());
			for (Object obj : list) {
				System.out.println(obj);
			}
			
			//Group By Clause
			@SuppressWarnings("rawtypes")
			Query querygr = session.createQuery("select sum(employee.salary), employee.deptId from Employee as employee group by employee.deptId");
			
			@SuppressWarnings("unchecked")
			List<Object> listgr = querygr.list();
			System.out.println("Size of Employee List : "+listgr.size());
			for (Object obj : listgr) {
				Object[] result = (Object[]) obj;
				System.out.println(result[0]+" : "+result[1]);
			}
			
			//Order By Clause
			@SuppressWarnings("rawtypes")
			Query queryor = session.createQuery("from Employee as employee order by employee.salary desc");
			
			@SuppressWarnings("unchecked")
			List<Object> listor = queryor.list();
			System.out.println("Size of order Employee List : "+listor.size());
			for (Object obj : listor) {
				Employee result = (Employee) obj;
				System.out.println(result);
			}
			
			//Update
			@SuppressWarnings("rawtypes")
			Query queryupd = session.createQuery("update Employee employee set empName=:empName where empId=:empId");
			queryupd.setParameter("empName", "Rahul");
			queryupd.setParameter("empId", 1003);
			
			int updCount = queryupd.executeUpdate();
			System.out.println("Updated Employee Count : "+updCount);
			
			//Delete
			@SuppressWarnings("rawtypes")
			Query querydel = session.createQuery("delete from Employee employee where empId=:empId");
			querydel.setParameter("empId", 1003);
			
			int delCount = querydel.executeUpdate();
			System.out.println("Deleted Employee Count : "+delCount);
			
			//Insert
			String insQuery = "insert into Employee (empId, empName, salary, age, deptId) "
			+"select empId, empName, salary, age, deptId from AnotherEmployee another_employee where empId=7000";
			@SuppressWarnings("rawtypes")
			Query queryins = session.createQuery(insQuery);
			
			int insCount = queryins.executeUpdate();
			System.out.println("Inserted Employee Count : "+insCount);
			
			//Aggregate
			String agrQuery = "select avg(employee.salary), sum(employee.salary), max(employee.salary), min(employee.salary), count(employee) from Employee employee";
			@SuppressWarnings("rawtypes")
			Query queryagr = session.createQuery(agrQuery);
			
			@SuppressWarnings("unchecked")
			List<Object> listagr = queryagr.list();
			
			System.out.println("Size of Employee List : "+listagr.size());
			for (Object obj : listagr) {
				Object[] result = (Object[]) obj;
				System.out.println("Avarage Salary : "+result[0]+" Total Salary : "+result[1]+" Maximum Salary : "+result[2]+" Minimum Salary : "+result[3]+" Total Employee Count : "+result[4]);
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
