package com.lq.lss.core.dao;
                                
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Repository
public class BusPurDetailDao extends LssSimpleBaseDao<CBusPurDetail, String>{

	@SuppressWarnings("unchecked")
	public List<CBusPurDetail> queryPurDetailListById(String purSerialno){
		return (List<CBusPurDetail>) findListByParams("queryPurDetailListById", purSerialno);
	}
}