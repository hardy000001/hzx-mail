package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.model.CStockOut;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
public interface StockOutService extends EasyUIService<CStockOut, String>{

	/**
     * 保存出库申请
     * @param stockOutDto
     * @return
     */
	public ResultDto<String> saveStockOutApplyRdTx(StockOutDto stockOutDto);
	/**
     * 修改出库信息
     * @param busSaleDto
     * @return
     */
	public ResultDto<String> updateStockOutInfoRdTx(StockOutDto stockOutDto);
	/**
     * 删除销售信息
     * @param 出库流水 outSerialno
     * @return
     */
	public ResultDto<String> deleteStockOutInfoById(String outSerialno,String deptId);
	/**
	 * 审核
	 * @param stockOutDto
	 */
	public ResultDto<String> auditInfoRdTx(StockOutDto stockOutDto);
	/**
	 * 反审核
	 * @param stockOutDto
	 */
	public ResultDto<String> antiAuditInfoRdTx(StockOutDto stockOutDto);
	
	/**
	 * 审核提暂存
	 * @param stockOutDto
	 */
	public ResultDto<String> audiStockTemporaryOutRdTx(StockOutDto stockOutDto);
	/**
	 * 反审核提暂存
	 * @param stockOutDto
	 */
	public ResultDto<String> antiAudiStockTemporaryOutRdTx(StockOutDto stockOutDto);

}