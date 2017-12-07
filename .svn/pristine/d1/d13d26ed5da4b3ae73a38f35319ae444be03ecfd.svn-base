package com.ikkong.core.shiro;

import java.util.Date;

import org.apache.shiro.authc.UsernamePasswordToken;

@SuppressWarnings("serial")
public class DgUserToken extends UsernamePasswordToken {

	private Long userId;
	private Date loginTime;//登录时间
	
	public DgUserToken(final String username, final char[] password) {
        super(username, password, false, null);
    }

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
