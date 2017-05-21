package com.lq.lss.core.dao;
                            
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CompensateDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Repository
public class CStockCompensateDetailDao extends LssSimpleBaseDao<CStockCompensateDetail, String>{

	public List<CompensateDetail> findStockCompensateDetailbyHtcode(String htcode) {
		// TODO Auto-generated method stub
		return (List<CompensateDetail>)findListByParams("findByHtcode",htcode);
	}


	
	public List<CStockCompensateDetailDto> findCStockCompensateDetail(String compensateSerialno) {
		// TODO Auto-generated method stub
		return (List<CStockCompensateDetailDto>)findListByParams("findByCompensateSerialno",compensateSerialno);
	}
	
	
	public List<CStockCompensateDetail> findCStockCompensateDetailByIds(String compensateSerialno) {
		// TODO Auto-generated method stub
		return (List<CStockCompensateDetail>)findListByParams("findById",compensateSerialno);
	}
}