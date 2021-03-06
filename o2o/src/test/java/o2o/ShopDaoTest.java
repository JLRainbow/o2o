package o2o;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dao.ShopDao;
import com.jial.o2o.entity.Area;
import com.jial.o2o.entity.PersonInfo;
import com.jial.o2o.entity.Shop;
import com.jial.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopListAndCount(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 3);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表大小"+shopList.size());
		System.out.println("店铺总数"+count);
		Area area = new Area();
		area.setAreaId(2);
		shopCondition.setArea(area);
		List<Shop> shopList2 = shopDao.queryShopList(shopCondition, 0, 3);
		int count2 = shopDao.queryShopCount(shopCondition);
		System.out.println("2店铺列表大小"+shopList2.size());
		System.out.println("2店铺总数"+count2);
	}

	@Test
	public void testInsertShop(){
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
		shop.setShopName("testName2");
		shop.setShopDesc("testDesc2");
		shop.setShopAddr("testAddr");
		shop.setPhone("123");
		shop.setShopImg("img");
		shop.setPriority(1);
		shop.setEnableStatus(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setAdvice("审核中...");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testUpdateShop(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("测试Name");
		shop.setShopDesc("测试Desc");
		shop.setShopAddr("测试Addr");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testQueryByShopId(){
		Shop shop = shopDao.queryByShopId(1L);
		assertEquals("测试Name", shop.getShopName());
	}
	
}
