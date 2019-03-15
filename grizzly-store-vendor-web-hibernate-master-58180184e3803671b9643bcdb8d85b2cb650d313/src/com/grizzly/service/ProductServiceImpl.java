package com.grizzly.service;

import java.util.ArrayList;

import com.grizzly.dao.ProductDao;
import com.grizzly.dao.ProductDaoImpl;
import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.ProductPojo;

public class ProductServiceImpl implements ProductService{

	public int addProduct(ProductPojo product) throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		int id = storeProduct.addProduct(product);
		return id;
	}
	
	public ArrayList getProducts() throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		ArrayList productList = storeProduct.getProducts();
		return productList;
	}
	
	public boolean deleteProduct(int deleteId) throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		boolean check = storeProduct.deleteProduct(deleteId);
		return check;
	}
	
	public ArrayList getVendorProducts() throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		ArrayList productList = storeProduct.getVendorProducts();
		return productList;
	}
	
	public ArrayList getInventoryProducts() throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		ArrayList productList = storeProduct.getInventoryProducts();
		return productList;
	}
	
	public int updateProduct(int id,int stock) throws ApplicationException
	{
		ProductDao storeProduct = new ProductDaoImpl();
		int update = storeProduct.updateProduct(id, stock);
		return update;
	}
	
}
