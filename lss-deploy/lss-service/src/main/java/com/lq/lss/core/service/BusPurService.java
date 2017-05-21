package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusPurDto;
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.SessionUser;

/**
 * 采购管理接口
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public interface BusPurService extends EasyUIService<CBusPur, String>{

    /**
     * 保存采购申请
     * @param busPurDto
     * @param user
     * @return
     */
	public ResultDto<String> savePurApplyRdTx(BusPurDto busPurDto,SessionUser user);
	/**
     * 修改采购信息
     * @param busPurDto
     * @param user
     * @return
     */
	public ResultDto<String> updatePurInfoRdTx(BusPurDto busPurDto,SessionUser user);
	/**
     * 删除采购信息
     * @param 流水 purSerialno
     * @param deptId 部门id 
     * @return
     */
	public ResultDto<String> deletePurInfoById(String purSerialno,String deptId);
	/**
	 * 审核
	 * @param busPurDto
	 * @param user
	 */
	public ResultDto<String>  auditInfoRdTx(BusPurDto busPurDto,SessionUser user);

}