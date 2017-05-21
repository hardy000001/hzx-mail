package com.lq.lss.model;

import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author hzx
 * @since 2016-08-31
 */
public class MchBaseinfo extends EasyUiModel<Integer>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 租赁商户号  **/
    private Long mchcode;
	/** id **/
    private Integer id;
	/** deptid **/
    private Integer deptid;
	/** 商户名称 **/
    private String mchname;
	/** 商户入驻结算规则。mch_rule.id **/
    private Integer mchrule;
	/** 商户联系方式 **/
    private String mchtel;
	/** 商户地址 **/
    private String mchaddress;
	/** 商户营业执照文件地址 **/
    private String mchlicence;
	/** 合同文件路径地址 **/
    private String contracturl;
	/** 开会银行名称 **/
    private String bankinfo;
	/** 开户名 **/
    private String bankaccount;
	/** 银行开户账号 **/
    private String accountno;
	/** 状态  0：正常  1：开户申请    2：已拒绝    9：停用 **/
    private Integer status;
	/** 修改时间 **/
    private Date modifytime;
	/** 操作员 **/
    private Integer createoper;
	/** 插入时间 **/
    private Date createtime;

    private String loginName;

    private String deptName;
    
    private String type;

   /**
    * 获取属性:mchcode
    * 租赁商户号 
    * @return mchcode
    */
   public Long getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 租赁商户号 
    * @param mchcode
    */
   public void setMchcode(Long mchcode) {
       this.mchcode = mchcode;
   }
	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:deptid
    * deptid
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * deptid
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:mchname
    * 商户名称
    * @return mchname
    */
   public String getMchname() {
       return mchname;
   }
   /**
    * 设置属性:mchname
    * 商户名称
    * @param mchname
    */
   public void setMchname(String mchname) {
       this.mchname = mchname;
   }
	
   /**
    * 获取属性:mchrule
    * 商户入驻结算规则。mch_rule.id
    * @return mchrule
    */
   public Integer getMchrule() {
       return mchrule;
   }
   /**
    * 设置属性:mchrule
    * 商户入驻结算规则。mch_rule.id
    * @param mchrule
    */
   public void setMchrule(Integer mchrule) {
       this.mchrule = mchrule;
   }
	
   /**
    * 获取属性:mchtel
    * 商户联系方式
    * @return mchtel
    */
   public String getMchtel() {
       return mchtel;
   }
   /**
    * 设置属性:mchtel
    * 商户联系方式
    * @param mchtel
    */
   public void setMchtel(String mchtel) {
       this.mchtel = mchtel;
   }
	
   /**
    * 获取属性:mchaddress
    * 商户地址
    * @return mchaddress
    */
   public String getMchaddress() {
       return mchaddress;
   }
   /**
    * 设置属性:mchaddress
    * 商户地址
    * @param mchaddress
    */
   public void setMchaddress(String mchaddress) {
       this.mchaddress = mchaddress;
   }
	
   /**
    * 获取属性:mchlicence
    * 商户营业执照文件地址
    * @return mchlicence
    */
   public String getMchlicence() {
       return mchlicence;
   }
   /**
    * 设置属性:mchlicence
    * 商户营业执照文件地址
    * @param mchlicence
    */
   public void setMchlicence(String mchlicence) {
       this.mchlicence = mchlicence;
   }
	
   /**
    * 获取属性:
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
    * 状态  0：正常  1：开户申请    2：已拒绝    9：停用
    * @return status
    */
   public Integer getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态  0：正常  1：开户申请    2：已拒绝    9：停用
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}