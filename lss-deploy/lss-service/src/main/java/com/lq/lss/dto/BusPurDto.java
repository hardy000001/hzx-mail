package com.lq.lss.dto;

import java.util.List;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public class BusPurDto{

	//====================主表信息
	/** 采购流水号**/
    private String purSerialno;
	/** 所属中心**/
    private Integer deptid;
    /** 供应商户号**/
    private String fromMchcode;
	/** 采购总金额**/
    private String totalAmt;
	/** 备注信息**/
    private String other;
	/** 状态 0：正常**/
    private String status;
    /** 申请时间 **/
    private String createtime;
    private String mchcode;
    //====================从表信息
    private List<BusPurDetailDto> busPurDetailDtos;
    
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
 
   /**
    * 获取属性:purSerialno
    * 采购流水号
    * @return purSerialno
    */
   public String getPurSerialno() {
       return purSerialno;
   }
   /**
    * 设置属性:purSerialno
    * 采购流水号
    * @param purSerialno
    */
   public void setPurSerialno(String purSerialno) {
       this.purSerialno = purSerialno;
   }
	
   /**
    * 获取属性:deptid
    * 所属中心
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属中心
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
   /**
    * 获取属性:fromMchcode
    * 供应商户号
    * @return fromMchcode
    */
   public String getFromMchcode() {
       return fromMchcode;
   }
   /**
    * 设置属性:fromMchcode
    * 供应商户号
    * @param fromMchcode
    */
   public void setFromMchcode(String fromMchcode) {
       this.fromMchcode = fromMchcode;
   }
   /**
    * 获取属性:totalAmt
    * 采购总金额
    * @return totalAmt
    */
   public String getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * 采购总金额
    * @param totalAmt
    */
   public void setTotalAmt(String totalAmt) {
       this.totalAmt = totalAmt;
   }
	
   /**
    * 获取属性:other
    * 备注信息
    * @return other
    */
   public String getOther() {
       return other;
   }
   /**
    * 设置属性:other
    * 备注信息
    * @param other
    */
   public void setOther(String other) {
       this.other = other;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
   
	public List<BusPurDetailDto> getBusPurDetailDtos() {
		return busPurDetailDtos;
	}
	public void setBusPurDetailDtos(List<BusPurDetailDto> busPurDetailDtos) {
		this.busPurDetailDtos = busPurDetailDtos;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getMchcode() {
		return mchcode;
	}
	public void setMchcode(String mchcode) {
		this.mchcode = mchcode;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getMchname() {
		return mchname;
	}
	public void setMchname(String mchname) {
		this.mchname = mchname;
	}
	
    
  

}