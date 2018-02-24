package com.jial.o2o.dao;

import com.jial.o2o.entity.Product;

/**
 * 商品接口
 * @author jial
 *
 */
public interface ProductDao {

	/**
	 * 添加商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	
}
