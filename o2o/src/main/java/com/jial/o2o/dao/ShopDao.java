package com.jial.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jial.o2o.entity.Shop;
/**
 * 店铺接口
 * @author jial
 *
 */
public interface ShopDao {
	
	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 分页查询店铺列表，查询条件：店铺名（模糊），店铺状态，店铺类型，区域Id,owner
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取
	 * @param pageSize 返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
	/**
	 * 通过shopId 查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);

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
