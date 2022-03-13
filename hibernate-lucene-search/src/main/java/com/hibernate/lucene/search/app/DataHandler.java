package com.hibernate.lucene.search.app;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.HibernateException;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.hibernate.lucene.search.model.Branch;
import com.hibernate.lucene.search.util.HibernateUtil;

import org.hibernate.search.FullTextQuery;

public class DataHandler {
	// TODO Drop and create the database in the mysql db before execution
	// drop database search
	// create database search
	public static void main(String[] args) throws IOException {
		try {
			DataHandler datahandler = new DataHandler();
//			datahandler.populateBranch();
			datahandler.doIndex();
			datahandler.searchBranch();
			datahandler.searchBranchOnProjection();
		} finally {
			HibernateUtil.shutdown();
		}
	}

	private void populateBranch() {
		Branch branch1 = new Branch("Test202", true);
		insertBranch(branch1);
		Branch branch2 = new Branch("Test203", true);
		insertBranch(branch2);
		Branch branch3 = new Branch("Test302", true);
		insertBranch(branch3);
		Branch branch4 = new Branch("Test304", true);
		insertBranch(branch4);

	}

	private void insertBranch(Branch branch) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(branch);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//	@SuppressWarnings("unchecked")
//	private void searchBranch() throws InterruptedException {
//		System.out.println("Search operation" );
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		FullTextEntityManager fullTextEntityManager 
//		  = (FullTextEntityManager) Search.getFullTextSession(session);
//		fullTextEntityManager.createIndexer().startAndWait();
//		try {
//			QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
//					  .buildQueryBuilder()
//					  .forEntity(Branch.class)
//					  .get();
//			org.apache.lucene.search.Query query = queryBuilder
//					  .keyword()
//					  .onField("branchName")
//					  .matching("Test202")
//					  .createQuery();
//			org.hibernate.search.jpa.FullTextQuery jpaQuery
//			  = fullTextEntityManager.createFullTextQuery(query, Branch.class);
//			List<Branch> searchResults = jpaQuery.getResultList();
//			System.out.println("Total branches " + searchResults.size());
//			for (Branch branch : searchResults) {
//				System.out.println("Branch Name " + branch.getBranchName());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			fullTextEntityManager.close();
//		}
//		System.out.println( );
//	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	private void searchBranch() {
		System.out.println("Search operation" );
		Session session = HibernateUtil.getSessionFactory().openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {

			QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Branch.class).get();
			Query luceneQuery = queryBuilder
					.bool()
					.should(queryBuilder.keyword().onField("branchName")
							.matching("Test202").createQuery()).createQuery();
			org.hibernate.Query hibernateQuery = fullTextSession
					.createFullTextQuery(luceneQuery, Branch.class);
			List<Branch> searchResults = hibernateQuery.list();
			System.out.println("Total branches " + searchResults.size());
			for (Branch branch : searchResults) {
				System.out.println("Branch Name " + branch.getBranchName());
			}
		} catch (Exception e) {
		} finally {
			fullTextSession.close();
		}
		System.out.println( );
	}

	@SuppressWarnings("unchecked")
	private void searchBranchOnProjection() {
		System.out.println("Search based on projection" );
		Session session = HibernateUtil.getSessionFactory().openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			QueryParser parser = new QueryParser("branchName",
					new StandardAnalyzer());
			String searchString = "branchName:" + "Test203" + " OR branchName:"
					+ "Test302";
			Query luceneQuery = parser.parse(searchString);
			FullTextQuery fullTextQuery = fullTextSession
					.createFullTextQuery(luceneQuery);
			fullTextQuery.setProjection("id", "branchName");
			List<Object[]> searchResults = (List<Object[]>) fullTextQuery
					.list();
			System.out.println("Search Results " + searchResults.size());
			for (Object[] result : searchResults) {
				System.out.println("Branch Id " + result[0]);
				System.out.println("Branch Name " + result[1]);
			}
		} catch (Exception e) {
		} finally {
			fullTextSession.close();
		}
		System.out.println( );
	}

	private void doIndex() {
		System.out.println("Indexing" );
		Session session = HibernateUtil.getSessionFactory().openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (Exception e) {
		} finally {
			fullTextSession.close();
		}
		System.out.println( );
	}
}
