package com.jial.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jial.o2o.entity.Area;
import com.jial.o2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/listAreas",method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listAreas() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			areaList = areaService.getAreaList();
			modelMap.put("rows", areaList);
			modelMap.put("total", areaList.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
}
