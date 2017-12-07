package com.ikkong.core.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import com.ikkong.core.constant.CommConts;
import com.ikkong.core.constant.ConstCache;
import com.ikkong.core.jfinal.ext.shiro.ShiroKit;
import com.ikkong.core.toolbox.support.DateTime;
import com.jfinal.plugin.ehcache.CacheKit;

public class ShiroFormFilter extends PathMatchingFilter {
	
	private String loginUrl = "/login/";

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String token = request.getParameter(CommConts.TOKEN_KEY);		
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		String headToken = httpReq.getHeader(CommConts.TOKEN_KEY);	
		DgUserToken cacheToken = CacheKit.get(ConstCache.CLIENT_CACHE, token);		
		
		if(ShiroKit.isAuthenticated() || cacheToken != null) {  
            return true;//已经登录过
        }
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
        if(isLoginRequest(req)) {
            return true;//继续过滤器链  
        } else {//保存当前地址并重定向到登录界面  
            saveRequestAndRedirectToLogin(req, resp);  
            return false;  
        }  
	}
	
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
        WebUtils.saveRequest(req);  
        WebUtils.issueRedirect(req, resp, loginUrl);  
    }
    
	private boolean isLoginRequest(HttpServletRequest req) {  
		String path = WebUtils.getPathWithinApplication(req);
        return pathsMatch(loginUrl, path);  
    }  

}
