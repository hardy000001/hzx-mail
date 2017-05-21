package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.BConsumableInfo;

public interface ConsumableInfoService extends EasyUIService<BConsumableInfo, Integer> {

	public List<BConsumableInfo> queryConsumableInfoList(Integer typeid);

}
