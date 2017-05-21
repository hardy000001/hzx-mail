package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusHtRentinfo;
import com.lq.lss.core.dao.BusHtRentinfoDao;
import com.lq.lss.core.service.BusHtRentinfoService;
import com.lq.lss.dto.BusHtRentinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
@Service
public class BusHtRentinfoServiceImpl extends EasyUIServiceBase<CBusHtRentinfo, String, BusHtRentinfoDao> implements BusHtRentinfoService{


	@Override
	public List<BusHtRentinfoDto> queryHtRentinfoListById(String htcode) {
		
		return modelDao.findHtRentinfoListById(htcode);
	}

	@Override
	public void batchAdd(List<CBusHtRentinfo> busHtRentinfos) {
		modelDao.batchCreate(busHtRentinfos);
	}
}