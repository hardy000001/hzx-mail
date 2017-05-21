package com.lq.lss.core.dao;
                                                                            
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.CustomerInfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Repository
public class CustomerInfoDao extends LssSimpleBaseDao<CustomerInfo, String>{

	   public String queryMaxCusCodeByDeptId(String deptid){
		   return (String) findObjectByParams("queryMaxCusCodeByDeptId", deptid);
	   }
	   /**
	    * 
	    * @param cuscode 客户id
	    * @param deptid  中心id
	    * @return
	    */
	   public CustomerInfo queryCustomerInfoById(String cuscode,String deptid){
				   Map<String, Object> params=new HashMap<String, Object>();
				   params.put("cuscode", cuscode);
				   params.put("deptid", deptid);
		   return (CustomerInfo) findObjectByParams("queryCustomerInfoById", params);
	   }
	   
	   
	   public int deleteByIdAndDeptid(String cuscode,String deptid) {
				   Map<String, Object> params=new HashMap<String, Object>();
				   params.put("cuscode", cuscode);
				   params.put("deptid", deptid);
			return super.delete("deleteByIdAndDeptid", params);
		}
	   
	   
		@SuppressWarnings("unchecked")
		public List<CustomerInfo> findCustomerInfoList(String deptId) {
	
		return (List<CustomerInfo>) findListByParams("findCustomerInfoList",
				deptId);
		}
		
	
		 public List<CustomerInfo> findCustomerInfoByDeptIdAndCustype (String deptid){
			  return (List<CustomerInfo>)findListByParams("findCustomerInfoBycustype",deptid);
		 }
}