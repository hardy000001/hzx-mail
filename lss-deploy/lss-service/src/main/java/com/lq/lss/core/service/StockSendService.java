package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.SessionUser;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 * 
 * @author CH
 *
 */
public interface StockSendService extends EasyUIService<CStockSend, String> {
 
	
	/**
     * 发料单增加
     * @param CStockLeaseDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockSendRdTx(CStockSendDto cStockSendDto,SessionUser user);
	/**
     * 删除
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> deleteCStockSend(String sendSerialno,String deptId);
	
	/**
     * 修改
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> updateCStockSendRdTx(CStockSendDto cStockSendDto, SessionUser user);


	
	
	//审核
	public ResultDto<String> auditCStockSendByIdRdTx(CStockSendDto cStockSendDto);
	
	
	
	
	
	//反审核
		public ResultDto<String> antiAuditCStockSendByIdRdTx(CStockSendDto cStockSendDto);
}
