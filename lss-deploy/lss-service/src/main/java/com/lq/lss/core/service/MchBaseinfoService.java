package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.MchBaseinfoDto;
import com.lq.lss.model.MchBaseinfo;

/**
 * 租赁商户基本信息接口
 * @author  作者: hzx
 * @date 创建时间: 2016-9-6下午1:54:18
 */
public interface MchBaseinfoService extends EasyUIService<MchBaseinfo, Integer>{
	
	/**
	 * 查询最大商户号
	 * @param deptId 已作废
	 * @return
	 */
	public String queryMaxMchCodeByDeptId(String deptId);

    /**
     * 开户申请
     * @param mchBaseinfo
     * @return
     */
	public ResultDto<String> saveMchBaseinfoRdTx(MchBaseinfo mchBaseinfo);
	/**
	 * 删除商户
	 * @param id
	 * @param deptId
	 * @return
	 */
	public ResultDto<String> deleteInfoById(String id,String deptId);
	 /**
     * 审核
     * @param mchBaseinfoDto
     * @return
     */
	public ResultDto<String> updateMchBaseinfoRdTx(MchBaseinfoDto mchBaseinfoDto);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public MchBaseinfo queryMchBaseinfoById(String id);
	/**
	 * 查询公司信息
	 * @param id
	 * @param deptId
	 * @return
	 */
	public MchBaseinfo findMchInfoByIdAndDeptId(String id,String deptId);
	
	
	/**
	 * 查询登录账户下中心客户
	 * @param id
	 * @return
	 */
	public List<MchBaseinfo> queryMchBaseinfoByDeptId(String deptId);

	public MchBaseinfo queryMchBaseinfo (MchBaseinfo baseinfo);

	public List<MchBaseinfo> querMchInfAndCustomerinfoByDeptId(String string);
	
	/**
	 * 查询登录账户下中心客户调拨客户
	 * @param id
	 * @return
	 */


}