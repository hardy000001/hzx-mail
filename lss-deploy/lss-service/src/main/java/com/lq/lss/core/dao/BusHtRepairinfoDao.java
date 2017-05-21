package com.lq.lss.core.dao;
                            
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.CBusHtRepairinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.BusHtRepairinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
@Repository
public class BusHtRepairinfoDao extends LssSimpleBaseDao<CBusHtRepairinfo, String>{

	@SuppressWarnings("unchecked")
	public List<BusHtRepairinfoDto> findHtRepairinfoListById(String htcode) {

		return (List<BusHtRepairinfoDto>) findListByParams(
				"findHtRepairinfoListById", htcode);
	}
	
}