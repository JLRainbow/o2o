package com.jial.o2o.dao;

import java.util.List;

import com.jial.o2o.entity.ProductImg;

/**
 * 商品图片详情接口
 * @author jial
 *
 */
public interface ProductImgDao {

	/**
	 * 批量添加商品图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
}
