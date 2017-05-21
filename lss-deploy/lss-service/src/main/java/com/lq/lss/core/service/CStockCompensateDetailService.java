package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CompensateDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
public interface CStockCompensateDetailService extends EasyUIService<CStockCompensateDetail, String>{

  
	List<CompensateDetail> findStockCompensateDetailbyHtcode(String htcode);
	
	
	List<CStockCompensateDetailDto> findCStockCompensateDetail(String compensateSerialno);
	
	
	List<CStockCompensateDetail> findCStockCompensateDetailByIds(String compensateSerialno);

}