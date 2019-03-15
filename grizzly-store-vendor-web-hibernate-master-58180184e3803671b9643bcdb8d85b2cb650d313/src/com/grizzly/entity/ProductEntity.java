package com.grizzly.entity;

import javax.persistence.*;

@Entity
@Table(name="Product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private int productId;
	
	@Column(name="Name")
	private String productName;
	
	@Column(name="Brand")
	private String productBrand;
	
	@Column(name="Category")
	private String productCategory;
	
	@Column(name="Rating")
	private double productRating;
	
	@Column(name="Price")
	private double productPrice;
	
	@OneToOne(mappedBy="product",cascade = CascadeType.ALL)
    private InventoryEntity inventory;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
	public double getProductRating() {
		return productRating;
	}
	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public InventoryEntity getInventory() {
		return inventory;
	}
	public void setInventory(InventoryEntity inventory) {
		this.inventory = inventory;
	}
	public ProductEntity(int productId)
	{
		this.productId = productId;
	}
	
	public ProductEntity()
	{
		
	}
	
}
