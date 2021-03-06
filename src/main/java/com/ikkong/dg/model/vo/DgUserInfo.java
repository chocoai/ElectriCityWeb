package com.ikkong.dg.model.vo;

import java.io.Serializable;

/**
 * 客户端用户信息
 * 
 * @author skybiran
 *
 */
@SuppressWarnings("serial")
public class DgUserInfo implements Serializable {

	private Long id;
	private String name; //昵称，大小写字母加汉字下划线组成
	private Integer gender;//性别
	private Integer status;//用户状态
	private String phoneNo; //注册手机号码
	private Long introId;//推荐人ID
	private String introPhone;//推荐人手机号
	private String code;//
	private String address;//
	private String headUrl; //头像地址
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getIntroId() {
		return introId;
	}
	public void setIntroId(Long introId) {
		this.introId = introId;
	}
	public String getIntroPhone() {
		return introPhone;
	}
	public void setIntroPhone(String introPhone) {
		this.introPhone = introPhone;
	}
	
	
}
