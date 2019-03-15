package com.grizzly.dao;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			
			/*Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
			applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());*/
			
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		
		catch (Throwable ex)
		{
			System.out.println("Inital SessionFactory creation failed:"+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
