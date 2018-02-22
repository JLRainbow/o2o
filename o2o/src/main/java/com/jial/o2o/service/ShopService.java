package com.jial.o2o.service;

import java.io.InputStream;

import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.exception.ShopException;
public interface ShopService {
	/**
	 * 根据店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 修改店铺信息  包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopException
	 */
	ShopExecution updateShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopException; 

	/**
	 * 添加店铺
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName);
}
