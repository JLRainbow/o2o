package com.jial.o2o.service;

import java.io.File;

import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Shop;
/**
 * 店铺接口
 * @author jial
 *
 */
public interface ShopService {

	/**
	 * 添加店铺
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution addShop(Shop shop,File shopImg);
}
