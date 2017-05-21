package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.SessionUser;


public interface StockTemporaryService extends EasyUIService<CStockTemporary, String>{
	/**
     * 暂存单保存
     * @param cstockRemodelingDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockTemporaryRdTx(CStockTemporaryDto cStockTemporaryDto,SessionUser user);
	/**
     * 删除暂存单
     * @param 流水 temSerialno
     * @return
     */
	public ResultDto<String> deleteCstpBytemSerialno(String temSerialno,String deptid);
	
	/**
     * 修改暂存单
     * @param 流水 temSerialno
     * @return
     */
	public ResultDto<String> updateTemporaryRdTx(CStockTemporaryDto cStockTemporaryDto, SessionUser user);
	

	/**
     * 审核
     * @param 
     * @return
     */
	public ResultDto<String> auditStockTemporaryByIdRdTx(CStockTemporaryDto cStockTemporaryDto);
	
	
	
	public CStockTemporary findTemporary(String temSerialno);
}
