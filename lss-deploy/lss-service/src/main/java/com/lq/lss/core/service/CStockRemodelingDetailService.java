package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockRemodelingDetail;

public interface CStockRemodelingDetailService extends EasyUIService<CStockRemodelingDetail, String> {

	
	
	/**
     * 查询
     * @param 流水 tsfSerialno
     * @param 
     * @return
     */
	public  List<CStockRemodelingDetail> fidnCsrddById(String remSerialNo);

}
