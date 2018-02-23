package com.jial.o2o.web.shopadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {

	@RequestMapping(value="/shopOperation")
	public String shopOperation(){
		return "shop/shopoperation";
	}
	
	@RequestMapping(value="/shopList")
	public String shopList(){
		return "shop/shoplist";
	}
	@RequestMapping(value="/shopManagement")
	public String shopManagement(){
		return "shop/shopmanagement";
	}
}
