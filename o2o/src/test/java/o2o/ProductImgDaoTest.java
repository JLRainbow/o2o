package o2o;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jial.o2o.dao.ProductImgDao;
import com.jial.o2o.entity.ProductImg;

public class ProductImgDaoTest extends BaseTest {

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testBatchInsertProductImg(){
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("test1");
		productImg1.setImgDesc("test1desc");
		productImg1.setPriority(10);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("test2");
		productImg2.setImgDesc("test2desc");
		productImg2.setPriority(20);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2,effectedNum);
	}
}
