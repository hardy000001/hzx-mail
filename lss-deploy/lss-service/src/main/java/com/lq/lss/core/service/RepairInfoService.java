package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.BRepairInfo;

public interface RepairInfoService extends EasyUIService<BRepairInfo, Integer> {
    /**
     * 通过物资类别查维修信息
     * @param typeid
     * @return
     */
	List<BRepairInfo> queryRepairInfoByTypeid(String typeid);

}
