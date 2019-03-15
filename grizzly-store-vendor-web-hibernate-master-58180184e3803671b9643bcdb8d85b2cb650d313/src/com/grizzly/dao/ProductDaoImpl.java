package com.grizzly.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.grizzly.entity.InventoryEntity;
import com.grizzly.entity.ProductEntity;
import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.ProductPojo;

public class ProductDaoImpl implements ProductDao {

	public int addProduct(ProductPojo product) throws ApplicationException
	{
		int id = 0;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		try
		{
			Transaction transaction = session.beginTransaction();
			
			ProductEntity productEntity = new ProductEntity();
			
			productEntity.setProductName(product.getProductName());
			productEntity.setProductBrand(product.getProductBrand());
			productEntity.setProductCategory(product.getProductCategory());
			productEntity.setProductRating(product.getProductRating());
			productEntity.setProductPrice(product.getProductPrice());
		
			session.save(productEntity);
			transaction.commit();
			
			List l1 = (List) session.createQuery("from ProductEntity").list();
			
			ProductEntity addedProduct = (ProductEntity)l1.get(l1.size()-1);
			
			id = addedProduct.getProductId();
			
			if(id != 0)
			{
				Transaction inventoryTransaction = session.beginTransaction();
				
				InventoryEntity inventory = new InventoryEntity(addedProduct.getProductId(),product.getProductBuffer());
				inventory.setProduct(addedProduct);
				
				session.save(inventory);
				inventoryTransaction.commit();
			}
		}
		
		catch(HibernateException ex)
		{
			throw new ApplicationException("Server Down!Please try again after some time.."); 
		}
		
		finally
		{
			session.close();
		} 
		
		return id;
	}
	
	public ArrayList getProducts() throws ApplicationException
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		ArrayList productList = new ArrayList();
		
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			List l1 = session.createQuery("from ProductEntity").list();
			
			for(int i = 0; i<l1.size(); i++)
			{
				ProductEntity product = (ProductEntity)l1.get(i);
				
				ProductPojo products = new ProductPojo();
				
				products.setProductId(product.getProductId());
				products.setProductName(product.getProductName());
				products.setProductBrand(product.getProductBrand());
				products.setProductCategory(product.getProductCategory());
				products.setProductRating(product.getProductRating());
				
				productList.add(products);
			
			}
		} 
		
		catch (HibernateException ex) 
		{
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		finally
		{
			session.close();
		}
		
		return productList;	
	}
	
	public boolean deleteProduct(int deleteId) throws ApplicationException
	{
		boolean flag = true;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			List l1 = session.createQuery("from ProductEntity where productId="+deleteId).list();
			
			Iterator iterator = l1.iterator();
			
			if(iterator.hasNext())
			{
				ProductEntity deleteProduct = (ProductEntity)l1.get(0);
				
				session.delete(deleteProduct);
				transaction.commit();
				
				flag = true;
			}
			else
			{
				flag = false;
			}
		} 
		
		catch (HibernateException ex) 
		{
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		return flag;
	}
	
	public ArrayList getVendorProducts() throws ApplicationException 
	{
		
		ArrayList productList = new ArrayList();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			List l1 = (List) session.createQuery("from ProductEntity").list();
			
			for(int i = 0; i<l1.size(); i++)
			{
				ProductEntity product = (ProductEntity)l1.get(i);
				
				ProductPojo products = new ProductPojo();
				
				products.setProductName(product.getProductName());
				products.setProductId(product.getProductId());
				products.setProductBrand(product.getProductBrand());
				products.setProductCategory(product.getProductCategory());
				
				productList.add(products);
			}
		}
		
		catch (HibernateException ex) 
		{
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		finally
		{
			session.close();
		}
		
		return productList;	
		
	} 
	
	public ArrayList getInventoryProducts() throws ApplicationException
	{
		ArrayList productList = new ArrayList();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			List l1 = (List) session.createQuery("from ProductEntity").list();
			
			
			for(int i = 0; i<l1.size(); i++)
			{
				ProductEntity product = (ProductEntity)l1.get(i);
				
				ProductPojo products = new ProductPojo();
				
				products.setProductName(product.getProductName());
				products.setProductId(product.getProductId());
				
				if(product.getInventory().getProductBuffer() - product.getInventory().getProductStock() > 0)
				{
					products.setProductRequired(product.getInventory().getProductBuffer() - product.getInventory().getProductStock());
				}
				else
				{
					products.setProductRequired(0);
				}
				
				products.setProductBuffer(product.getInventory().getProductBuffer());
				products.setProductPrice(product.getProductPrice());
				products.setProductRating(product.getProductRating());
				
				productList.add(products);
			}
		} 
		
		catch (HibernateException e) {
			
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		return productList;
	}
	
	public int updateProduct(int id,int stock) throws ApplicationException
	{
		int update = 0;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			
			Transaction transaction = session.beginTransaction();
			
			/*List l1 = (List) session.createQuery("from InventoryEntity where productId="+id).list();
			
			Iterator iterator = l1.iterator();
			
			if(iterator.hasNext())
			{
				InventoryEntity updateStock = (InventoryEntity)l1.get(0);
				
				updateStock.setProductStock(stock);
				
				session.update(updateStock);*/
			
				InventoryEntity inventoryUpdate = session.get(InventoryEntity.class, id);
				inventoryUpdate.setProductStock(stock);
				
				session.update(inventoryUpdate);
					
				transaction.commit();
				
				update++;
			//}
		} 
		
		catch (HibernateException e) {
		
			throw new ApplicationException("Server Down!Please try again after some time..");
		}
		
		return update;
	}
	
}
