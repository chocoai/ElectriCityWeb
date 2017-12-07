package com.ikkong.common.utils;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ikkong.common.constant.SmsTempConts;
import com.ikkong.core.sms.model.SendSmsRequest;
import com.ikkong.core.sms.model.SendSmsResponse;

/**
 * 短信发送类
 * 
 * @author sparker
 *
 */
public class SmsSender {
	
	//产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIoQC9iho01vXd";
    static final String accessKeySecret = "KXw8x9IxYi6EMXVbqhD2bmEoqZd4Vo";
    
    // 短信签名
    static final String SIGN_NAME = "奥博华";
        
    private static IAcsClient acsClient = null;
    
    static {
    	//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = null;
        try {
        	profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			acsClient = new DefaultAcsClient(profile);
		} catch (ClientException e) {
			e.printStackTrace();
		}
    }
    
    private static SendSmsRequest getSmsReq() {
    	//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGN_NAME);
        return request;
    }
    
    /**
     * 注册验证码发送
     * 
     * @param phoneNo
     * @param vcode
     * @param backCode
     * @return
     */
	public static SendSmsResponse registSms(String phoneNo, String vcode, String backCode) {
		return sendSms(SmsTempConts.USER_REGIST_TMP, phoneNo, vcode, backCode);
	}	
	
	/**
	 * 找回密码验证码
	 * 
	 * @param phoneNo
	 * @param vcode
	 * @param backCode
	 * @return
	 */
	public static SendSmsResponse findPwdSms(String phoneNo, String vcode, String backCode) {
		return sendSms(SmsTempConts.MODIFY_PWD_TMP, phoneNo, vcode, backCode);
	}
	
	/**
	 * 短信发送公共调用方法
	 * 
	 * @param template
	 * @param phoneNo
	 * @param vcode
	 * @param backCode
	 * @return
	 * @throws ClientException
	 */
	private static SendSmsResponse sendSms(String template, String phoneNo, String vcode, String backCode) {
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = getSmsReq();

		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNo);

		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(template);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		if(vcode != null) {
			JSONObject json = new JSONObject();
			json.put("code", vcode);
			request.setTemplateParam(json.toJSONString());
		}

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		if(backCode != null) {
			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			request.setOutId(backCode);
		}

		SendSmsResponse sendSmsResponse = null;
		// hint 此处可能会抛出异常，注意catch
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return sendSmsResponse;
	}

	/**
	 * 生成短信随机码
	 * 
	 * @return
	 */
	public static String genVCode() {
		Long val = Math.abs(new Random().nextLong());
		return StringUtils.left(String.valueOf(val), 6);
	}
	
	public static String digestCode(String phoneNo, Long timeMills) {
		String[] array = {"Gcs9&3","Sn#6xQ","$Y0t9x","pW%G9r"};
		String[] newArray = ArrayUtils.insert(1, array, phoneNo);
		String[] digestArray = ArrayUtils.insert(3, newArray, String.valueOf(timeMills));
		return DigestUtils.md5Hex(StringUtils.join(digestArray, "@"));
	}
	
	/**
	 * 根据模板和手机号串发送
	 * 
	 * @param template
	 * @param phoneNos
	 * @return
	 */
	public static SendSmsResponse sendTipSms(String template, String phoneNos) {
		return sendSms(template, phoneNos, null, null);
	}
}
