package com.jial.o2o.dto;

import java.util.List;

import com.jial.o2o.entity.Product;
import com.jial.o2o.enums.ProductStateEnum;

/**
 * 商品返回类
 * 
 * @author jial
 *
 */
public class ProductExecution {

	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;

	private Product product;

	private List<Product> productList;

	private int count;

	public ProductExecution() {
	}

	// 操作失败的的构造器
	public ProductExecution(ProductStateEnum pse) {
		this.state = pse.getState();
		this.stateInfo = pse.getStateInfo();
	}

	// 操作失败的的构造器
	public ProductExecution(ProductStateEnum pse,Product product) {
		this.state = pse.getState();
		this.stateInfo = pse.getStateInfo();
		this.product = product;
	}

	// 操作失败的的构造器
	public ProductExecution(ProductStateEnum pse,List<Product> productList) {
		this.state = pse.getState();
		this.stateInfo = pse.getStateInfo();
		this.productList = productList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
}
