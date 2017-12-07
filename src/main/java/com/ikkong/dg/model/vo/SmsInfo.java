package com.ikkong.dg.model.vo;

import java.io.Serializable;

/**
 * 短信找回凭证
 * @author skybiran
 *
 */
@SuppressWarnings("serial")
public class SmsInfo implements Serializable {

	private String smsKey;
	private String vcode;
	private String phoneNo;
	
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSmsKey() {
		return smsKey;
	}
	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}
	
	
}
