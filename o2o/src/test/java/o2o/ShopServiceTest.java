package o2o;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dto.ShopExecution;
import com.jial.o2o.entity.Area;
import com.jial.o2o.entity.PersonInfo;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.entity.ShopCategory;
import com.jial.o2o.enums.ShopStateEnum;
import com.jial.o2o.service.ShopService;

public class ShopServiceTest extends BaseTest {

	@Autowired
	private ShopService shopservice;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		ShopExecution se = shopservice.getShopList(shopCondition, 2, 3);
		System.out.println("店铺列表大小："+se.getShopList().size());
		System.out.println("店铺总数："+se.getCount());
	}

	@Test
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();

		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		Area area = new Area();
		area.setAreaId(1);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(personInfo);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		shop.setShopName("test2Name");
		shop.setShopDesc("test2Desc");
		shop.setShopAddr("test2Addr");
		shop.setPhone("123");
		shop.setPriority(1);
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中...");
		File shopImg =  new File("E:/test.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se=shopservice.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState() ,se.getState());
	}
	
	@Test
	public void testUpdateShop() throws FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的名称");
		File shopImg =  new File("E:/test2.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution updateShop = shopservice.updateShop(shop, is, shopImg.getName());
		System.out.println("新的图片地址："+updateShop.getShop().getShopImg());
	}
}
