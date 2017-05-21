package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                    
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-01 14:54:23
 */
public class SMchRentmaterial extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
	/** 商户号 **/
    private String cuscode;
	/** 所属中心**/
    private Integer deptid;
	/** 材料类别**/
    private Integer typeid;
	/** 期初数量**/
    private BigDecimal beginNum;
	/** 期末数量**/
    private BigDecimal endNum;
	/** 发料数量**/
    private BigDecimal sendNum;
	/** 收料数量**/
    private BigDecimal receiptNum;
	/** 赔偿数量**/
    private BigDecimal indemnifyNum;
	/** 单位**/
    private String unit;
	/** 结算月份**/
    private String settledate;

    private String projectName;
    private String cusName;
    private String centerName;
    private String typeName;
	
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
    * 获取属性:cuscode
    * 商户号 
    * @return cuscode
    */
   public String getCuscode() {
       return cuscode;
   }
   /**
    * 设置属性:cuscode
    * 商户号 
    * @param cuscode
    */
   public void setCuscode(String cuscode) {
       this.cuscode = cuscode;
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
    * 获取属性:typeid
    * 材料类别
    * @return typeid
    */
   public Integer getTypeid() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 材料类别
    * @param typeid
    */
   public void setTypeid(Integer typeid) {
       this.typeid = typeid;
   }
	
   /**
    * 获取属性:beginNum
    * 期初数量
    * @return beginNum
    */
   public BigDecimal getBeginNum() {
       return beginNum;
   }
   /**
    * 设置属性:beginNum
    * 期初数量
    * @param beginNum
    */
   public void setBeginNum(BigDecimal beginNum) {
       this.beginNum = beginNum;
   }
	
   /**
    * 获取属性:endNum
    * 期末数量
    * @return endNum
    */
   public BigDecimal getEndNum() {
       return endNum;
   }
   /**
    * 设置属性:endNum
    * 期末数量
    * @param endNum
    */
   public void setEndNum(BigDecimal endNum) {
       this.endNum = endNum;
   }
	
   /**
    * 获取属性:sendNum
    * 发料数量
    * @return sendNum
    */
   public BigDecimal getSendNum() {
       return sendNum;
   }
   /**
    * 设置属性:sendNum
    * 发料数量
    * @param sendNum
    */
   public void setSendNum(BigDecimal sendNum) {
       this.sendNum = sendNum;
   }
	
   /**
    * 获取属性:receiptNum
    * 收料数量
    * @return receiptNum
    */
   public BigDecimal getReceiptNum() {
       return receiptNum;
   }
   /**
    * 设置属性:receiptNum
    * 收料数量
    * @param receiptNum
    */
   public void setReceiptNum(BigDecimal receiptNum) {
       this.receiptNum = receiptNum;
   }
	
   /**
    * 获取属性:indemnifyNum
    * 赔偿数量
    * @return indemnifyNum
    */
   public BigDecimal getIndemnifyNum() {
       return indemnifyNum;
   }
   /**
    * 设置属性:indemnifyNum
    * 赔偿数量
    * @param indemnifyNum
    */
   public void setIndemnifyNum(BigDecimal indemnifyNum) {
       this.indemnifyNum = indemnifyNum;
   }
	
   /**
    * 获取属性:unit
    * 单位
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * 单位
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }
	
   /**
    * 获取属性:settledate
    * 结算月份
    * @return settledate
    */
   public String getSettledate() {
       return settledate;
   }
   /**
    * 设置属性:settledate
    * 结算月份
    * @param settledate
    */
   public void setSettledate(String settledate) {
       this.settledate = settledate;
   }
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public String getCusName() {
	return cusName;
}
public void setCusName(String cusName) {
	this.cusName = cusName;
}
public String getCenterName() {
	return centerName;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}
   
   

}