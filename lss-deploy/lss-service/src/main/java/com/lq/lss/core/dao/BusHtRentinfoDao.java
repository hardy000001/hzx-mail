package com.lq.lss.core.dao;
                                
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CBusHtRentinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.BusHtRentinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
@Repository
public class BusHtRentinfoDao extends LssSimpleBaseDao<CBusHtRentinfo, String>{

	
	@SuppressWarnings("unchecked")
	public List<BusHtRentinfoDto> findHtRentinfoListById(String htcode) {

		return (List<BusHtRentinfoDto>) findListByParams(
				"findHtRentinfoListById", htcode);
	}
}