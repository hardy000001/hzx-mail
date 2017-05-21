package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.BConsumableInfo;

@Repository
public class BConsumableInfoDao extends LssSimpleBaseDao<BConsumableInfo, Integer> {

	/**
	 * 按名称查询
	 * 
	 * @param name
	 * @return BConsumableInfo 或null
	 */
	public BConsumableInfo findByTypeName(String name) {
		return (BConsumableInfo) findObjectByParams("findByName", name);
	}

	/**
	 * 按类别查询易耗品
	 * @param typeid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BConsumableInfo> findTypeList(Integer  typeid) {
		return (List<BConsumableInfo>) findListByParams("findByTypeId", typeid);
	}

}
