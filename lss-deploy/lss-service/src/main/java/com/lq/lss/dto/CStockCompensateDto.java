package com.lq.lss.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
public class CStockCompensateDto{

	
	/** 赔偿流水号**/
    private String compensateSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 商户号**/
    private String mchcode;
	/** 合同编号**/
    private String htcode;
	/** 承租经办人**/
    private String lessee;
	/** 出租经办人**/
    private String renter;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 是否按照同类设置价格 0:各自规格设置价格 1：按照大类设置价格   默认是1**/
    private String flag;
	/** 交易汇总信息**/
    private String tradeinfo;
	/** 制单人**/
    private String createoper;
	/** 操作时间**/
    private String createtime;
	/** 审核操作员**/
    private String auditingoper;
	/** 审核时间**/
    private String auditingtime;
	/** 备注信息**/
    private String remark;
    
    
    private Long userId;
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchcname;
    private String projectName;

 
    private List<CStockCompensateDetailDto> cStockCompensateDetailDtos;
   /**
    * 获取属性:compensateSerialno
    * 赔偿流水号
    * @return compensateSerialno
    */
   public String getCompensateSerialno() {
       return compensateSerialno;
   }
   /**
    * 设置属性:compensateSerialno
    * 赔偿流水号
    * @param compensateSerialno
    */
   public void setCompensateSerialno(String compensateSerialno) {
       this.compensateSerialno = compensateSerialno;
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
    * 获取属性:htcode
    * 合同编号
    * @return htcode
    */
   public String getHtcode() {
       return htcode;
   }
   /**
    * 设置属性:htcode
    * 合同编号
    * @param htcode
    */
   public void setHtcode(String htcode) {
       this.htcode = htcode;
   }
	
   /**
    * 获取属性:lessee
    * 承租经办人
    * @return lessee
    */
   public String getLessee() {
       return lessee;
   }
   /**
    * 设置属性:lessee
    * 承租经办人
    * @param lessee
    */
   public void setLessee(String lessee) {
       this.lessee = lessee;
   }
	
   /**
    * 获取属性:renter
    * 出租经办人
    * @return renter
    */
   public String getRenter() {
       return renter;
   }
   /**
    * 设置属性:renter
    * 出租经办人
    * @param renter
    */
   public void setRenter(String renter) {
       this.renter = renter;
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
    * 获取属性:flag
    * 是否按照同类设置价格 0:各自规格设置价格 1：按照大类设置价格   默认是1
    * @return flag
    */
   public String getFlag() {
       return flag;
   }
   /**
    * 设置属性:flag
    * 是否按照同类设置价格 0:各自规格设置价格 1：按照大类设置价格   默认是1
    * @param flag
    */
   public void setFlag(String flag) {
       this.flag = flag;
   }
	
   /**
    * 获取属性:tradeinfo
    * 交易汇总信息
    * @return tradeinfo
    */
   public String getTradeinfo() {
       return tradeinfo;
   }
   /**
    * 设置属性:tradeinfo
    * 交易汇总信息
    * @param tradeinfo
    */
   public void setTradeinfo(String tradeinfo) {
       this.tradeinfo = tradeinfo;
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
public List<CStockCompensateDetailDto> getcStockCompensateDetailDtos() {
	return cStockCompensateDetailDtos;
}
public void setcStockCompensateDetailDtos(List<CStockCompensateDetailDto> cStockCompensateDetailDtos) {
	this.cStockCompensateDetailDtos = cStockCompensateDetailDtos;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
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
public String getMchcname() {
	return mchcname;
}
public void setMchcname(String mchcname) {
	this.mchcname = mchcname;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
   
   

}