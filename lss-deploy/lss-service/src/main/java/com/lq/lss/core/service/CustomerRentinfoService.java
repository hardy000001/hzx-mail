package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CustomerRentinfoDto;
import com.lq.lss.model.CustomerRentinfo;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
public interface CustomerRentinfoService extends EasyUIService<CustomerRentinfo, String>{

    /**
     * 通过客户id查询租价信息
     * @param cuscode
     * @return
     */
	List<CustomerRentinfoDto> queryRentinfoById(String cuscode);

}