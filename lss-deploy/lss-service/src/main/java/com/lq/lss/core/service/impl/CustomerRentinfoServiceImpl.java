package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.core.dao.CustomerRentinfoDao;
import com.lq.lss.core.service.CustomerRentinfoService;
import com.lq.lss.dto.CustomerRentinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
@Service
public class CustomerRentinfoServiceImpl extends EasyUIServiceBase<CustomerRentinfo, String, CustomerRentinfoDao> implements CustomerRentinfoService{

	@Override
	public List<CustomerRentinfoDto> queryRentinfoById(String cuscode) {
		
		return modelDao.findRentinfoById(cuscode);
	}

   
}