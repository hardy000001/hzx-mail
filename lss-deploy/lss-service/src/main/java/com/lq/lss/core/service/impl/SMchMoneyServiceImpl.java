package com.lq.lss.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.SMchMoney;
import com.lq.lss.model.STransferMoney;
import com.lq.lss.core.dao.SMchMoneyDao;
import com.lq.lss.core.dao.STransferMoneyDao;
import com.lq.lss.core.service.SMchMoneyService;
import com.lq.lss.core.service.STransferMoneyService;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-12-07 14:38:12
 */
@Service
public class SMchMoneyServiceImpl extends EasyUIServiceBase<SMchMoney, String, SMchMoneyDao> implements SMchMoneyService{

    @Resource
	private SMchMoneyDao sMchMoneyDao;
}