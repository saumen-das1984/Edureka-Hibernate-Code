package com.hibernate.session.scope.anno.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernate.session.scope.anno.model.Branch;
import com.hibernate.session.scope.anno.model.BranchAddress;

public class HibernateUtil {
	private static SessionFactory sessionFactory  ;
	static {
	    Configuration configuration = new Configuration();

	    configuration.addAnnotatedClass (BranchAddress.class);
	    configuration.addAnnotatedClass (Branch.class);
	    configuration.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
	    configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/edureka_test");
	    configuration.setProperty("hibernate.connection.username", "root");
	    configuration.setProperty("hibernate.connection.password", "root");
	    configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    configuration.setProperty("hibernate.hbm2ddl.auto", "update");
	    configuration.setProperty("hibernate.show_sql", "true");
	    configuration.setProperty("hibernate.format_sql", "true");
	    configuration.setProperty("hibernate.connection.pool_size", "50");
//	    configuration.setProperty("hibernate.current_session_context_class","thread");

	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

	    sessionFactory = configuration.buildSessionFactory(builder.build());

	}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}