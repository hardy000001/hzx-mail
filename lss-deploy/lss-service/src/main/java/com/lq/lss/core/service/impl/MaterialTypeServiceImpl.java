package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.MaterialTypeDao;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.model.BMaterialType;

/**
 * 
 * @author lanbo
 *
 */
@Service
public class MaterialTypeServiceImpl extends EasyUIServiceBase<BMaterialType, Integer, MaterialTypeDao> implements MaterialTypeService {

	@Override
	public List<BMaterialType> findBmtList(BMaterialType bMaterialType) {
		// TODO Auto-generated method stub
		 return modelDao.findBmtList(bMaterialType);
	}

	


}
