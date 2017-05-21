package com.lq.lss.core.dao;
                                
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Repository
public class BusSaleDetailDao extends LssSimpleBaseDao<CBusSaleDetail, String>{

	@SuppressWarnings("unchecked")
	public List<CBusSaleDetail> queryDetailListById(String saleSerialno){
		return (List<CBusSaleDetail>) findListByParams("queryDetailListById", saleSerialno);
	}
}