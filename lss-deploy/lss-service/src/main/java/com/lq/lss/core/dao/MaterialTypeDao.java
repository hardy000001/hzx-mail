package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.BMaterialType;

/**
 * 
 * @author lanbo
 *
 */
@Repository
public class MaterialTypeDao extends LssSimpleBaseDao<BMaterialType, Integer> {
 
 @SuppressWarnings("unchecked")
 public List<BMaterialType> findBmtList(BMaterialType bMaterialType){
	 return (List<BMaterialType>)findListByParams("findMaterialTypeList", bMaterialType);
 }
}
