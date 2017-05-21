package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CBusHt;
/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26下午5:05:16
 */
@Repository
public class BusHtDao extends LssSimpleBaseDao<CBusHt, String>{
	@SuppressWarnings("unchecked")
	public List<CBusHt> findCBusHtList(String deptid) {
		return (List<CBusHt>) findListByParams("findCBusHtList", deptid);
	}
}
