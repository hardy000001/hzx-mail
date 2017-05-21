package com.lq.lss.core.dao;
                                
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.CustomerRentinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
@Repository
public class CustomerRentinfoDao extends LssSimpleBaseDao<CustomerRentinfo, String>{

	@SuppressWarnings("unchecked")
	public List<CustomerRentinfoDto> findRentinfoById(String cuscode) {

		return (List<CustomerRentinfoDto>) findListByParams("findRentinfoById",
				cuscode);
	}
}