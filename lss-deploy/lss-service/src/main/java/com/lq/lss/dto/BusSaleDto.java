package com.lq.lss.dto;

import java.util.List;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-9-21下午5:30:10
 */
public class BusSaleDto{

	/** sale_serialno **/
    private String saleSerialno;
    /** 接收商户**/
    private String toMchcode;
	/** 销售总金额 **/
    private String totalAmt;
	/** 销售客户 **/
    private String customer;
	/** 客户联系方式 **/
    private String tel;
	/** 备注 **/
    private String others;
	/** 状态 0：正常 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 创建时间 **/
    private String createtime;
    private String dataList;
    
    List<BusSaleDetailDto> busSaleDetailDtos;
    
    private String deptId;
    private Long userId;
    private String mchcode;
    private String mchname;
    private String loginName;
    private String centerName;
	
   /**
    * 获取属性:saleSerialno
    * sale_serialno
    * @return saleSerialno
    */
   public String getSaleSerialno() {
       return saleSerialno;
   }
   /**
    * 设置属性:saleSerialno
    * sale_serialno
    * @param saleSerialno
    */
   public void setSaleSerialno(String saleSerialno) {
       this.saleSerialno = saleSerialno;
   }
   /**
    * 获取属性:toMchcode
    * 接收商户
    * @return toMchcode
    */
   public String getToMchcode() {
       return toMchcode;
   }
   /**
    * 设置属性:toMchcode
    * 接收商户
    * @param toMchcode
    */
   public void setToMchcode(String toMchcode) {
       this.toMchcode = toMchcode;
   }
   /**
    * 获取属性:totalAmt
    * 销售总金额
    * @return totalAmt
    */
   public String getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * 销售总金额
    * @param totalAmt
    */
   public void setTotalAmt(String totalAmt) {
       this.totalAmt = totalAmt;
   }
	
   /**
    * 获取属性:customer
    * 销售客户
    * @return customer
    */
   public String getCustomer() {
       return customer;
   }
   /**
    * 设置属性:customer
    * 销售客户
    * @param customer
    */
   public void setCustomer(String customer) {
       this.customer = customer;
   }
	
   /**
    * 获取属性:tel
    * 客户联系方式
    * @return tel
    */
   public String getTel() {
       return tel;
   }
   /**
    * 设置属性:tel
    * 客户联系方式
    * @param tel
    */
   public void setTel(String tel) {
       this.tel = tel;
   }
	
   /**
    * 获取属性:others
    * 备注
    * @return others
    */
   public String getOthers() {
       return others;
   }
   /**
    * 设置属性:others
    * 备注
    * @param others
    */
   public void setOthers(String others) {
       this.others = others;
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
	
   /**
    * 获取属性:createoper
    * 操作员
    * @return createoper
    */
   public String getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 操作员
    * @param createoper
    */
   public void setCreateoper(String createoper) {
       this.createoper = createoper;
   }
	
   /**
    * 获取属性:createtime
    * 创建时间
    * @return createtime
    */
   public String getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 创建时间
    * @param createtime
    */
   public void setCreatetime(String createtime) {
       this.createtime = createtime;
   }
	
	public List<BusSaleDetailDto> getBusSaleDetailDtos() {
		return busSaleDetailDtos;
	}

	public void setBusSaleDetailDtos(List<BusSaleDetailDto> busSaleDetailDtos) {
		this.busSaleDetailDtos = busSaleDetailDtos;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	public String getMchcode() {
		return mchcode;
	}
	public void setMchcode(String mchcode) {
		this.mchcode = mchcode;
	}
	public String getMchname() {
		return mchname;
	}
	public void setMchname(String mchname) {
		this.mchname = mchname;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	

}