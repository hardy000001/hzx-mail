package com.lq.lss.core.dao;
                                                                    
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-9-6下午1:57:22
 */
@Repository
public class MchBaseinfoDao extends LssSimpleBaseDao<MchBaseinfo, Integer>{

   public String queryMaxMchCodeByDeptId(String deptid){
	   return (String) findObjectByParams("queryMaxMchCodeByDeptId", deptid);
   }
   /**
    * 
    * @param mchcode 商户id
    * @param deptid  中心id
    * @return
    */
   public MchBaseinfo queryMchBaseinfoById(String mchcode,String deptid){
			   Map<String, Object> params=new HashMap<String, Object>();
			   params.put("mchcode", mchcode);
			   params.put("deptid", deptid);
	   return (MchBaseinfo) findObjectByParams("queryMchBaseinfoById", params);
   }
   
   
   public int deleteByIdAndDeptid(String mchcode,String deptid) {
			   Map<String, Object> params=new HashMap<String, Object>();
			   params.put("mchcode", mchcode);
			   params.put("deptid", deptid);
		return super.delete("deleteByIdAndDeptid", params);
	}
   
   @SuppressWarnings("unchecked")
   public List<MchBaseinfo> findMaInfoListByDeptidList(String deptId) {
		
		return (List<MchBaseinfo>)findListByParams("findMchCodeByDeptId",deptId);
	}

	public MchBaseinfo selectMchBaseinfo(MchBaseinfo baseinfo){
		return (MchBaseinfo) findObjectByParams("selectMchBaseinfo", baseinfo);
	}
	
	
public List<MchBaseinfo> findMchInfAndCustomerinfoList(String deptId) {
		
		return (List<MchBaseinfo>)findListByParams("findMchInfAndCustomerinfoByDeptId",deptId);
	}
	
}