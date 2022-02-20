package com.hibernate.criteria.anno.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.hibernate.criteria.anno.model.AnotherEmployee;
import com.hibernate.criteria.anno.model.Employee;
import com.hibernate.criteria.anno.model.Skill;
import com.hibernate.criteria.anno.util.HibernateUtil;

@SuppressWarnings({"deprecation","unchecked"})
public class DataManager {
	public static void main(String[] args) {
		//basicCreteria();
		//creteriaWithRestriction();
		//createProjections();
		//createAnotherEmployee();
		//createFetch();
		//createDetached();
	}
	
	private static void basicCreteria() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Employee.class);

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
			crit.add(Restrictions.idEq(1003));

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
			//crit.add(Restrictions.idEq(1003));
			crit.setProjection(Projections.rowCount());

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
			
			List<Skill> emp1Skills = new ArrayList<>();
			Skill emp1Skill1 = Skill.builder()
					//.skillId(1000)
					.skillName("Vue.js")
					.skillType("7")
					.build();
			emp1Skills.add(emp1Skill1);
			Skill emp1Skill2 = Skill.builder()
//					.skillId(1000)
					.skillName("React.js")
					.skillType("8")
					.build();
			emp1Skills.add(emp1Skill2);
			//emp1.setEmpId(1000);
			emp1.setEmpName("Rahul");
			emp1.setSkills(emp1Skills);
			
			List<Skill> emp2Skills = new ArrayList<>();
			Skill emp2Skill1 = Skill.builder()
//					.skillId(1001)
					.skillName("Python")
					.skillType("9")
					.build();
			emp2Skills.add(emp2Skill1);
			//emp2.setEmpId(1001);
			emp2.setEmpName("Rakesh");
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
			//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit = session.createCriteria(AnotherEmployee.class, "another_employee");
			crit.setFetchMode("another_employee.skills", FetchMode.JOIN);
			crit.createAlias("another_employee.skills", "skills"); // inner join by default

			ProjectionList columns = Projections.projectionList()
							.add(Projections.property("empId"))
							.add(Projections.property("skills.skillName"));
			crit.setProjection(columns);

			
			List<Object[]> list = crit.list();

			for (Object obj : list) {
				Object[] result = (Object[]) obj;
				System.out.println("Emp Id : "+result[0]+" Skills : "+result[1]);
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
		deCrit.add(Property.forName("empId").eq(1003));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
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
