package com.grizzly.service;

import java.util.ArrayList;

import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.ProductPojo;

public interface ProductService {

	int addProduct(ProductPojo product) throws ApplicationException;
	
	ArrayList getProducts() throws ApplicationException;
	
	boolean deleteProduct(int deleteId) throws ApplicationException;
	
	ArrayList getVendorProducts() throws ApplicationException;
	
	ArrayList getInventoryProducts() throws ApplicationException;
	
	int updateProduct(int id,int stock) throws ApplicationException;
	
}
