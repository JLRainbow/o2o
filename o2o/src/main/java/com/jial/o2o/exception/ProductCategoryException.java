package com.jial.o2o.exception;
/**
 * 商品类别异常类
 * @author jial
 *
 */
public class ProductCategoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234972892896963316L;


	public ProductCategoryException(String msg){
		super(msg);
	}
}
