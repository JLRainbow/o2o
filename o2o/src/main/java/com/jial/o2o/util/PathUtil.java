package com.jial.o2o.util;
/**
 * 获取路径工具类
 * @author jial
 *
 */
public class PathUtil {
	//文件分隔符
	private static String separator = System.getProperty("file.separator");
	/**
	 * 获取图片的路径
	 * @return
	 */
	public static String getImgBasePath(){
		 
		String os = System.getProperty("os.name");//获取操作系统
		String basePath = "";
		if(os.toLowerCase().startsWith("win")){
			basePath = "D:/projectImg/";
		}else{
			basePath = "/home/projectImg/";
		}
		basePath.replace("/", separator);
		return basePath;
	}
	/**
	 * 获取各自店铺的图片路径
	 * @param shopId 
	 * @return
	 */
	public static String getShopImgPath(long shopId){
		String imgPath ="/upload/item/shop/"+shopId+"/";
		return imgPath.replace("/", separator);
	}
}
