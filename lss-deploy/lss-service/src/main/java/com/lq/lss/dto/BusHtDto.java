package com.lq.lss.dto;

import java.util.List;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
public class BusHtDto{

	
	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
    /** deptid**/
    private String deptid;
	/** 公司名称**/
    private String comName;
	/** 公司地址**/
    private String comAddress;
	/** 公司联系人**/
    private String comLinkman;
	/** 联系方式**/
    private String comTel;
	/** 公司类型  1：内部  2：外部 3：大客户**/
    private Integer comType;
	/** 项目保证金**/
    private String projectDeposit;
	/** 项目地址**/
    private String projectAdress;
	/** 项目开始时间**/
    private String beginDate;
	/** 项目结束时间**/
    private String endDate;
	/** 合计租期**/
    private String totalDays;
	/** 状态 0:合同正常进行中    8：合同完成  9 合同作废**/
    private String status;
	/** 合同类型   预留**/
    private Integer htType;
	/** 操作员**/
    private String createoper;
	/** 操作时间**/
    private String createtime;
	/** 审核员**/
    private String auditingoper;
	/** 审核时间**/
    private String auditingtime;
    /** 项目名称**/
    private String projectName;
    /** 承租单位code**/
    private String lesseeCusCode;
	/** 担保单位code**/
    private String assureCusCode;
    
    private String dataList;
    private String dataList2;
    private List<BusHtRepairinfoDto> busHtRepairinfoDtos;
    private List<BusHtRentinfoDto> busHtRentinfoDtos;
    private BusHtSettlementDto busHtSettlementDto;

 
	
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
    * 获取属性:comName
    * 公司名称
    * @return comName
    */
   public String getComName() {
       return comName;
   }
   /**
    * 设置属性:comName
    * 公司名称
    * @param comName
    */
   public void setComName(String comName) {
       this.comName = comName;
   }
	
   /**
    * 获取属性:comAddress
    * 公司地址
    * @return comAddress
    */
   public String getComAddress() {
       return comAddress;
   }
   /**
    * 设置属性:comAddress
    * 公司地址
    * @param comAddress
    */
   public void setComAddress(String comAddress) {
       this.comAddress = comAddress;
   }
	
   /**
    * 获取属性:comLinkman
    * 公司联系人
    * @return comLinkman
    */
   public String getComLinkman() {
       return comLinkman;
   }
   /**
    * 设置属性:comLinkman
    * 公司联系人
    * @param comLinkman
    */
   public void setComLinkman(String comLinkman) {
       this.comLinkman = comLinkman;
   }
	
   /**
    * 获取属性:comTel
    * 联系方式
    * @return comTel
    */
   public String getComTel() {
       return comTel;
   }
   /**
    * 设置属性:comTel
    * 联系方式
    * @param comTel
    */
   public void setComTel(String comTel) {
       this.comTel = comTel;
   }
	
   /**
    * 获取属性:comType
    * 公司类型  1：内部  2：外部 3：大客户
    * @return comType
    */
   public Integer getComType() {
       return comType;
   }
   /**
    * 设置属性:comType
    * 公司类型  1：内部  2：外部 3：大客户
    * @param comType
    */
   public void setComType(Integer comType) {
       this.comType = comType;
   }
	
   /**
    * 获取属性:projectDeposit
    * 项目保证金
    * @return projectDeposit
    */
   public String getProjectDeposit() {
       return projectDeposit;
   }
   /**
    * 设置属性:projectDeposit
    * 项目保证金
    * @param projectDeposit
    */
   public void setProjectDeposit(String projectDeposit) {
       this.projectDeposit = projectDeposit;
   }
	
   /**
    * 获取属性:projectAdress
    * 项目地址
    * @return projectAdress
    */
   public String getProjectAdress() {
       return projectAdress;
   }
   /**
    * 设置属性:projectAdress
    * 项目地址
    * @param projectAdress
    */
   public void setProjectAdress(String projectAdress) {
       this.projectAdress = projectAdress;
   }
	
   /**
    * 获取属性:beginDate
    * 项目开始时间
    * @return beginDate
    */
   public String getBeginDate() {
       return beginDate;
   }
   /**
    * 设置属性:beginDate
    * 项目开始时间
    * @param beginDate
    */
   public void setBeginDate(String beginDate) {
       this.beginDate = beginDate;
   }
	
   /**
    * 获取属性:endDate
    * 项目结束时间
    * @return endDate
    */
   public String getEndDate() {
       return endDate;
   }
   /**
    * 设置属性:endDate
    * 项目结束时间
    * @param endDate
    */
   public void setEndDate(String endDate) {
       this.endDate = endDate;
   }
	
   /**
    * 获取属性:totalDays
    * 合计租期
    * @return totalDays
    */
   public String getTotalDays() {
       return totalDays;
   }
   /**
    * 设置属性:totalDays
    * 合计租期
    * @param totalDays
    */
   public void setTotalDays(String totalDays) {
       this.totalDays = totalDays;
   }
	
   /**
    * 获取属性:status
    * 状态 0:合同正常进行中    8：合同完成  9 合同作废
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0:合同正常进行中    8：合同完成  9 合同作废
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
	
   /**
    * 获取属性:htType
    * 合同类型   预留
    * @return htType
    */
   public Integer getHtType() {
       return htType;
   }
   /**
    * 设置属性:htType
    * 合同类型   预留
    * @param htType
    */
   public void setHtType(Integer htType) {
       this.htType = htType;
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
	
   /**
    * 获取属性:auditingoper
    * 审核员
    * @return auditingoper
    */
   public String getAuditingoper() {
       return auditingoper;
   }
   /**
    * 设置属性:auditingoper
    * 审核员
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
    * 获取属性:lesseeCusCode
    * 承租单位code
    * @return lesseeCusCode
    */
   public String getLesseeCusCode() {
       return lesseeCusCode;
   }
   /**
    * 设置属性:lesseeCusCode
    * 承租单位code
    * @param lesseeCusCode
    */
   public void setLesseeCusCode(String lesseeCusCode) {
       this.lesseeCusCode = lesseeCusCode;
   }
	
   /**
    * 获取属性:assureCusCode
    * 担保单位code
    * @return assureCusCode
    */
   public String getAssureCusCode() {
       return assureCusCode;
   }
   /**
    * 设置属性:assureCusCode
    * 担保单位code
    * @param assureCusCode
    */
   public void setAssureCusCode(String assureCusCode) {
       this.assureCusCode = assureCusCode;
   }
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	public String getDataList2() {
		return dataList2;
	}
	public void setDataList2(String dataList2) {
		this.dataList2 = dataList2;
	}
	public List<BusHtRepairinfoDto> getBusHtRepairinfoDtos() {
		return busHtRepairinfoDtos;
	}
	public void setBusHtRepairinfoDtos(List<BusHtRepairinfoDto> busHtRepairinfoDtos) {
		this.busHtRepairinfoDtos = busHtRepairinfoDtos;
	}
	public List<BusHtRentinfoDto> getBusHtRentinfoDtos() {
		return busHtRentinfoDtos;
	}
	public void setBusHtRentinfoDtos(List<BusHtRentinfoDto> busHtRentinfoDtos) {
		this.busHtRentinfoDtos = busHtRentinfoDtos;
	}
	public BusHtSettlementDto getBusHtSettlementDto() {
		return busHtSettlementDto;
	}
	public void setBusHtSettlementDto(BusHtSettlementDto busHtSettlementDto) {
		this.busHtSettlementDto = busHtSettlementDto;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
   
    
}