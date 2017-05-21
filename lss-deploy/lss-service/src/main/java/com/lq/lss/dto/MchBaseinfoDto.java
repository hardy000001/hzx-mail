package com.lq.lss.dto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public class MchBaseinfoDto{

	private Integer id;
	private Long mchcode;
	/** 商户名称**/
    private String mchname;
	/** 商户入驻结算规则。mch_rule.id**/
    private Integer mchrule;
	/** 商户联系方式**/
    private String mchtel;
	/** 商户地址**/
    private String mchaddress;
	/** 开会银行名称**/
    private String bankinfo;
	/** 开户名**/
    private String bankaccount;
	/** 银行开户账号**/
    private String accountno;
    private String status;
    private Integer createoper;
    
    private String deptid;

	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getMchcode() {
		return mchcode;
	}
	public void setMchcode(Long mchcode) {
		this.mchcode = mchcode;
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
   
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCreateoper() {
		return createoper;
	}
	public void setCreateoper(Integer createoper) {
		this.createoper = createoper;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
    
	
  

}