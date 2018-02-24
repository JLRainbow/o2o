package com.jial.o2o.web.shopadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jial.o2o.dto.ProductCategoryExecution;
import com.jial.o2o.dto.Result;
import com.jial.o2o.entity.ProductCategory;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.enums.ProductCategoryStateEnum;
import com.jial.o2o.exception.ProductCategoryException;
import com.jial.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/getProductCategoryByShopId",method = RequestMethod.GET)
	@ResponseBody
	public Result<List<ProductCategory>> getProductCategoryByShopId(HttpServletRequest req){
//		Shop shop = new Shop();
//		shop.setShopId(1L);
//		req.getSession().setAttribute("currentShop", shop);
		
		Shop currentShop = (Shop)req.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if(currentShop!=null&&currentShop.getShopId()>0){
			 list = productCategoryService.getProductCategoryByShopId(currentShop.getShopId());
			 return new Result<List<ProductCategory>>(true ,list);
		}else{
			 ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
			 return new Result<List<ProductCategory>>(false ,productCategoryStateEnum.getState(),productCategoryStateEnum.getStateInfo());
		}
		
	}
	
	@RequestMapping(value="/addProductCategorys",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addProductCategorys(HttpServletRequest req,@RequestBody List<ProductCategory> productCategoryList){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		Shop currentShop = (Shop)req.getSession().getAttribute("currentShop");
		//遍历商品分类list  给每个设置shopId
		for(ProductCategory pc :productCategoryList){
			pc.setShopId(currentShop.getShopId());
			pc.setCreateTime(new Date());
			pc.setProductCategoryDesc(pc.getProductCategoryName());
		}
		if(productCategoryList!=null&&productCategoryList.size()>0){
			try {
				ProductCategoryExecution pce = productCategoryService.batchInsertProductCategory(productCategoryList);
				if(pce.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", false);
					modelMap.put("errMsg", pce.getStateInfo());
				}
			} catch (ProductCategoryException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
			
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少添加一个商品类别");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/deleteProductCategory",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteProductCategory(HttpServletRequest req,Long productCategoryId){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(productCategoryId!=null&&productCategoryId>0){
			Shop currentShop = (Shop)req.getSession().getAttribute("currentShop");
			
			try {
				ProductCategoryExecution pce = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if(pce.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", false);
					modelMap.put("errMsg", pce.getStateInfo());
				}
			} catch (ProductCategoryException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		
			
		return modelMap;
	}
}
