/**
 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ikkong.core.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikkong.common.vo.ShiroUser;
import com.ikkong.core.interfaces.IShiro;
import com.ikkong.core.jfinal.ext.shiro.ShiroKit;
import com.ikkong.core.toolbox.Func;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.model.vo.ShiroDgUser;

public class DgShiroRealm extends AuthorizingRealm {
	public DgShiroRealm() {		
		super();
		setName("dgShiroRealm");
		setCredentialMatcher();//登陆密码自定义MD5 Hash匹配
	}
	private static Logger log = LoggerFactory.getLogger(DgShiroRealm.class);
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		log.info("Shiro登录认证启动");
		IShiro shiroFactory = ShiroManager.me().getDefaultShiroFactory();
		DgUserToken token = (DgUserToken) authcToken;
		
		DgUser user = shiroFactory.dgUser(token.getUsername());

		ShiroDgUser shiroDgUser = shiroFactory.shiroDgUser(user);

		SimpleAuthenticationInfo info = shiroFactory.dgUserInfo(shiroDgUser, user, getName());

		log.info("Shiro登录认证完毕");
		return info;
	}

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		IShiro shiroFactory = ShiroManager.me().getDefaultShiroFactory();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		Object userId = shiroUser.getId();
		List<String> roleList = shiroUser.getRoleList();
		Set<String> urlSet = new HashSet<>();
		Set<String> roleNameSet = new HashSet<>();
		for (String roleId : roleList) {
			List<Map<String, Object>> permissions = shiroFactory.findPermissionsByRoleId(userId, roleId);
			if (null != permissions) {
				for (Map<String, Object> map : permissions) {
					if (!Func.isEmpty(map.get("URL"))) {
						urlSet.add(Func.format(map.get("URL")));
					}
				}
			}
			String roleName = shiroFactory.findRoleNameByRoleId(roleId);
			roleNameSet.add(roleName);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		info.addRoles(roleNameSet);
		return info;
	}

	/**
	 * 设置认证加密方式
	 */
	@PostConstruct
	public void setCredentialMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
		credentialsMatcher.setHashIterations(ShiroKit.hashIterations);
		setCredentialsMatcher(credentialsMatcher);
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof DgUserToken;
	}
	
}
