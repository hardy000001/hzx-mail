package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author Eric
 * @since 2016-08-31
 */
public class CStockDetailInfo extends EasyUiModel<Integer>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 所属租赁商户号 **/
    private String mchcode;
	/** 所属租赁商名称 **/
    private String mchcodename;
	/** 自增序号 **/
    private Integer id;
	/** 所属租赁中心的ID **/
    private Integer deptid;
	/** 租赁中心名称 **/
    private String deptname;
    
	/** 物资类型编号 **/
    private String materialcode;
	/** 物资类型名称 **/
    private String materialname;
    
    
	/** 库存总数量 **/
    private BigDecimal totalS;
	/** 库存物资的单位 **/
    private String unit;
	/** 库存的总米数 **/
    private BigDecimal totalM;
	/** 库存总重量 **/
    private BigDecimal totalT;
	/** 最后修改时间 **/
    private Date modifytime;
    
	/** 增减标示1增加，-1为减少**/
    private int changeType;
	/** 是否有此物料 **/
    private String isHave;
    
    
	/** test **/
    private String quantity;
    
    
	/** 小计的单位 **/
    private String cunit;
	/** 小计总数 **/
    private BigDecimal ctotal;
    
    
    
    
    

	
   /**
    * 获取属性:mchcode
    * 所属租赁商户号
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 所属租赁商户号
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
   }
   
   /**
    * 获取属性:mchcodename
    * 所属租赁商户号
    * @return mchcode
    */
   public String getMchcodename() {
       return mchcodename;
   }
   /**
    * 设置属性:mchcodename
    * 所属租赁商户号
    * @param mchcode
    */
   public void setMchcodename(String mchcodename) {
       this.mchcodename = mchcodename;
   }
	
   /**
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:deptid
    * 所属租赁中心的ID
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属租赁中心的ID
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
   
   /**
    * 获取属性:Deptname
    * 物资类型名称
    * @return Deptname
    */
   public String getDeptname() {
       return deptname;
   }
   /**
    * 设置属性:Deptname
    * 物资类型名称
    * @param Deptname
    */
   public void setDeptname(String deptname) {
       this.deptname = deptname;
   }
     
   /**
    * 获取属性:materialcode
    * 物资类型编号
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资类型编号
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
   }
   
   /**
    * 获取属性:materialcode
    * 物资类型名称
    * @return materialcode
    */
   public String getMaterialname() {
       return materialname;
   }
   /**
    * 设置属性:materialname
    * 物资类型名称
    * @param materialname
    */
   public void setMaterialname(String materialname) {
       this.materialname = materialname;
   }
   
	
   /**
    * 获取属性:totalS
    * 库存总数量
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 库存总数量
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:unit
    * 库存物资的单位
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * 库存物资的单位
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }
	
   /**
    * 获取属性:totalM
    * 库存的总米数
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 库存的总米数
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalT
    * 库存总重量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 库存总重量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
   }
	
   /**
    * 获取属性:modifytime
    * 最后修改时间
    * @return modifytime
    */
   public Date getModifytime() {
       return modifytime;
   }
   /**
    * 设置属性:modifytime
    * 最后修改时间
    * @param modifytime
    */
   public void setModifytime(Date modifytime) {
       this.modifytime = modifytime;
   }
   
   /**
    * 获取属性:changeType
    * 库存物资的单位
    * @return unit
    */
   public int getChangeType() {
       return changeType;
   }
   /**
    * 设置属性:changeType
    * 数据修改类型
    * @param changeType
    */
   public void setChangeType(int changeType) {
       this.changeType = changeType;
   }
   
   
   
   
   /**
    * 获取属性:isHave
    * 是否拥有此物料
    * @return isHave
    */
   public String getIsHave() {
       return isHave;
   }
   /**
    * 设置属性:isHave
    * 是否拥有此物料
    * @param isHave
    */
   public void setIsHave(String isHave) {
       this.isHave = isHave;
   }
   
   
   /**
    * 获取属性:unit
    * 库存物资的单位
    * @return unit
    */
   public String getQuantity() {
       return quantity;
   }
   /**
    * 设置属性:unit
    * 库存物资的单位
    * @param unit
    */
   public void setQuantity(String quantity) {
       this.quantity = quantity;
   }
   
   /**
    * 获取属性:cunit
    * 库存物资的单位
    * @return cunit
    */
   public String getCUnit() {
       return cunit;
   }
   /**
    * 设置属性:cunit
    * 库存物资的单位
    * @param cunit
    */
   public void setCUnit(String cunit) {
       this.cunit = cunit;
   }
	
   /**
    * 获取属性:ctotal
    * 库存的总米数
    * @return ctotal
    */
   public BigDecimal getCTotal() {
       return ctotal;
   }
   /**
    * 设置属性:totalM
    * 库存的总米数
    * @param totalM
    */
   public void setCTotal(BigDecimal ctotal) {
       this.ctotal = ctotal;
   }
   
   

}