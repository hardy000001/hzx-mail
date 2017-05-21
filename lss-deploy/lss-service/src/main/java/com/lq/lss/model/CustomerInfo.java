package com.lq.lss.model;

import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                            
/**
 * 客户基本信息表
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
public class CustomerInfo extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** deptid**/
    private String deptid;
	/** id**/
    private String id;
	/** 租赁商户号 **/
    private String cuscode;
	/** 客户名称**/
    private String cusname;
	/** 客户类型1：调拨客户 2：供应商3：采购商 4：担保公司**/
    private String custype;
	/** 客户入驻结算规则。mch_rule.id**/
    private Integer cusrule;
	/** 联系人**/
    private String linkman;
	/** 客户联系电话**/
    private String custel;
	/** 客户地址**/
    private String cusaddress;
	/** 客户营业执照文件地址**/
    private String cuslicence;
	/** 合同文件路径地址**/
    private String contracturl;
	/** 开会银行名称**/
    private String bankinfo;
	/** 开户名**/
    private String bankaccount;
	/** 银行开户账号**/
    private String accountno;
	/** 状态  0：正常  9：停用**/
    private Integer status;
	/** 修改时间**/
    private Date modifytime;
	/** 操作员**/
    private Integer createoper;
	/** 插入时间**/
    private Date createtime;

   
	
   /**
    * 获取属性:deptid
    * deptid
    * @return deptid
    */
   public String getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * deptid
    * @param deptid
    */
   public void setDeptid(String deptid) {
       this.deptid = deptid;
   }
	
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
    * 获取属性:cuscode
    * 租赁商户号 
    * @return cuscode
    */
   public String getCuscode() {
       return cuscode;
   }
   /**
    * 设置属性:cuscode
    * 租赁商户号 
    * @param cuscode
    */
   public void setCuscode(String cuscode) {
       this.cuscode = cuscode;
   }
	
   /**
    * 获取属性:cusname
    * 客户名称
    * @return cusname
    */
   public String getCusname() {
       return cusname;
   }
   /**
    * 设置属性:cusname
    * 客户名称
    * @param cusname
    */
   public void setCusname(String cusname) {
       this.cusname = cusname;
   }
	
   /**
    * 获取属性:custype
    * 客户类型1：调拨客户 2：供应商3：采购商 4：担保公司
    * @return custype
    */
   public String getCustype() {
       return custype;
   }
   /**
    * 设置属性:custype
    * 客户类型1：调拨客户 2：供应商3：采购商 4：担保公司
    * @param custype
    */
   public void setCustype(String custype) {
       this.custype = custype;
   }
	
   /**
    * 获取属性:cusrule
    * 客户入驻结算规则。mch_rule.id
    * @return cusrule
    */
   public Integer getCusrule() {
       return cusrule;
   }
   /**
    * 设置属性:cusrule
    * 客户入驻结算规则。mch_rule.id
    * @param cusrule
    */
   public void setCusrule(Integer cusrule) {
       this.cusrule = cusrule;
   }
	
   /**
    * 获取属性:linkman
    * 联系人
    * @return linkman
    */
   public String getLinkman() {
       return linkman;
   }
   /**
    * 设置属性:linkman
    * 联系人
    * @param linkman
    */
   public void setLinkman(String linkman) {
       this.linkman = linkman;
   }
	
   /**
    * 获取属性:custel
    * 客户联系电话
    * @return custel
    */
   public String getCustel() {
       return custel;
   }
   /**
    * 设置属性:custel
    * 客户联系电话
    * @param custel
    */
   public void setCustel(String custel) {
       this.custel = custel;
   }
	
   /**
    * 获取属性:cusaddress
    * 客户地址
    * @return cusaddress
    */
   public String getCusaddress() {
       return cusaddress;
   }
   /**
    * 设置属性:cusaddress
    * 客户地址
    * @param cusaddress
    */
   public void setCusaddress(String cusaddress) {
       this.cusaddress = cusaddress;
   }
	
   /**
    * 获取属性:cuslicence
    * 客户营业执照文件地址
    * @return cuslicence
    */
   public String getCuslicence() {
       return cuslicence;
   }
   /**
    * 设置属性:cuslicence
    * 客户营业执照文件地址
    * @param cuslicence
    */
   public void setCuslicence(String cuslicence) {
       this.cuslicence = cuslicence;
   }
	
   /**
    * 获取属性:contracturl
    * 合同文件路径地址
    * @return contracturl
    */
   public String getContracturl() {
       return contracturl;
   }
   /**
    * 设置属性:contracturl
    * 合同文件路径地址
    * @param contracturl
    */
   public void setContracturl(String contracturl) {
       this.contracturl = contracturl;
   }
	
   /**
    * 获取属性:bankinfo
    * 开会银行名称
    * @return bankinfo
    */
   public String getBankinfo() {
       return bankinfo;
   }
   /**
    * 设置属性:bankinfo
    * 开会银行名称
    * @param bankinfo
    */
   public void setBankinfo(String bankinfo) {
       this.bankinfo = bankinfo;
   }
	
   /**
    * 获取属性:bankaccount
    * 开户名
    * @return bankaccount
    */
   public String getBankaccount() {
       return bankaccount;
   }
   /**
    * 设置属性:bankaccount
    * 开户名
    * @param bankaccount
    */
   public void setBankaccount(String bankaccount) {
       this.bankaccount = bankaccount;
   }
	
   /**
    * 获取属性:accountno
    * 银行开户账号
    * @return accountno
    */
   public String getAccountno() {
       return accountno;
   }
   /**
    * 设置属性:accountno
    * 银行开户账号
    * @param accountno
    */
   public void setAccountno(String accountno) {
       this.accountno = accountno;
   }
	
   /**
    * 获取属性:status
    * 状态  0：正常  9：停用
    * @return status
    */
   public Integer getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态  0：正常  9：停用
    * @param status
    */
   public void setStatus(Integer status) {
       this.status = status;
   }
	
   /**
    * 获取属性:modifytime
    * 修改时间
    * @return modifytime
    */
   public Date getModifytime() {
       return modifytime;
   }
   /**
    * 设置属性:modifytime
    * 修改时间
    * @param modifytime
    */
   public void setModifytime(Date modifytime) {
       this.modifytime = modifytime;
   }
	
   /**
    * 获取属性:createoper
    * 操作员
    * @return createoper
    */
   public Integer getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 操作员
    * @param createoper
    */
   public void setCreateoper(Integer createoper) {
       this.createoper = createoper;
   }
	
   /**
    * 获取属性:createtime
    * 插入时间
    * @return createtime
    */
   public Date getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 插入时间
    * @param createtime
    */
   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }

}