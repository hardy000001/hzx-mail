package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.SessionUser;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 * 
 * @author CH
 *
 */
public interface StockCenterTransferInService extends EasyUIService<CStockCenterTransfer, String> {
 
	
	/**
     * 中心租站调出保存
     * @param CStockLeaseDto
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockStockCenterTransferInRdTx(CStockCenterTransferDto stockCenterTransferDto,SessionUser user);
	/**
     * 删除
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> deleteCStockCenterTransferIn(String tsfSerialno,String deptId);
	
	/**
     * 修改
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> updateCStockCenterTransferInRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user);


	public Pager<CStockCenterTransfer> pagerList(PageParam pageParam,CStockCenterTransfer cStockCenterTransfer);
	
	
	/**
	 * 
	 * 审核
	 * @param cStockCenterTransfer
	 * @return
	 */
	public ResultDto<String> auditStockCenterTransferInByIdRdTx(CStockCenterTransferDto stockCenterTransferDto);

	/**
	 * 
	 * 反审核
	 * @param cStockCenterTransfer
	 * @return
	 */
	public ResultDto<String> antiAuditStockCenterTransferInByIdRdTx(CStockCenterTransferDto stockCenterTransferDto);
}
