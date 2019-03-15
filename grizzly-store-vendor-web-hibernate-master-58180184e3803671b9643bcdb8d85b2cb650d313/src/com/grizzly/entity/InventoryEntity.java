package com.grizzly.entity;

import javax.persistence.*;

@Entity
@Table(name="Inventory")
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
	private int productId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private ProductEntity product;
	
	@Column(name="Required")
	private int productRequired;
	
	@Column(name="Buffer")
	private int productBuffer;

	@Column(name="Product_Stock",columnDefinition = "int default 0")
	private int productStock;
	
	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	public int getProductRequired() {
		return productRequired;
	}

	public void setProductRequired(int productRequired) {
		this.productRequired = productRequired;
	}
	
	public int getProductBuffer() {
		return productBuffer;
	}

	public void setProductBuffer(int productBuffer) {
		this.productBuffer = productBuffer;
	}
	
	public InventoryEntity()
	{
		
	}
	
	public InventoryEntity(int productId,int productBuffer)
	{
		this.productId = productId;
		this.productBuffer = productBuffer;
	}
}

