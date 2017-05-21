package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.model.CBusHtStopflow;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.SessionUser;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
public interface BusHtStopflowService extends EasyUIService<CBusHtStopflow, String>{

  
	 /**
     * 保存报停合同
     * @param busHtStopflowDto
     * @return
     */
	public ResultDto<String> saveHtStopflowRdTx(BusHtStopflowDto busHtStopflowDto, SessionUser user);
	/**
     * 修改报停合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> updateHtStopflowRdTx(BusHtStopflowDto busHtStopflowDto);
	/**
     * 删除报停合同
     * @param htcode 
     * @param deptId 中心id
     * @return
     */
	public ResultDto<String> deleteHtStopflowById(String id,String deptId);
	/**
     * 修改报停合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> auditHtStopflowRdTx(BusHtStopflowDto busHtStopflowDto, SessionUser user);
	

}