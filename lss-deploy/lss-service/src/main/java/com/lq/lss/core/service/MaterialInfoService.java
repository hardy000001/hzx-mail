package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.BMaterialInfo;

public interface MaterialInfoService extends EasyUIService<BMaterialInfo, Integer> {
 
	
	public List<BMaterialInfo> queryMaInfoList(Integer typeid);
	
	
	public void bachcreatMerTest();
	
	
	

}
