package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockCompensateDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.CStockCompensate;
import com.lq.lss.model.SessionUser;

/**
 *
 * @author  作者: ch
 * @date 创建时间: 2016-12-28 15:32:42
 */
public interface CStockCompensateService extends EasyUIService<CStockCompensate, String>{

  
	
	/**
     * 赔偿单增加
     * @param 
     * @param user
     * @return
     */
	public ResultDto<String> saveCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto,SessionUser user);
	/**
     * 删除
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> deleteCStockCompensate(String compensateSerialno,String deptId);
	
	/**
     * 修改
     * @param 
     * @param 
     * @return
     */
	public ResultDto<String> updateCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto, SessionUser user);


	
	
	//审核
	public ResultDto<String> auditCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto);
}