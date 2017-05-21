package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.dto.CBusAccountDto;
import com.lq.lss.model.CBusAccount;
import com.lq.lss.model.SessionUser;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 09:56:42
 */
public interface BusAccountService extends EasyUIService<CBusAccount, String>{

  
	/**
     * 保存报停合同
     * @param busHtStopflowDto
     * @return
     */
	public ResultDto<String> saveBusAccountRdTx(CBusAccountDto cBusAccountDto, SessionUser user);
	/**
     * 修改报停合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> updateBusAccountRdTx(CBusAccountDto cBusAccountDto);
	/**
     * 删除报停合同
     * @param htcode 
     * @param deptId 中心id
     * @return
     */
	public ResultDto<String> deleteBusAccountById(String id,String deptId);
	/**
     * 修改报停合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> auditBusAccountRdTx(CBusAccountDto cBusAccountDto, SessionUser user);
	

}