package com.grizzly.dao;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.grizzly.entity.LoginEntity;
import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.LoginPojo;

public class LoginDaoImpl implements LoginDao {

static int status = 0;
	
	public String checkUser(LoginPojo login) throws ApplicationException
	{
		StringBuilder builder = new StringBuilder();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		LoginEntity loginEntity = new LoginEntity();
		
		loginEntity.setUserName(login.getUserName());
		loginEntity.setPassword(login.getPassword());
		
		
		try {
			
			List l1 = (List) session.createQuery("from LoginEntity where Username='"+loginEntity.getUserName()+"' and Login_Password='"+loginEntity.getPassword()+"'").list();
			
			Iterator iterator = l1.iterator();
			
			System.out.println(l1.size());
			
			if(iterator.hasNext())
			{
				LoginEntity checkLogin = (LoginEntity)l1.get(0);
				
				boolean flag = checkLogin.getAccStatus();
					 
				if(flag)
				{
					builder.append(checkLogin.getRole());
				}
				else
				{
					builder.append("Blocked");
				}
			}
			
			else
			{
				List l2 = (List) session.createQuery("from LoginEntity where Username='"+loginEntity.getUserName()+"'").list();
				
				Iterator iteratorId = l2.iterator();
				
				if(iteratorId.hasNext())
				{
					LoginEntity checkStatus = (LoginEntity)l2.get(0);
					
					int userId = checkStatus.getUserId();
					
					boolean accessStatus = accountStatus(userId);
					
					if(accessStatus)
					{
						builder.append("TryAgain");
					}
					else
					{
						builder.append("Blocked");
					}
				}
				
				else
				{
					builder.append("TryAgain");
				}
				
			}
		} 
		
		catch (HibernateException ex) 
		{
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		String check = builder.toString();
		return check;
	}
	
	public static boolean accountStatus(int userId) throws ApplicationException
	{
		boolean access = true;
		
		try {
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			List l3 = (List) session.createQuery("from LoginEntity where ID ="+userId).list();
			
			Iterator iteratorStatus = l3.iterator();
			
			if(iteratorStatus.hasNext())
			{
				LoginEntity checkStatus = (LoginEntity)l3.get(0);
				
				access = checkStatus.getAccStatus();
				
				if(access)
				{
					if(status == 3)
					{
						checkStatus.setAccStatus(false);
						access = false;
						session.update(checkStatus);
					}
					else
					{
						status++;
					}
				}
				
			}
		} 
		
		catch (HibernateException ex) {
			
			throw new ApplicationException("Server Down!Please try again after some time.."); 
		}
		
		return access;
		
	}
	
	public int getId(String userName, String password) throws ApplicationException 
	{
		int id = 0; 
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			List l1 = (List) session.createQuery("from LoginEntity where Username='"+userName+"' and Login_Password='"+password+"'").list();
			
			Iterator iterator = l1.iterator();
			
			if(iterator.hasNext())
			{
				LoginEntity login = (LoginEntity)l1.get(0);
				
				id = login.getUserId();
			}
		} 
		
		catch (HibernateException ex) {

			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		finally
		{
			session.close();
		}
		
		return id;
	}
	
}
