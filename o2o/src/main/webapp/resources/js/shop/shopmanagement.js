$(function(){
	
	var shopId = getQueryString('shopId');
	var ShopInfoUrl = "/o2o/shopadmin/getShopManagementInfo?shopId="+shopId;
	
	$.getJSON(ShopInfoUrl,function (data){
		if(data.redirect){
			window.location.href = data.url;
		}else{
			if(data.shopId !=null&&data.shopId != undefined){
				shopId = data.shopId;
			}
			$("#shopInfo").attr('href','/o2o/shopadmin/shopOperation?shopId='+shopId);
		}
	})
})