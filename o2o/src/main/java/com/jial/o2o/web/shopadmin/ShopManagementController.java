package com.jial.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Area;
import com.jial.o2o.entity.PersonInfo;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.entity.ShopCategory;
import com.jial.o2o.enums.ShopStateEnum;
import com.jial.o2o.service.AreaService;
import com.jial.o2o.service.ShopCategoryService;
import com.jial.o2o.service.ShopService;
import com.jial.o2o.util.CaptchaUtil;
import com.jial.o2o.util.HttpServletRequestUtil;
import com.jial.o2o.util.ImgUtil;
import com.jial.o2o.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopservice;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getShopInitInfo" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<>();
		List<Area> areaList = new ArrayList<>();
		List<ShopCategory> shopCategoryList = new ArrayList<>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value="/registerShop" , method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> registerShop(HttpServletRequest req){
		Map<String,Object> modelMap = new HashMap<>();
		//验证码比对
		if(!CaptchaUtil.checkCaptcha(req)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}
		//1.接受并转化相应的参数，包括店铺的信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(req, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(req)){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)req;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if(shop!=null&&shopImg!=null){
			//personInfo应该是从session中获取
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			ShopExecution se;
			try {
				se = shopservice.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(se.getState() == ShopStateEnum.CHECK.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
				
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请填写注册店铺信息");
			return modelMap;
		}
		//3.返回结果
		
	}
	
//	private static void inputStreamToFile(InputStream is , File file){
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while((bytesRead=is.read(buffer))!=-1){
//				os.write(buffer, 0, bytesRead);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("调用inputStreamToFile产生异常"+e.getMessage());
//		}finally {
//			try {
//				if(os != null){
//					os.close();
//				}
//				if(is != null){
//					is.close();
//				}
//			} catch (IOException e) {
//				throw new RuntimeException("inputStreamToFile关闭io异常"+e.getMessage());
//			}
//		}
//	}
}
