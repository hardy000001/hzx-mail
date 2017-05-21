package com.lq.lss.core.dao.base;

import java.io.Serializable;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.lq.dao.BaseSimpleDaoV2;
import com.lq.model.base.SimpleBaseModel;


public class LssSimpleBaseDao<T extends SimpleBaseModel<ID>, ID extends Serializable> extends BaseSimpleDaoV2<T, ID> {

	@Resource(name = "lss-sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
