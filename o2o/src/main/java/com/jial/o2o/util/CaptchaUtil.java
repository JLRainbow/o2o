package com.jial.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码比对工具类
 * @author jial
 *
 */
public class CaptchaUtil {

	public static boolean checkCaptcha(HttpServletRequest req){
		//获取生成的验证码
		String captchaExpected = (String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//获取输入的验证码
		String captcha = HttpServletRequestUtil.getString(req,
				"captcha");
		if (captcha == null || !captcha.equalsIgnoreCase(captchaExpected)) {
			return false;
		}
		return true;
	}
}
