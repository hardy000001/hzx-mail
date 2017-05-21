package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.BConsumableType;

@Repository
public class BConsumableTypeDao extends LssSimpleBaseDao<BConsumableType, Integer> {

	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return BConsumableType 或null
	 */
	public BConsumableType findByTypeName(String typeName) {
		return (BConsumableType) findObjectByParams("findByTypeName", typeName);
	}

	/**
	 * 查询列表
	 * @param bConsumableType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BConsumableType> findTypeList(BConsumableType bConsumableType) {
		return (List<BConsumableType>) findListByParams("findTypeList", bConsumableType);
	}

}
