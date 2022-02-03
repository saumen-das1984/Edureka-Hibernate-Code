package com.hibernate.config.annotation.app;

import java.io.IOException;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.config.annotation.model.Branch;
import com.hibernate.config.annotation.util.HibernateUtil;

public class DataHandler {

	// TODO
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try {
			DataHandler dataHandler = new DataHandler();

			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			int option = 0;

			System.out
					.println("\n 1.Create \n 2.Update \n 3.Read \n 4.Delete \n 5.Exit  \nEnter Your Option :  ");
			option = s.nextInt();

			switch (option) {
			case 1:
				System.out.println("Enter Id  \n");
				int id = s.nextInt();
				System.out.println("Enter Branch Name \n");

				String branchName = s.next();
				System.out.println("Branch Status  \n");
				Boolean branchStatus = s.nextBoolean();
				dataHandler.createBranch(id, branchName, branchStatus);
				break;
			case 2:
				System.out.println("Enter Id  \n");
				int updateId = s.nextInt();
				System.out.println("Enter Branch Name  \n");
				String updateBranchName = s.next();
				dataHandler.updateBranch(updateId, updateBranchName);
				break;
			case 3:
				System.out.println("Enter Id  \n");
				int readId = s.nextInt();
				dataHandler.readBranchDetails(readId);
				break;
			case 4:
				System.out.println("Enter Id  \n");
				int deleteId = s.nextInt();
				dataHandler.detleteBranch(deleteId);
				break;
			case 5:
				System.out.println("Exit  \n");
				return;

			default:
				System.out.println("Please Provide The Valid Option  \n");
				break;
			}
		} finally {
			HibernateUtil.shutDown();
		}
	}

	public void createBranch(int branchId, String branchName,
			boolean branchStatus) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			branch.setBranchName(branchName);
			branch.setBranchStatus(branchStatus);
			session.save(branch);
			transaction.commit();
			System.out.println("Dateils Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void readBranchDetails(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Branch branch = (Branch) session.get(Branch.class, id);
			System.out.println("Branch Details : " + branch.getBranchName());
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateBranch(int id, String branchName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Branch branch = (Branch) session.get(Branch.class, id);
			branch.setBranchName(branchName);
			session.update(branch);
			transaction.commit();
			System.out.println("Branch Updated Successfully ..!");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void detleteBranch(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Branch branch = (Branch) session.get(Branch.class, id);
			branch.setBranchId(id);
			session.delete(branch);
			System.out.println("Branch deleted Successfully ..!");
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			;
		} finally {
			session.close();
		}

	}
}