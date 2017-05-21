package com.lq.lss.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.TNotice;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.TNoticeDao;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.TNoticeDto;
import com.lq.lss.enums.NoticeStatus;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-03-14 15:55:45
 */
@Service
public class TNoticeServiceImpl extends EasyUIServiceBase<TNotice, String, TNoticeDao> implements TNoticeService{


	@Override
	public void createNotice(Integer operId,Integer deptId, Integer toId, String title,
			String orderNo) {
		
		try {
			String notice = NoticeConst.NOTICE_CONTENT_TEMPLATE.replace(
				     "XXXX", orderNo);
			TNotice t=new TNotice();
				t.setFromoper(operId);
				t.setTooper(toId);
				t.setDeptid(deptId);
				t.setNtitle(title);
				t.setNotice(notice);
				t.setOrderno(orderNo);
				t.setCreatetime(new Date());
				t.setCreateoper(operId);
				t.setStatus(NoticeStatus.UNREAD.value()+"");
				t.setAdminstatus(NoticeStatus.UNREAD.value()+"");
				t.setType(NoticeStatus.INIT.value()+"");
				t.setLevel(NoticeStatus.INIT.value()+"");
				modelDao.create(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("========记录通知发生错误======");
		}
		
	}

	@Override
	public void createNotice(TNoticeDto tNoticeDto) {
		try {
			TNotice t=new TNotice();
			t.setFromoper(tNoticeDto.getFromoper());
			t.setTooper(tNoticeDto.getTooper());
			t.setDeptid(tNoticeDto.getDeptid());
			t.setNtitle(tNoticeDto.getNtitle());
			t.setNotice(tNoticeDto.getNotice());
			t.setType(tNoticeDto.getType());
			t.setStatus(tNoticeDto.getStatus());
			t.setLevel(tNoticeDto.getLevel());
			t.setRemark(tNoticeDto.getRemark());
			t.setCreateoper(tNoticeDto.getCreateoper());
			t.setCreatetime(new Date());
			modelDao.create(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("========记录通知发生错误======");
		}
		
	}

	@Override
	public void updateNotice(TNoticeDto tNoticeDto) {
		try {
			TNotice t=new TNotice();
			t.setFromoper(tNoticeDto.getFromoper());
			t.setTooper(tNoticeDto.getTooper());
			t.setDeptid(tNoticeDto.getDeptid());
			t.setNtitle(tNoticeDto.getNtitle());
			t.setNotice(tNoticeDto.getNotice());
			t.setType(tNoticeDto.getType());
			t.setStatus(tNoticeDto.getStatus());
			t.setLevel(tNoticeDto.getLevel());
			t.setRemark(tNoticeDto.getRemark());
			t.setModifyoper(tNoticeDto.getModifyoper());
			t.setModifytime(new Date());
			modelDao.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("========记录通知发生错误======");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TNotice> queryNoticeList(TNoticeDto tNoticeDto) {
		
		return (List<TNotice>) modelDao.findListByParams("queryNoticeList", tNoticeDto);
	}
}