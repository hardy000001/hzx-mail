package com.lq.lss.core.service;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.AdminAuditLog;

/**
 *
 */
public interface AdminAuditLogService extends EasyUIService<AdminAuditLog, Long> {


    /**
     * 记录日志
     * @param operatorId  操作员ID
     * @param operateType 操作类型
     * @param operateDesc 操作描述
     * @param paras  参数列表
     * @param refId 引用的ID  删除或者更新的主键 ，默认0
     */
    void log(Long operatorId, int operateType, String operateDesc, Object paras, Long refId);
	
}
