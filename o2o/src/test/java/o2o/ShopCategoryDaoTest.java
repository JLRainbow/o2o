package o2o;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dao.ShopCategoryDao;
import com.jial.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory(){
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2, shopCategoryList.size());
		
		ShopCategory shopCategoryCondition = new ShopCategory();
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shopCategoryCondition.setParent(shopCategory);
		List<ShopCategory> shopCategoryList2 = shopCategoryDao.queryShopCategory(shopCategoryCondition);
		assertEquals(1, shopCategoryList2.size());

	}
}
