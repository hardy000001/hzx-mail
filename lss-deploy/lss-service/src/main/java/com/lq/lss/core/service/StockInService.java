package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusPurDto;
import com.lq.lss.dto.CStockInRepairinfoDto;
import com.lq.lss.dto.StockInDetailDto;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.SessionUser;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 * 
 * @author Eric
 *
 */
public interface StockInService extends EasyUIService<CStockIn, String> {
	

	
	/**
	 * 入库申请
	 * 
	 * @param busPurDto
	 * @param user
	 * @return
	 */
	public ResultDto<String> saveStockInRdTx(CStockIn cStockIn,
			List<StockInDetailDto> cStockInDetails,
			List<CStockInRepairinfoDto> repairinfoDtos);
	
	/**
	 * 修改入库申请信息
	 * 
	 * @param busPurDto
	 * @param user
	 * @return
	 */
	public ResultDto<String> updateStockInRdTx(CStockIn cStockIn,
			List<StockInDetailDto> cStockInDetails,
			List<CStockInRepairinfoDto> repairinfoDtos);

 /**
  * 删除入库申请信息
  * @param 流水 inSerialno
  * @param deptId 部门id 
  * @return
  */
	public ResultDto<String> deleteStockInByIdRdTx(String inSerialno,String deptId);
	
	/**
	  * 审核入库申请信息
	  * @param  CStockIn
	  * @return
	  */
	public ResultDto<String> auditStockInByIdRdTx(CStockIn cStockIn);

	
	public Pager<CStockIn> pagerList(PageParam pageParam,CStockIn cStockIn);
	
	
	
	/**
	  * 入库反审核
	  * @param  CStockIn
	  * @return
	  */
	public ResultDto<String> antiAuditStockInByIdRdTx(CStockIn cStockIn);
	
	
	/**
	  * 审核暂存
	  * @param  CStockIn
	  * @return
	  */
	public ResultDto<String> auditStockTemporaryInByIdRdTx(CStockIn cStockIn);
	
	
	/**
	  * 反审核暂存
	  * @param  CStockIn
	  * @return
	  */
	public ResultDto<String> antiAuditStockTemporaryInByIdRdTx(CStockIn cStockIn);
	
	
}
