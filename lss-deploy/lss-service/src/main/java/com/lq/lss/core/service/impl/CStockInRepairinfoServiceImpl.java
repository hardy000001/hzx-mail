package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockInRepairinfo;
import com.lq.lss.core.dao.CStockInRepairinfoDao;
import com.lq.lss.core.service.CStockInRepairinfoService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-02-23 15:58:20
 */
@Service
public class CStockInRepairinfoServiceImpl extends EasyUIServiceBase<CStockInRepairinfo, String, CStockInRepairinfoDao> implements CStockInRepairinfoService{

    @Resource
	private CStockInRepairinfoDao cStockInRepairinfoDao;
}