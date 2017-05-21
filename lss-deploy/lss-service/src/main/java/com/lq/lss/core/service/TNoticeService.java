package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.TNoticeDto;
import com.lq.lss.model.TNotice;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-03-14 15:55:45
 */
public interface TNoticeService extends EasyUIService<TNotice, String>{

	/**
	 * 创建单据通知
	 * @param operId  操作id
	 * @param toId    接收id
	 * @param deptId   中心id
	 * @param title   标题
	 * @param orderNo 单据号
	 */
    void createNotice(Integer operId, Integer deptId, Integer toId, String title, String orderNo);
	/**
	 * 创建通知
	 * @param tNotice
	 */
    void createNotice(TNoticeDto tNoticeDto);
	/**
	 * 修改通知
	 * @param tNotice
	 */
    void updateNotice(TNoticeDto tNoticeDto);
    /**
     * 获取通知
     * @param tNoticeDto
     * @return
     */
    List<TNotice> queryNoticeList(TNoticeDto tNoticeDto);

}