package com.jial.o2o.service;

import java.util.List;

import com.jial.o2o.entity.ShopCategory;

public interface ShopCategoryService {

	/**
	 * 根据传入条件获取店铺类别list
	 * @param ShopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
