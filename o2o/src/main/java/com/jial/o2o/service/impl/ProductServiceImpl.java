package com.jial.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jial.o2o.dao.ProductDao;
import com.jial.o2o.dao.ProductImgDao;
import com.jial.o2o.dto.ImageHolder;
import com.jial.o2o.dto.ProductExecution;
import com.jial.o2o.entity.Product;
import com.jial.o2o.exception.ProductException;
import com.jial.o2o.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductImgDao productImgDao;
	@Autowired
	private ProductDao productDao;

	/**
	 * 1.处理缩略图，获取缩略图相对路径并赋值给product
	 * 2.往tb_product写入商品信息，获取productId
	 * 3.结合productId批量处理商品详情图
	 * 4.将商品详情图列表插入tb_product_img中
	 */
	@Override
	@Transactional
	public ProductExecution insertProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
