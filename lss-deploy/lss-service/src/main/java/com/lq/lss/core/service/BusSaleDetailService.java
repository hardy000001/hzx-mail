package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CBusSaleDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public interface BusSaleDetailService extends EasyUIService<CBusSaleDetail, String>{

    /**
     * 查询明细
     * @param saleSerialno 销售流水号
     * @return
     */
	List<CBusSaleDetail> querySaleDetailListById(String saleSerialno);

}