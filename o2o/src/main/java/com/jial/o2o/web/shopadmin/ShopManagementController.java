package com.jial.o2o.web.shopadmin;

import java.io.IOException;
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

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopservice;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getShopManagementInfo" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getShopManagementInfo(HttpServletRequest req){
		Map<String,Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		if(shopId<=0){
			Object currentShopObj = req.getSession().getAttribute("currentShop");
			if(currentShopObj==null){
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shopList");
			}else{
				Shop currentShop = (Shop)currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
			
		}else{
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			req.getSession().setAttribute("currentShop",currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getShopList" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getShopList(HttpServletRequest req){
		Map<String,Object> modelMap = new HashMap<>();
		PersonInfo user = new PersonInfo();
		user.setUserId(1L);
		user.setName("test");
		req.getSession().setAttribute("user", user);
		user =(PersonInfo) req.getSession().getAttribute("user");
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution se = shopservice.getShopList(shopCondition, 0, 100);
			modelMap.put("success", true);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getShopById" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getShopById(HttpServletRequest req){
		Map<String,Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		if(shopId>-1){
			try {
				Shop shop = shopservice.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("success", true);
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	
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

	@SuppressWarnings("unchecked")
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
			PersonInfo owner = (PersonInfo) req.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				se = shopservice.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(se.getState() == ShopStateEnum.CHECK.getState()){
					modelMap.put("success", true);
					//该用户可以操作的店铺列表
					List<Shop> shopList = (List<Shop>) req.getSession().getAttribute("shopList");
					if(shopList == null||shopList.size() == 0){
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					req.getSession().setAttribute("shopList", shopList);
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
	
	@RequestMapping(value="/updateShop" , method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateShop(HttpServletRequest req){
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
		}
		//2.修改店铺
		if(shop!=null&&shop.getShopId()!=null){
			
			ShopExecution se;
			try {
				if(shopImg==null){
					se = shopservice.updateShop(shop, null,null);
				}else{
					se = shopservice.updateShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				}
				if(se.getState() == ShopStateEnum.SUCCESS.getState()){
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
			modelMap.put("errMsg", "请输入店铺id");
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
