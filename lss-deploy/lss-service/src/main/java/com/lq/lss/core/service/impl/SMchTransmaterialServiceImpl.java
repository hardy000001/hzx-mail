package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.SMchTransmaterial;
import com.lq.lss.core.dao.SMchTransmaterialDao;
import com.lq.lss.core.service.SMchTransmaterialService;

/**
 *
 * @author  作者: eric
 * @date 创建时间: 2017-04-20 13:55:11
 */
@Service
public class SMchTransmaterialServiceImpl extends EasyUIServiceBase<SMchTransmaterial, String, SMchTransmaterialDao> implements SMchTransmaterialService{

    @Resource
	private SMchTransmaterialDao sMchTransmaterialDao;
    
    
    
    
}