package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.model.CBusHtRepairinfo;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
public interface BusHtRepairinfoService extends EasyUIService<CBusHtRepairinfo, String>{

  
	/**
     * 查询明细
     * @param htcode 合同id
     * @return
     */
	List<BusHtRepairinfoDto> queryHtRepairinfoListById(String htcode);
	

}