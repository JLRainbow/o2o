package com.jial.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jial.o2o.entity.ShopCategory;

/**
 * 店铺分类接口
 * @author jial
 *
 */
public interface ShopCategoryDao {

	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);
}
