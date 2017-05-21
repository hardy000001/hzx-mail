package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusHtSettlement;
import com.lq.lss.core.dao.BusHtSettlementDao;
import com.lq.lss.core.service.BusHtSettlementService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 09:20:51
 */
@Service
public class BusHtSettlementServiceImpl extends EasyUIServiceBase<CBusHtSettlement, String, BusHtSettlementDao> implements BusHtSettlementService{

    @Resource
	private BusHtSettlementDao busHtSettlementDao;
}