package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.SessionUser;

/**
 * 
 * @author CH
 *
 */
public interface StockLeaseService extends EasyUIService<CStockLease, String> {
 
	
	/**
     * 中心租站调拨保存
     * @param CStockLeaseDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockLeaseRdTx(CStockLeaseDto cStockLeaseDto,SessionUser user);
	/**
     * 删除
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> deleteCStockLease(String lsSerialno);
	
	/**
     * 修改
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> updateTemporaryRdTx(CStockLeaseDto cStockLeaseDto, SessionUser user);
}
