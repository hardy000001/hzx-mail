package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.BusHtDto;
import com.lq.lss.model.CBusHt;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26下午5:04:21
 */
public interface BusHtService extends EasyUIService<CBusHt, String>{
	
	 /**
     * 保存合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> saveBusHtRdTx(BusHtDto busHtDto);
	/**
     * 修改合同
     * @param busHtDto
     * @return
     */
	public ResultDto<String> updateBusHtRdTx(BusHtDto busHtDto);
	/**
     * 删除合同
     * @param htcode 
     * @param deptId 中心id
     * @return
     */
	public ResultDto<String> deleteBusHtById(String htcode,String deptId);
	/**
	 * 审核
	 * @param auditDto
	 */
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto);
	/**
	 * 查询合同
	 * @param 
	 */
	public CBusHt findCBusHtbyHtcode(String htcode);
	/**
	 * 查询审核后的合同
	 * @param 
	 */
	public List<CBusHt> findCBusHtList(String deptId);

}
