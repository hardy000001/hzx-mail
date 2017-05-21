package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.BusSaleDto;
import com.lq.lss.model.CBusSale;

/**
 * 销售管理接口
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public interface BusSaleService extends EasyUIService<CBusSale, String>{

  
	 /**
     * 保存销售申请
     * @param busSaleDto
     * @return
     */
	public ResultDto<String> saveSaleApplyRdTx(BusSaleDto busSaleDto);
	/**
     * 修改采购信息
     * @param busSaleDto
     * @return
     */
	public ResultDto<String> updateSaleInfoRdTx(BusSaleDto busSaleDto);
	/**
     * 删除销售信息
     * @param 流水 saleSerialno
     * @param deptId 部门id 
     * @return
     */
	public ResultDto<String> deleteSaleInfoById(String saleSerialno,String deptId);
	/**
	 * 审核
	 * @param busSaleDto
	 */
	public ResultDto<String> auditInfoRdTx(BusSaleDto busSaleDto);

}