package com.jial.o2o.dao;

import com.jial.o2o.entity.Shop;
/**
 * 店铺接口
 * @author jial
 *
 */
public interface ShopDao {

	/**
	 * 新增店铺
	 * 
	 * @param shop
	 * @return effectedNum
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺信息
	 * 
	 * @param shop
	 * @return effectedNum
	 */
	int updateShop(Shop shop);
}
