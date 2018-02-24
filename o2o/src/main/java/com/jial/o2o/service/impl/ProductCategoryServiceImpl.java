package com.jial.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jial.o2o.dao.ProductCategoryDao;
import com.jial.o2o.dto.ProductCategoryExecution;
import com.jial.o2o.entity.ProductCategory;
import com.jial.o2o.enums.ProductCategoryStateEnum;
import com.jial.o2o.exception.ProductCategoryException;
import com.jial.o2o.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryByShopId(long shopId) {
		return productCategoryDao.queryProductCategoryByShopId(shopId);
	}

	
	@Override
	@Transactional
	public ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryException {
		if(productCategoryList!=null&&productCategoryList.size()>0){
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum<=0){
					throw new ProductCategoryException("商品类别创建失败");
				}else{
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryException("batchInsertProductCategory error:"+e.getMessage());
			}
		}else{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}


	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryException {
		// TODO 将此类别下的商品里的类别id置为空
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum<=0){
				throw new ProductCategoryException("商品类别删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryException("deleteProductCategory error:"+e.getMessage());
		}
	}

}
