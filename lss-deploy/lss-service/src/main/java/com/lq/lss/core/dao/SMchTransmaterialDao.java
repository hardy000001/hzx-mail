package com.lq.lss.core.dao;
                                                            
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.SMchRentmaterial;
import com.lq.lss.model.SMchTransmaterial;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: eric
 * @date 创建时间: 2017-04-20 13:55:11
 */
@Repository
public class SMchTransmaterialDao extends LssSimpleBaseDao<SMchTransmaterial, String>{

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
	public List<SMchTransmaterial> findMchMaterialinfoList(String sDate) {
		return (List<SMchTransmaterial>) findListByParams("findBySettleDate", sDate);
	}


}