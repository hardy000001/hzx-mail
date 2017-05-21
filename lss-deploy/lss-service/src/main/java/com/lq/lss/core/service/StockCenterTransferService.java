package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.SessionUser;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 * 
 * @author CH
 *
 */
public interface StockCenterTransferService extends EasyUIService<CStockCenterTransfer, String> {
 
	
	/**
     * 中心租站调出保存
     * @param CStockLeaseDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockStockCenterTransferRdTx(CStockCenterTransferDto stockCenterTransferDto,SessionUser user);
	/**
     * 删除
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> deleteCStockCenterTransfer(String tsfSerialno,String deptId);
	
	/**
     * 修改
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> updateCStockCenterTransferRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user);


	public Pager<CStockCenterTransfer> pagerList(PageParam pageParam,CStockCenterTransfer cStockCenterTransfer);
	
	//审核
	public ResultDto<String> auditStockCenterTransferByIdRdTx(CStockCenterTransferDto stockCenterTransferDto);
	
	
	public CStockCenterTransfer queryStockCenterTransfer(String tsfSerialno);
	
	/**
     * f 反审核
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> antiAuditStockCenterTransferByIdRdTx(CStockCenterTransferDto stockCenterTransferDto);
		
	
}
