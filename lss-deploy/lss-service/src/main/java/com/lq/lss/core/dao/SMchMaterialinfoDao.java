package com.lq.lss.core.dao;
                                                        
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.SMchMaterialinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.StockTransTotalflowDto;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-11-24 17:06:41
 */
@Repository
public class SMchMaterialinfoDao extends LssSimpleBaseDao<SMchMaterialinfo, String>{
	
	
	@SuppressWarnings("unchecked")
	public List<SMchMaterialinfo> findMchMaterialinfoList(String sDate) {
		return (List<SMchMaterialinfo>) findListByParams("findBySettleDate", sDate);
	}
	
	
	/**
	 *
	 * 按照结算日期删除数据
	 * 
	 */
	public int deleteBySetDate(String setDate) {
		return delete("deleteBySetDate",setDate);
	}
	
	

	@SuppressWarnings("unchecked")
	public List<StockTransTotalflowDto> findBeginFeeBySettleDate(String sDate) {
		return (List<StockTransTotalflowDto>) findListByParams("findBeginFeeBySettleDate", sDate);
	}
	


}