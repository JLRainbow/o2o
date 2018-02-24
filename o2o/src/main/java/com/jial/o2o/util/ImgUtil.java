package com.jial.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jial.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
/**
 * 图片处理工具类
 * @author jial
 *
 */
public class ImgUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random random = new Random();
	/**
	 * 生成缩略图
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(ImageHolder thumbnail ,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage())
			.size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.3f)
			.outputQuality(0.3f)
			.toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		// 获取上传文件的文件名
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日时分秒+五位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取五位随机数
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr+rannum;
	}

	public static void main(String[] args) throws IOException {
		
		Thumbnails.of(new File("E:/test.jpg"))
		.size(200, 200)//设置图片大小
		.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.3f)//设置水印的位置和透明度
		.outputQuality(0.8f)//压缩图片
		.toFile("E:/testwatermark.jpg");//输出位置
	}
	/**
	 * path如果是文件路径就删除该文件
	 * path如果是目录路径就删除该目录下的所有文件
	 * @param path
	 */
	public static void deleteFileOrPath(String path){
		File fileOrPath = new File(PathUtil.getImgBasePath()+path);
		if(fileOrPath.exists()){
			if(fileOrPath.isDirectory()){
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
