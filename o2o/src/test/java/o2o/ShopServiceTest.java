package o2o;

import static org.junit.Assert.assertEquals;

import java.io.File;

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
	public void addShop() {
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
		ShopExecution se=shopservice.addShop(shop, shopImg);
		assertEquals(ShopStateEnum.CHECK.getState() ,se.getState());
	}
}
