package com.ikkong.dg.service;

import com.ikkong.core.base.service.IService;
import com.ikkong.dg.model.UserHeadIco;

/**
 * Generated by Blade.
 * 2017-10-28 20:22:10
 */
public interface UserHeadIcoService extends IService<UserHeadIco>{

	void saveHeadIco(String imageName, String imagePath, Long userId);
	
}