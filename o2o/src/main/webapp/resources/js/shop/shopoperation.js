$(function(){
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;
	
	var initUrl = "/o2o/shopadmin/getShopInitInfo";
	var registerShopUrl = "/o2o/shopadmin/registerShop";
	var ShopInfoUrl = "/o2o/shopadmin/getShopById?shopId="+shopId;
	var updateShopUrl = "/o2o/shopadmin/updateShop";
	if(!isEdit){
		getShopInitInfo();
	}else{
		getShopInfo(shopId);
	}
	//通过shopId回显店铺信息
	function getShopInfo(shopId){
		$.getJSON(ShopInfoUrl,function (data){
			if(data.success){
				var shop = data.shop;
				$("#shop-name").val(shop.shopName);
				$("#shop-addr").val(shop.shopAddr);
				$("#shop-phone").val(shop.phone);
				$("#shop-desc").val(shop.shopDesc);
				var shopCategory = "<option selected data-id='"+shop.shopCategory.shopCategoryId+"'>"+shop.shopCategory.shopCategoryName+"</option>";
				$("#shop-category").html(shopCategory);
				$("#shop-category").attr("disabled","disabled");//不可修改的
				var tempAreaHtml="";//区域是可以选择的  默认选择原先的值
				data.areaList.map(function (item,index){
					tempAreaHtml += "<option data-id='"+item.areaId+"'>"+item.areaName+"</option>"
				});
				$("#area").html(tempAreaHtml);
				$("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
			}
		})
	}
	//初始化店铺分类和所属区域下拉列表
	function getShopInitInfo(){
		$.getJSON(initUrl,function (data){
			if(data.success){
				var tempShopCategoryHtml="";
				var tempAreaHtml="";
				data.shopCategoryList.map(function (item,index){
					tempShopCategoryHtml += "<option data-id='"+item.shopCategoryId+"'>"+item.shopCategoryName+"</option>"
				});
				data.areaList.map(function (item,index){
					tempAreaHtml += "<option data-id='"+item.areaId+"'>"+item.areaName+"</option>"
				});
				$("#shop-category").html(tempShopCategoryHtml);
				$("#area").html(tempAreaHtml);
			}
		})
	}
	//表单提交
	$('#submit').click(function() {
		var shop = {};

		if(isEdit){
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();

		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};

		var shopImg = $("#shop-img")[0].files[0];
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		//获取验证码
		var captcha = $("#j_captcha").val();
		if(!captcha){
			$.toast('请输入验证码！');
			return;
		}
		formData.append('captcha', captcha);
		$.ajax({
			url : (isEdit?updateShopUrl:registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
				} else {
					$.toast('提交失败！');
				}
				$("#captcha_img").click();
			}
		});
	});
	
})