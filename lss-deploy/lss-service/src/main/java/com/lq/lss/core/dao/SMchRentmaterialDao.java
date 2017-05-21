package com.lq.lss.core.dao;
                                                    
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.SMchMaterialinfo;
import com.lq.lss.model.SMchRentmaterial;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.StockTransTotalflowDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-01 14:54:23
 */
@Repository
public class SMchRentmaterialDao extends LssSimpleBaseDao<SMchRentmaterial, String>{
	
	
	/**
	 *
	 * 按照结算日期删除数据
	 * 
	 */
	public int deleteBySetDate(String setDate) {
		return delete("deleteBySetDate",setDate);
	}
	/**
	 *
	 * 按照结算日期获取期初期末数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SMchRentmaterial> findMchMaterialinfoList(String sDate) {
		return (List<SMchRentmaterial>) findListByParams("findBySettleDate", sDate);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StockTransTotalflowDto> findBeginFeeBySettleDate(String sDate) {
		return (List<StockTransTotalflowDto>) findListByParams("findBeginFeeBySettleDate", sDate);
	}
	

	


}