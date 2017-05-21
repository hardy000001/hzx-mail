package com.lq.lss.dto;

import java.util.List;


/**
 *
 * @author  作者: hzx
 * @String 创建时间: 2016-12-29 13:26:15
 */
public class StockInventoryDto{

	
	/** 盘点流水号**/
    private String inventorySerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 商户号**/
    private String mchcode;
	/** 盘点经办人**/
    private String inventoryoper;
	/** 制单人**/
    private String createoper;
    /** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 操作时间**/
    private String createtime;
	/** 审核操作员**/
    private String auditingoper;
	/** 审核时间**/
    private String auditingtime;
	/** 备注信息**/
    private String remark;
    private String dataList;
    private List<StockInventoryDetailDto> stockInventoryDetailDtos;
    
    private String beginTime;
    private String endTime;
    private String loginName;
    private String mchname;
    private String centerName;
 
	
   /**
    * 获取属性:inventorySerialno
    * 盘点流水号
    * @return inventorySerialno
    */
   public String getInventorySerialno() {
       return inventorySerialno;
   }
   /**
    * 设置属性:inventorySerialno
    * 盘点流水号
    * @param inventorySerialno
    */
   public void setInventorySerialno(String inventorySerialno) {
       this.inventorySerialno = inventorySerialno;
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
    * 获取属性:mchcode
    * 商户号
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 商户号
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
   }
	
   /**
    * 获取属性:inventoryoper
    * 盘点经办人
    * @return inventoryoper
    */
   public String getInventoryoper() {
       return inventoryoper;
   }
   /**
    * 设置属性:inventoryoper
    * 盘点经办人
    * @param inventoryoper
    */
   public void setInventoryoper(String inventoryoper) {
       this.inventoryoper = inventoryoper;
   }
	
   /**
    * 获取属性:createoper
    * 制单人
    * @return createoper
    */
   public String getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 制单人
    * @param createoper
    */
   public void setCreateoper(String createoper) {
       this.createoper = createoper;
   }
   /**
    * 获取属性:status
    * 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
   /**
    * 获取属性:createtime
    * 操作时间
    * @return createtime
    */
   public String getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 操作时间
    * @param createtime
    */
   public void setCreatetime(String createtime) {
       this.createtime = createtime;
   }
	
   /**
    * 获取属性:auditingoper
    * 审核操作员
    * @return auditingoper
    */
   public String getAuditingoper() {
       return auditingoper;
   }
   /**
    * 设置属性:auditingoper
    * 审核操作员
    * @param auditingoper
    */
   public void setAuditingoper(String auditingoper) {
       this.auditingoper = auditingoper;
   }
	
   /**
    * 获取属性:auditingtime
    * 审核时间
    * @return auditingtime
    */
   public String getAuditingtime() {
       return auditingtime;
   }
   /**
    * 设置属性:auditingtime
    * 审核时间
    * @param auditingtime
    */
   public void setAuditingtime(String auditingtime) {
       this.auditingtime = auditingtime;
   }
	
   /**
    * 获取属性:remark
    * 备注信息
    * @return remark
    */
   public String getRemark() {
       return remark;
   }
   /**
    * 设置属性:remark
    * 备注信息
    * @param remark
    */
   public void setRemark(String remark) {
       this.remark = remark;
   }
   
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	public List<StockInventoryDetailDto> getStockInventoryDetailDtos() {
		return stockInventoryDetailDtos;
	}
	public void setStockInventoryDetailDtos(
			List<StockInventoryDetailDto> stockInventoryDetailDtos) {
		this.stockInventoryDetailDtos = stockInventoryDetailDtos;
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
	public String getMchname() {
		return mchname;
	}
	public void setMchname(String mchname) {
		this.mchname = mchname;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
    
}