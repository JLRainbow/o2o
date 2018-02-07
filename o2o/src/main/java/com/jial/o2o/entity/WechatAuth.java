package com.jial.o2o.entity;

import java.util.Date;

/**
 * 微信账号实体类
 * @author jial
 *
 */
public class WechatAuth {
	//微信账号id
	private Long wechatAuthId;
	//用户id
	private Long userId;
	//微信openid
	private String openId;
	//创建时间
	private Date createTime;
	//用户信息对象
	private PersonInfo personInfo;
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
