package com.jial.o2o.service;

import java.util.List;

import com.jial.o2o.dto.ImageHolder;
import com.jial.o2o.dto.ProductExecution;
import com.jial.o2o.entity.Product;
import com.jial.o2o.exception.ProductException;

public interface ProductService {

	/**
	 * 添加商品信息以及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 * @throws ProductException
	 */
	ProductExecution insertProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgList) throws ProductException;
}
