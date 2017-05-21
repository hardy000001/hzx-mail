package com.lq.lss.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lq.easyui.model.easyui.EasyUiModel;
                                                    
/**
 *
 * @author  作者: CH
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockTemporaryDto{



	/** 中心暂存单流水号**/
    private String temSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 所属租赁商户**/
    private String mchcode;
	/** 入库总米数**/
    private String totalM;
	/** total_s**/
    private String totalS;
	/** 入库总重量**/
    private String totalT;
	/** 装卸费用**/
    private String zxFee;
	/** 杂费**/
    private String otherFee;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 操作员**/
    private String createoper;
	/** 操作时间**/
    private String createtime;

    private String userId;
    /** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private String auditingtime;
    private String tradeinfo;
    List<CStockTemporaryDetailDto>  cStockTemporaryDetailDtos;
   
   
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
	
   /**
    * 获取属性:temSerialno
    * 中心暂存单流水号
    * @return temSerialno
    */
   public String getTemSerialno() {
       return temSerialno;
   }
   /**
    * 设置属性:temSerialno
    * 中心暂存单流水号
    * @param temSerialno
    */
   public void setTemSerialno(String temSerialno) {
       this.temSerialno = temSerialno;
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
    * 所属租赁商户
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 所属租赁商户
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
   }
	
   /**
    * 获取属性:totalM
    * 入库总米数
    * @return totalM
    */
   public String getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总米数
    * @param totalM
    */
   public void setTotalM(String totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * total_s
    * @return totalS
    */
   public String getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * total_s
    * @param totalS
    */
   public void setTotalS(String totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 入库总重量
    * @return totalT
    */
   public String getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总重量
    * @param totalT
    */
   public void setTotalT(String totalT) {
       this.totalT = totalT;
   }
	
   /**
    * 获取属性:zxFee
    * 装卸费用
    * @return zxFee
    */
   public String getZxFee() {
       return zxFee;
   }
   /**
    * 设置属性:zxFee
    * 装卸费用
    * @param zxFee
    */
   public void setZxFee(String zxFee) {
       this.zxFee = zxFee;
   }
	
   /**
    * 获取属性:otherFee
    * 杂费
    * @return otherFee
    */
   public String getOtherFee() {
       return otherFee;
   }
   /**
    * 设置属性:otherFee
    * 杂费
    * @param otherFee
    */
   public void setOtherFee(String otherFee) {
       this.otherFee = otherFee;
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
   	
   public String getUserId() {
       return userId;
   }
   
   
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<CStockTemporaryDetailDto> getcStockTemporaryDetailDtos() {
		return cStockTemporaryDetailDtos;
	}
	public void setcStockTemporaryDetailDtos(List<CStockTemporaryDetailDto> cStockTemporaryDetailDtos) {
		this.cStockTemporaryDetailDtos = cStockTemporaryDetailDtos;
	}
	public String getAuditingoper() {
		return auditingoper;
	}
	public void setAuditingoper(String auditingoper) {
		this.auditingoper = auditingoper;
	}
	public String getAuditingtime() {
		return auditingtime;
	}
	public void setAuditingtime(String auditingtime) {
		this.auditingtime = auditingtime;
	}
	public String getTradeinfo() {
		return tradeinfo;
	}
	public void setTradeinfo(String tradeinfo) {
		this.tradeinfo = tradeinfo;
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