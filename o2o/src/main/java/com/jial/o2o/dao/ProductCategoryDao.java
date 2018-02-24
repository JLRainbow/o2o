package com.jial.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jial.o2o.entity.ProductCategory;

/**
 * 商品类别接口
 * @author jial
 *
 */
public interface ProductCategoryDao {

	/**
	 * 通过shopId查询该店铺下的商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryByShopId(long shopId);
	/**
	 * 批量添加商品类别
	 * @param ProductCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> ProductCategoryList);
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
