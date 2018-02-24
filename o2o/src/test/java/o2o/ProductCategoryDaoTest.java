package o2o;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dao.ProductCategoryDao;
import com.jial.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testQueryProductCategoryByShopId(){
		long shopId = 1L;
		List<ProductCategory> list = productCategoryDao.queryProductCategoryByShopId(shopId);
		System.out.println("商品类别数量："+list.size());

	}
	@Test
	public void testBatchInsertProductCategory(){
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("test1");
		productCategory1.setProductCategoryDesc("test1desc");
		productCategory1.setPriority(20);
		productCategory1.setCreateTime(new Date());
		productCategory1.setShopId(1L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("test2");
		productCategory2.setProductCategoryDesc("test2desc");
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(1L);
		List<ProductCategory> ProductCategoryList = new ArrayList<ProductCategory>();
		ProductCategoryList.add(productCategory1);
		ProductCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(ProductCategoryList);
		assertEquals(2,effectedNum);
	}
	@Test
	public void testDeleteProductCategory(){
		int effectedNum = productCategoryDao.deleteProductCategory(4L, 1L);
		assertEquals(1,effectedNum);
	}
}
