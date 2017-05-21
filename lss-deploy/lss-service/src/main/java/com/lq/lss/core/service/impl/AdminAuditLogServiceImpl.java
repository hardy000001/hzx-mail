package com.lq.lss.core.service.impl;


import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminAuditLogDao;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.model.AdminAuditLog;
import com.lq.util.JSONUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminAuditLogServiceImpl extends EasyUIServiceBase<AdminAuditLog, Long, AdminAuditLogDao> implements AdminAuditLogService {


    @Override
    public void log(Long operatorId, int operateType, String operateDesc, Object paras, Long refId) {

        String parasStr = "";

        try {
            if (paras != null) {
                parasStr = JSONUtil.toJSonString(paras);
            }

            AdminAuditLog adminLog = new AdminAuditLog();
            adminLog.setCreateTime(new Date());
            adminLog.setOperatorId(operatorId);
            adminLog.setKeyParas(parasStr);
            adminLog.setOperateType(Long.parseLong(operateType+""));
            adminLog.setOperateDesc(operateDesc);
            adminLog.setRefId(refId);
            modelDao.create(adminLog);
        } catch (Exception e) {
            log.error("========记录日志发生错误======");
        }

    }


}
