package com.jial.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jial.o2o.ShopException;
import com.jial.o2o.dao.ShopDao;
import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.enums.ShopStateEnum;
import com.jial.o2o.service.ShopService;
import com.jial.o2o.util.ImgUtil;
import com.jial.o2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, File shopImg) {
		//空值判断
		if(shop == null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try{
			//给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0){
				throw new ShopException("店铺创建失败");
			}else{
				if(shopImg != null){
					//存储图片
					try {
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						throw new ShopException("addShopImg error:"+e.getMessage());
					}
					//更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0){
						throw new ShopException("更新店铺的图片地址失败");
					}
				}
			}
		}catch(Exception e){
			throw new ShopException("addShop error:"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, File shopImg) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		String shopImgAddr = ImgUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

}