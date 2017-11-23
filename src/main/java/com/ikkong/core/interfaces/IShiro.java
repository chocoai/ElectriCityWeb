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
package com.ikkong.core.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.ikkong.common.vo.ShiroUser;
import com.ikkong.common.vo.User;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.model.vo.ShiroDgUser;

/**
 * 定义shirorealm所需数据的接口
 *
 */
public interface IShiro {
	//----------------------------------
	//-------系统管理用户-----------------
	//----------------------------------
	User user(String account) throws AuthenticationException;

	ShiroUser shiroUser(User user);

	List<Map<String, Object>> findPermissionsByRoleId(Object userId, String roleId);

	String findRoleNameByRoleId(String roleId);
	
	SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);
	
	//----------------------------------
	//-------电工用户--------------------
	//----------------------------------
	
	DgUser dgUser(String phoneNo)  throws AuthenticationException;
	
	ShiroDgUser shiroDgUser(DgUser user);
	
	SimpleAuthenticationInfo dgUserInfo(ShiroDgUser shiroUser, DgUser user, String realmName);
}
