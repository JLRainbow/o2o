/**
 * 更换验证码图片
 */
function changeCaptcha(img){
	img.src="../Kaptcha?"+Math.floor(Math.random()*100);
}
/**
 * 根据传入的参数名返回对应的值
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}