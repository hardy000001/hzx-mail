package com.lq.lss.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.BRepairInfo;

@Repository
public class BRepairInfoDao extends LssSimpleBaseDao<BRepairInfo, Integer> {

	@SuppressWarnings("unchecked")
	public List<BRepairInfo> findRepairInfoByTypeid(String typeid) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("typeid", typeid);

		return (List<BRepairInfo>) findListByParams("findRepairInfoByTypeid",
				map);
	}

}
