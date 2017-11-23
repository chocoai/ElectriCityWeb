package com.ikkong.core.toolbox.kit;

import java.util.UUID;

/**
 * 字符串处理
 * 
 * @author Administrator
 *
 */
public class UUIDKit {

	/**
	 * 生成去除横向的字符串
	 * 
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
