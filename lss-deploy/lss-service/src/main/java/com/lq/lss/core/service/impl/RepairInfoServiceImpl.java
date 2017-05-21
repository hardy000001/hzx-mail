package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BRepairInfoDao;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.model.BRepairInfo;

@Service
public class RepairInfoServiceImpl extends EasyUIServiceBase<BRepairInfo, Integer, BRepairInfoDao> implements RepairInfoService {

	@Override
	public List<BRepairInfo> queryRepairInfoByTypeid(String typeid) {
		
		return modelDao.findRepairInfoByTypeid(typeid);
	}

	


}
