package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CBusPurDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public interface BusPurDetailService extends EasyUIService<CBusPurDetail, String>{

    /**
     * 查询明细
     * @param purSerialno 采购流水号
     * @return
     */
	List<CBusPurDetail> queryPurDetailListById(String purSerialno);

}