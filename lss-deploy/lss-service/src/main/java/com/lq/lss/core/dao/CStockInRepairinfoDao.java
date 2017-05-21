package com.lq.lss.core.dao;
                        
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CStockInRepairinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-02-23 15:58:20
 */
@Repository
public class CStockInRepairinfoDao extends LssSimpleBaseDao<CStockInRepairinfo, String>{

	@SuppressWarnings("unchecked")
	public List<CStockInRepairinfo> queryRepairiBySerialno(
			String inSerialno) {

		return (List<CStockInRepairinfo>) findListByParams(
				"queryRepairiBySerialno", inSerialno);
	}

}