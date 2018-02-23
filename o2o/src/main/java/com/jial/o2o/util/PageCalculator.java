package com.jial.o2o.util;
/**
 * 分页计算工具类
 * @author jial
 *
 */
public class PageCalculator {

	public static int calculateRowIndex(int pageIndex,int pageSize){
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
