package com.lq.lss.core.dao;
                                                                    
import org.springframework.stereotype.Repository;

import com.lq.lss.model.STransferMoney;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-07 14:38:12
 */
@Repository
public class STransferMoneyDao extends LssSimpleBaseDao<STransferMoney, String>{

	
	
	/**
	 *
	 * 按照结算日期删除数据
	 * 
	 */
	public int deleteBySetDate(String settleMonth) {
		return delete("deleteBySetDate",settleMonth);
	}

}