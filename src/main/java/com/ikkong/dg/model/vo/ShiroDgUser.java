package com.ikkong.dg.model.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShiroDgUser implements Serializable {

	private Long id;
	private String name; //昵称，大小写字母加汉字下划线组成
	private String phoneNo; //注册手机号码
//	private Date registerTime; //用户注册时间，当前系统时间
	
	public ShiroDgUser(Long id, String phoneNo, String name){
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
	}
	
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
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return phoneNo;
	}
}
