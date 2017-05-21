package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.SMchMaterialinfo;
import com.lq.lss.core.dao.SMchMaterialinfoDao;
import com.lq.lss.core.service.SMchMaterialinfoService;

/**
 *
 * @author  作者: eric
 * @date 创建时间: 2016-11-24 17:06:41
 */
@Service
public class SMchMaterialinfoServiceImpl extends EasyUIServiceBase<SMchMaterialinfo, String, SMchMaterialinfoDao> implements SMchMaterialinfoService{

    @Resource
	private SMchMaterialinfoDao sMchMaterialinfoDao;
    
    
    
}