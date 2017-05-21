package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.StockReceiptDto;
import com.lq.lss.model.CStockReceipt;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
public interface StockReceiptService extends EasyUIService<CStockReceipt, String>{

  
	/**
     * 保存收料申请
     * @param stockReceiptDto
     * @return
     */
	public ResultDto<String> saveStockReceiptApplyRdTx(StockReceiptDto stockReceiptDto);
	/**
     * 修改收料信息
     * @param stockReceiptDto
     * @return
     */
	public ResultDto<String> updateStockReceiptRdTx(StockReceiptDto stockReceiptDto);
	/**
	 * 删除收料信息
	 * @param receiptSerialno
	 * @param deptId 中心id
	 * @return
	 */
	public ResultDto<String> deleteStockReceiptById(String receiptSerialno,String deptId);
	/**
	 * 审核
	 * @param auditDto
	 */
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto);
	/**
	 * 反审核
	 * @param auditDto
	 */
	public ResultDto<String> antiAuditInfoRdTx(AuditDto auditDto);

}