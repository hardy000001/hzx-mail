package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.BMaterialType;


public interface MaterialTypeService extends EasyUIService<BMaterialType, Integer> {
 
		public List<BMaterialType> loadAll();
	
		public List<BMaterialType> findBmtList(BMaterialType bMaterialType);

}
