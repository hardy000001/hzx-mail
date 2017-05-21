package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.BMaterialInfo;

/**
 * 
 * @author lanbo
 *
 */
@Repository
public class MaterialInfoDao extends LssSimpleBaseDao<BMaterialInfo, Integer> {

	@SuppressWarnings("unchecked")
	public List<BMaterialInfo> findMaInfoList(Integer  typeid) {
		return (List<BMaterialInfo>) findListByParams("findByTypeId", typeid);
	}
}
