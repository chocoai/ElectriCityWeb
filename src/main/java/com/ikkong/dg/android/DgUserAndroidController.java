package com.ikkong.dg.android;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.common.vo.LoginLog;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.constant.CommConts;
import com.ikkong.core.constant.ConstCache;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.jfinal.ext.kit.JLogKit;
import com.ikkong.core.jfinal.ext.shiro.ClearShiro;
import com.ikkong.core.jfinal.ext.shiro.ShiroKit;
import com.ikkong.core.model.AjaxResult;
import com.ikkong.core.shiro.DgUserToken;
import com.ikkong.core.toolbox.Func;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.kit.UUIDKit;
import com.ikkong.core.toolbox.log.LogManager;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.model.vo.DgUserInfo;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.UserHeadIcoService;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.dg.service.impl.UserHeadIcoServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.ehcache.CacheKit;

public class DgUserAndroidController extends BaseController {

	private DgUserService userServ = new DgUserServiceImpl();
	private UserHeadIcoService headIcoServ = new UserHeadIcoServiceImpl();
	
	private static final String headBasePath = PropKit.use("config.properties").get("image.headPath");

	@ClearShiro
	@Before(POST.class)
	public void anLogin() {
		String tokenId = getPara(CommConts.TOKEN_KEY);
		AjaxResult resVal = loginValidate();
		if (StringUtils.isNotBlank(tokenId)) {
			DgUserToken cacheToken = CacheKit.get(ConstCache.CLIENT_CACHE, tokenId);
			// cacheToken.getLoginTime() 判断登录时长是否过期
			cacheToken.setRememberMe(true);
			Subject currentUser = ShiroKit.getSubject();
			currentUser.login(cacheToken);
			cacheToken.setUserId(ShiroKit.getDgUser().getId());
			cacheToken.setLoginTime(new Date());// 更新登录时间
			CacheKit.put(ConstCache.CLIENT_CACHE, tokenId, cacheToken);
			AjaxResult res = success("您当前已是登录状态!");
			res.setData(tokenId);
			return;
		}

		if (resVal.isSuccess()) {
			String account = getPara("phoneNo");
			String password = getPara("password");
			String captCode = getPara("imgCode");// 验证码

			Subject currentUser = ShiroKit.getSubject();
			DgUserToken token = new DgUserToken(account, password.toCharArray());
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				token.setUserId(ShiroKit.getDgUser().getId());
				token.setLoginTime(new Date());
				Session session = ShiroKit.getSession();
				// session 信息放Ehcache 缓存
				CacheKit.put(ConstCache.CLIENT_CACHE, session.getId(), token);
				resVal.setData(session.getId());
				JLogKit.println("手机客户端sessionID	: {} ", session.getId());
				JLogKit.println("手机客户端sessionHost	: {}", session.getHost());
				JLogKit.println("手机客户端sessionTimeOut	: {}", session.getTimeout());
			} catch (UnknownAccountException e) {
				LogKit.error("账号不存在：{}", e);
				renderJson(error("账号不存在"));
				return;
			} catch (DisabledAccountException e) {
				LogKit.error("账号未启用：{}", e);
				renderJson(error("账号未启用"));
				return;
			} catch (IncorrectCredentialsException e) {
				LogKit.error("密码错误：{}", e);
				renderJson(error("密码错误"));
				return;
			} catch (AuthenticationException e) {
				LogKit.error("账户名或密码不匹配：{}", e);
				renderJson(error("账户名或密码不匹配!"));
				return;
			} catch (RuntimeException e) {
				LogKit.error("未知错误,请联系管理员：{}", e);
				renderJson(error(e.getMessage()));
				return;
			}
			doClientLog(ShiroKit.getSession(), "登录");
		}
		renderJson(resVal);
	}

	@ClearShiro
	@Before(POST.class)
	public void anRegister() {
		AjaxResult resVal = regValidate();
		if (resVal.isSuccess()) {
			String registerNo = getPara("phoneNo");
			String password = getPara("password");
			String smsCode = getPara("smsCode");
			String refereeNo = getPara("refereeNo");

			DgUser dgUser = new DgUser();
			dgUser.setPhoneno(registerNo);
			// intro_id 推荐人id
			if (StringUtils.isNotBlank(refereeNo)) {
				DgUser refer = getByPhoneNo(refereeNo);
				dgUser.setIntro_id(refer != null ? refer.getId() : null);
			}
			dgUser.setStatus(CommonConts.UserStatus.NORMAL.getStatusVal());
			dgUser.setVersion(1);
			String salt = ShiroKit.getRandomSalt(5);
			String pwdMd5 = ShiroKit.md5(password, salt);
			dgUser.setUserpwd(pwdMd5);
			dgUser.setSalt(salt);
			dgUser.setName(registerNo);
			dgUser.setRegister_time(new Date());
			try {
				boolean saveStatus = Blade.create(DgUser.class).save(dgUser);
				if (saveStatus)
					LogKit.info("【" + registerNo + "】注册成功！");
			} catch (Exception e) {
				resVal = error("注册失败,服务端保存异常!");
				LogKit.error("注册失败,服务端保存异常：{}", e);
			}
		}
		renderJson(resVal);
	}
		
	private DgUser getByPhoneNo(String phoneNo) {
		return userServ.findFirstBy("phoneno = #{phoneNo}", Record.create().set("phoneNo", phoneNo));
	}	

	@Before(POST.class)
	public void uploadImg() {
		String filename = "";
		DgUserToken cacheToken = dgUserToken();
	    try{
	        DiskFileItemFactory dff = new DiskFileItemFactory();
	        ServletFileUpload sfu = new ServletFileUpload(dff);
	        List<FileItem> items = sfu.parseRequest(getRequest());
	        if(items != null && !items.isEmpty()){
	        	FileItem item = items.get(0);
	        	// 更改文件名为唯一的
                filename = item.getName();
                if (filename != null) {
                    filename = UUIDKit.uuid() + "." + FilenameUtils.getExtension(filename);
                }
                // 生成存储路径
                File file = new File(headBasePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                item.write(new File(headBasePath + filename));
                headIcoServ.saveHeadIco(filename, "", cacheToken.getUserId());
                
                DgUser dgUser = userServ.findById(cacheToken.getUserId());
                dgUser.setHead_url(filename);
                dgUser.setVersion(dgUser.getVersion()+1);
                userServ.update(dgUser);	
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        renderJson(error("头像上传失败!"));
	    } finally {
	    	AjaxResult json =  success("头像上传成功!");
            json.setData(PropKit.get("access.head")+filename);
            renderJson(json);
	    }
	}
	
	@Before(POST.class)
	public void modify() {// 用户信息修改
		String name = getPara("name");
		Integer gender = getParaToInt("gender");
		String code = getPara("code");
		String address = getPara("address");
//		String headUrl = getPara("headUrl");
		String idRegx = "[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]";

		if (StringUtils.isNotBlank(code) && !code.matches(idRegx)) {
			renderJson(error("身份证号格式不正确!"));
			return;
		}
		if (gender != null && gender != 0 && gender != 1) {
			renderJson(error("性别填写值必须是0或1"));
			return;
		}
		DgUserToken token = dgUserToken();
		DgUser dgUser = userServ.findById(token.getUserId());
		dgUser.setName(name);
		dgUser.setAddress(address);
		dgUser.setCode(code);
		dgUser.setGender(gender);
//		dgUser.setHead_url(headUrl);
		dgUser.setVersion(dgUser.getVersion() + 1);

		try {
			if (userServ.update(dgUser)) {
				renderJson(success("资料修改成功！"));
				LogKit.info("资料修改成功！");
			}
		} catch (Exception e) {
			LogKit.error("资料修改失败,服务端保存异常：{}", e);
			renderJson(error("资料修改失败！"));
		}
	}

	@Before(POST.class)
	public void userInfo() {// 用户信息
		DgUserToken token = dgUserToken();
		if (token != null) {
			DgUserInfo user = userServ.findByUserId(token.getUserId());
			renderJson(user);
		} else {
			renderJson(error("用户信息获取失败！"));
		}
	}

	public void modifyPwd() {//修改密码
		String newer = getPara("newPwd");
		String vcode = getPara("vcode");
		
		if("1234".equals(vcode)){
			if(StringUtils.isBlank(newer)){
				renderJson(warn("新密码不能为空!"));
				return;
			}
			DgUserToken token = dgUserToken();
			DgUser dgUser = userServ.findById(token.getUserId());
			String salt = dgUser.getSalt();
			dgUser.setUserpwd(ShiroKit.md5(newer, salt));
			dgUser.setVersion(dgUser.getVersion()+1);
			try {
				if(userServ.update(dgUser)) {
					renderJson(success("密码修改成功!"));
				} else {
					renderJson(fail("密码修改失败!"));
				}
				return;
			} catch (Exception e) {
				e.printStackTrace();
				renderJson(error("服务端处理异常!"));
				return;
			}
		} else {
			renderJson(warn("验证码不正确!"));
		}		
	}

	public void changeImage() throws IOException {
		
		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dff);
			List<FileItem> items = sfu.parseRequest(getRequest());
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通表单
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					System.out.println("name=" + fieldName + ", value=" + fieldValue);
				} else {// 获取上传字段
					// 更改文件名为唯一的
					String filename = item.getName();
					if (filename != null) {
						filename = UUIDKit.uuid() + "." + FilenameUtils.getExtension(filename);
					}
					// 生成存储路径
					File file = new File(headBasePath);
					if (!file.exists()) {
						file.mkdir();
					}
					// 处理文件的上传
					try {
						item.write(new File(headBasePath + filename));
						System.out.println("filePath=" + headBasePath + filename);
					} catch (Exception e) {
						renderJson(error("头像上传失败!"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("头像上传失败!"));
		} finally {
			renderJson(success("头像变更成功!"));
		}
	}

	private AjaxResult loginValidate() {
		String phoneNo = getPara("phoneNo");
		String password = getPara("password");
		String captCode = getPara("imgCode");
		if (StringUtils.isBlank(phoneNo)) {
			return error("登录帐号不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			return error("密码不能为空!");
		}
		// if(StringUtils.isBlank(captCode)){
		// return error("验证码不能为空!");
		// }

		return success("登录成功!");
	}

	private AjaxResult regValidate() {
		String registerNo = getPara("phoneNo");
		String password = getPara("password");
		String smsCode = getPara("smsCode");
		String refereeNo = getPara("refereeNo");

		if (StringUtils.isBlank(registerNo)) {
			return error("手机号不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			return error("密码不能为空!");
		}
		if (StringUtils.isBlank(smsCode)) {
			return error("短信验证码不能为空!");
		} else if (!"1234".equals(smsCode)) {
			return error("短信验证码不正确!");
		}
		if (StringUtils.isNotBlank(registerNo)) {
			if (!registerNo.matches(CommonConts.PhoneRegex)) {
				return error("手机号不正确!");
			}
		}
		if (StringUtils.isNotBlank(refereeNo) && !refereeNo.matches(CommonConts.PhoneRegex)) {
			return error("推荐人手机号不正确!");
		}
		DgUser regUser = getByPhoneNo(registerNo);
		if(regUser != null){
			return error("当前手机号已被注册!");
		}

		return success("恭喜,注册成功!");
	}

	public void doClientLog(Session session, String type) {
		if (!LogManager.isDoLog()) {
			return;
		}
		try {
			LoginLog log = new LoginLog();
			String msg = Func.format("[sessionID]: {} [sessionHost]: {} [sessionHost]: {}", session.getId(),
					session.getHost(), session.getTimeout());
			log.setLogname(type);
			log.setMethod(msg);
			log.setCreatetime(new Date());
			log.setSucceed("1");
			log.setUserid(Func.format(ShiroKit.getDgUser().getId()));
			Blade.create(LoginLog.class).save(log);
		} catch (Exception ex) {
			LogKit.logNothing(ex);
		}
	}
}
