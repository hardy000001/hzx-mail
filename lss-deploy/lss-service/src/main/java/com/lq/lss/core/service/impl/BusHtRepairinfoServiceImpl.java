package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusHtRepairinfo;
import com.lq.lss.core.dao.BusHtRepairinfoDao;
import com.lq.lss.core.service.BusHtRepairinfoService;
import com.lq.lss.dto.BusHtRepairinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
@Service
public class BusHtRepairinfoServiceImpl extends EasyUIServiceBase<CBusHtRepairinfo, String, BusHtRepairinfoDao> implements BusHtRepairinfoService{

   
	@Override
	public List<BusHtRepairinfoDto> queryHtRepairinfoListById(String htcode) {
		
		return modelDao.findHtRepairinfoListById(htcode);
	}
}