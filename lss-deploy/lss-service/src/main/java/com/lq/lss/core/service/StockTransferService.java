package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;

import java.util.List;


/**
 * @author lanbo
 */
public interface StockTransferService extends EasyUIService<CStockTransfer, String> {

    /**
     * 保存调拨信息--启用事务
     *
     * @param cStockTransfer        实体类
     * @param cStockTransferDetails 明细实体类
     * @return 结果
     */
    ResultDto<String> saveStockTransferRdTx(CStockTransfer cStockTransfer, List<CStockTransferDetail> cStockTransferDetails);

    /**
     * 更新调拨单信息
     *
     * @param cStockTransfer        实体类
     * @param cStockTransferDetails 明细实体类
     * @return 结果
     */
    ResultDto<String> updateStockTransferRdTx(CStockTransfer cStockTransfer, List<CStockTransferDetail> cStockTransferDetails);

    /**
     * 删除调拨单价
     * @param transSerialNo 调拨流水号
     */
    void deleteStockTransferByIdsRdTx(String transSerialNo);
    /**
     * 审核调出
     * @param 
     * @return
     */
	public ResultDto<String> auditStockTransferOutByIdRdTx(CStockTransfer cStockTransfer);
	 /**
     * 审核调入
     * @param 
     * @return
     */
	
	public ResultDto<String> auditStockTransferInByIdRdTx(CStockTransfer cStockTransfer);
	
	 /**
     * 审核相互调拨
     * @param 
     * @return
     */
	public ResultDto<String> auditStockTransferByIdRdTx(CStockTransfer cStockTransfer);
	
	 /**
     * 内部调出反审核
     * @param 
     * @return
     */
	public ResultDto<String> antiAuditStockTransferOutByIdRdTx(CStockTransfer cStockTransfer);
	 /**
     * 内部调入反审核
     * @param 
     * @return
     */
	public ResultDto<String> antiAuditStockTransferInByIdRdTx(CStockTransfer cStockTransfer);
    

}
