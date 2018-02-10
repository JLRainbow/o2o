package com.jial.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Shop;
public interface ShopService {

	/**
	 * 添加店铺
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName);
}
