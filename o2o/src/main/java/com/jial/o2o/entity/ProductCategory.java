package com.jial.o2o.entity;

import java.util.Date;
/**
 * 商品类别实体类
 * @author jial
 *
 */
public class ProductCategory {

	private Long productCategoryId;
	//店铺id
	private Long shopId;
	//商品类别名称
	private String productCategoryName;
	//商品列表描述
	private String productCategoryDesc;
	//权重
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public String getProductCategoryDesc() {
		return productCategoryDesc;
	}
	public void setProductCategoryDesc(String productCategoryDesc) {
		this.productCategoryDesc = productCategoryDesc;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
