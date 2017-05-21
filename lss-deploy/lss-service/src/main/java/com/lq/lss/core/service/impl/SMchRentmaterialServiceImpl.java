package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.SMchRentmaterial;
import com.lq.lss.core.dao.SMchRentmaterialDao;
import com.lq.lss.core.service.SMchRentmaterialService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-01 14:54:23
 */
@Service
public class SMchRentmaterialServiceImpl extends EasyUIServiceBase<SMchRentmaterial, String, SMchRentmaterialDao> implements SMchRentmaterialService{

    @Resource
	private SMchRentmaterialDao sMchRentmaterialDao;
}