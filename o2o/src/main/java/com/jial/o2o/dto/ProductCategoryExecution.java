package com.jial.o2o.dto;

import java.util.List;

import com.jial.o2o.entity.ProductCategory;
import com.jial.o2o.enums.ProductCategoryStateEnum;

/**
 * 商品类别返回类
 * 
 * @author jial
 *
 */
public class ProductCategoryExecution {

	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;

	private ProductCategory productCategory;

	private List<ProductCategory> productCategoryList;

	private int count;

	public ProductCategoryExecution() {
	}

	// 操作失败的的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum pcse) {
		this.state = pcse.getState();
		this.stateInfo = pcse.getStateInfo();
	}

	// 操作失败的的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum pcse,ProductCategory productCategory) {
		this.state = pcse.getState();
		this.stateInfo = pcse.getStateInfo();
		this.productCategory = productCategory;
	}

	// 操作失败的的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum pcse,List<ProductCategory> productCategoryList) {
		this.state = pcse.getState();
		this.stateInfo = pcse.getStateInfo();
		this.productCategoryList = productCategoryList;
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
