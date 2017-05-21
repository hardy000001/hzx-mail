package com.lq.lss.model;

import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
public class UAdminUser extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


    /** id **/
    private String id;
	/** 登录名**/
    private String loginName;
	/** 登录密码**/
    private String loginPwd;
	/** real_name**/
    private String realName;
	/** 0为禁用，1为启用**/
    private Integer status;
	/** 手机号**/
    private String mobile;
	/** email**/
    private String email;
	/** 部门id**/
    private String deptId;
	/** 操作员编码**/
    private String operCode;
	/** 性别：1：男，2：女**/
    private String sex;
	/** 拼音简拼**/
    private String pyCode;
	/** 五笔简拼**/
    private String wbCode;
	/** 联系地址**/
    private String address;
	/** create_time**/
    private Date createTime;
	/** modify_time**/
    private Date modifyTime;
	/** 商户code**/
    private String mchCode;

   
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
	
   /**
    * 获取属性:loginName
    * 登录名
    * @return loginName
    */
   public String getLoginName() {
       return loginName;
   }
   /**
    * 设置属性:loginName
    * 登录名
    * @param loginName
    */
   public void setLoginName(String loginName) {
       this.loginName = loginName;
   }
	
   /**
    * 获取属性:loginPwd
    * 登录密码
    * @return loginPwd
    */
   public String getLoginPwd() {
       return loginPwd;
   }
   /**
    * 设置属性:loginPwd
    * 登录密码
    * @param loginPwd
    */
   public void setLoginPwd(String loginPwd) {
       this.loginPwd = loginPwd;
   }
	
   /**
    * 获取属性:realName
    * real_name
    * @return realName
    */
   public String getRealName() {
       return realName;
   }
   /**
    * 设置属性:realName
    * real_name
    * @param realName
    */
   public void setRealName(String realName) {
       this.realName = realName;
   }
	
   /**
    * 获取属性:status
    * 0为禁用，1为启用
    * @return status
    */
   public Integer getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 0为禁用，1为启用
    * @param status
    */
   public void setStatus(Integer status) {
       this.status = status;
   }
	
   /**
    * 获取属性:mobile
    * 手机号
    * @return mobile
    */
   public String getMobile() {
       return mobile;
   }
   /**
    * 设置属性:mobile
    * 手机号
    * @param mobile
    */
   public void setMobile(String mobile) {
       this.mobile = mobile;
   }
	
   /**
    * 获取属性:email
    * email
    * @return email
    */
   public String getEmail() {
       return email;
   }
   /**
    * 设置属性:email
    * email
    * @param email
    */
   public void setEmail(String email) {
       this.email = email;
   }
	
   /**
    * 获取属性:deptId
    * 部门id
    * @return deptId
    */
   public String getDeptId() {
       return deptId;
   }
   /**
    * 设置属性:deptId
    * 部门id
    * @param deptId
    */
   public void setDeptId(String deptId) {
       this.deptId = deptId;
   }
	
   /**
    * 获取属性:operCode
    * 操作员编码
    * @return operCode
    */
   public String getOperCode() {
       return operCode;
   }
   /**
    * 设置属性:operCode
    * 操作员编码
    * @param operCode
    */
   public void setOperCode(String operCode) {
       this.operCode = operCode;
   }
	
   /**
    * 获取属性:sex
    * 性别：1：男，2：女
    * @return sex
    */
   public String getSex() {
       return sex;
   }
   /**
    * 设置属性:sex
    * 性别：1：男，2：女
    * @param sex
    */
   public void setSex(String sex) {
       this.sex = sex;
   }
	
   /**
    * 获取属性:pyCode
    * 拼音简拼
    * @return pyCode
    */
   public String getPyCode() {
       return pyCode;
   }
   /**
    * 设置属性:pyCode
    * 拼音简拼
    * @param pyCode
    */
   public void setPyCode(String pyCode) {
       this.pyCode = pyCode;
   }
	
   /**
    * 获取属性:wbCode
    * 五笔简拼
    * @return wbCode
    */
   public String getWbCode() {
       return wbCode;
   }
   /**
    * 设置属性:wbCode
    * 五笔简拼
    * @param wbCode
    */
   public void setWbCode(String wbCode) {
       this.wbCode = wbCode;
   }
	
   /**
    * 获取属性:address
    * 联系地址
    * @return address
    */
   public String getAddress() {
       return address;
   }
   /**
    * 设置属性:address
    * 联系地址
    * @param address
    */
   public void setAddress(String address) {
       this.address = address;
   }
	
   /**
    * 获取属性:createTime
    * create_time
    * @return createTime
    */
   public Date getCreateTime() {
       return createTime;
   }
   /**
    * 设置属性:createTime
    * create_time
    * @param createTime
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }
	
   /**
    * 获取属性:modifyTime
    * modify_time
    * @return modifyTime
    */
   public Date getModifyTime() {
       return modifyTime;
   }
   /**
    * 设置属性:modifyTime
    * modify_time
    * @param modifyTime
    */
   public void setModifyTime(Date modifyTime) {
       this.modifyTime = modifyTime;
   }
	
   /**
    * 获取属性:mchCode
    * 商户code
    * @return mchCode
    */
   public String getMchCode() {
       return mchCode;
   }
   /**
    * 设置属性:mchCode
    * 商户code
    * @param mchCode
    */
   public void setMchCode(String mchCode) {
       this.mchCode = mchCode;
   }

}