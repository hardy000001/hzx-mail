package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockRemodelingDto;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.SessionUser;

public interface CStockRemodelingService extends EasyUIService<CStockRemodeling, String>{
	/**
     * 保存加工改制单
     * @param cstockRemodelingDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockRemodelingRdTx(CStockRemodelingDto cstockRemodelingDto,SessionUser user);
	
	/**
     * 删除加工改制单
     * @param 流水 remSerialNo
     * @return
     */
	public ResultDto<String> deleteCsrdlById(String remSerialNo,String deptId);
	
	/**
     * 查询加工改制
     * @param 流水 remSerialNo
     * @return
     */
	public CStockRemodeling findbyRemSerialNo(String remSerialNo);
	
	/**
     * 保存加工改制单
     * @param cstockRemodelingDto
     * @param user
     * @return
     */
	public ResultDto<String> updatePurInfoRdTx(CStockRemodelingDto cstockRemodelingDto,SessionUser user);
	/**
     * 审核加工改制单
     * @param cstockRemodelingDto
     * @param user
     * @return
     */
	public ResultDto<String> auditStockRemodelingByIdRdTx(CStockRemodelingDto cStockRemodelingDto);

}
