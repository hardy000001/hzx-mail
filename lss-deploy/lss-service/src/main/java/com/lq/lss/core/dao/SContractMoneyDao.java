package com.lq.lss.core.dao;
                                                                    
import org.springframework.stereotype.Repository;

import com.lq.lss.model.SContractMoney;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-11-24 17:06:41
 */
@Repository
public class SContractMoneyDao extends LssSimpleBaseDao<SContractMoney, String>{

	/**
	 *
	 * 按照结算日期删除数据
	 * 
	 */
	public int deleteBySetDate(String settleMonth) {
		return delete("deleteBySetDate",settleMonth);
	}


}