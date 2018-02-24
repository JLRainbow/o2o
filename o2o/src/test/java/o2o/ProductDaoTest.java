package o2o;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dao.ProductDao;
import com.jial.o2o.entity.Product;
import com.jial.o2o.entity.ProductCategory;
import com.jial.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest {

	@Autowired
	private ProductDao productDao;
	
	@Test 
	public void testInsertProduct(){
		Product product = new Product();
		product.setProductName("商品1");
		product.setProductDesc("商品描述");
		product.setImgAddr("imgaddr");
		product.setNormalPrice("11");
		product.setPromotionPrice("10");
		product.setPriority(10);
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		Shop shop = new Shop();
		shop.setShopId(1L);
		product.setShop(shop);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		product.setProductCategory(productCategory );
		productDao.insertProduct(product);
	}
}
