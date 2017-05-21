package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.CustomerInfoDto;
import com.lq.lss.model.CustomerInfo;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
public interface CustomerInfoService extends EasyUIService<CustomerInfo, String>{

	/**
	 * 查询最大客户编号
	 * @param deptId 已作废
	 * @return
	 */
	public String queryMaxCusCodeByDeptId(String deptId);

    /**
     * 新增客户
     * @param customerInfo
     * @return
     */
	public ResultDto<String> saveCustomerInfoRdTx(CustomerInfoDto customerInfoDto);
	/**
	 * 删除客户
	 * @param id
	 * @param deptId
	 * @return
	 */
	public ResultDto<String> deleteInfoById(String id,String deptId);
	 /**
     * 修改
     * @param customerInfoDto
     * @return
     */
	public ResultDto<String> updateCustomerInfoRdTx(CustomerInfoDto customerInfoDto);
	/**
	 * 审核
	 * @param auditDto
	 */
	public ResultDto<String>  auditInfoRdTx(AuditDto auditDto);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public CustomerInfo queryCustomerInfoById(String id);
	/**
	 * 查询客户信息
	 * @param id
	 * @param deptId
	 * @return
	 */
	public CustomerInfo findCustomerInfoByIdAndDeptId(String id,String deptId);
	
	
	/**
	 * 查询登录账户下中心客户
	 * @param id
	 * @return
	 */
	public List<CustomerInfo> queryCustomerInfoByDeptId(String deptId);
	/**
	 * 查询登录账户下调各类客户信息
	 * @param deptId   custype
	 * @return
	 */
	public List<CustomerInfo> queryCustomerInfoByDeptIdAndCustype(String deptId);
	

}