package com.jial.o2o.service;

import java.util.List;


import com.jial.o2o.dto.ProductCategoryExecution;
import com.jial.o2o.entity.ProductCategory;
import com.jial.o2o.exception.ProductCategoryException;

public interface ProductCategoryService {

	/**
	 * 通过shopId查询该店铺下的商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryByShopId(long shopId);
	/**
	 * 批量插入商品类别信息
	 * @param ProductCategoryList
	 * @return
	 * @throws ProductCategoryException
	 */
	ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException;
	/**
	 * 将此类别下的商品里的类别id置为空，再删除该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryException;
}
