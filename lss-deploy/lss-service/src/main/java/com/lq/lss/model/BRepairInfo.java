package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class BRepairInfo extends EasyUiModel<Integer> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 序号，自增 **/
    private Integer id;
	/** 物资类别   b_material_type.typeId **/
    private Integer typeid;
	/** 维修项目名称 **/
    private String name;
	/** 费用比例 倍数 **/
    private BigDecimal feePercant;
	/** 费用单位 **/
    private String feeUnit;
	/** 状态 0：正常 9：禁用 **/
    private String status;
    private Integer flag;
    
    private String tName; //物资名
	
   /**
    * 获取属性:id
    * 序号，自增
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 序号，自增
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:typeid
    * 物资类别   b_material_type.typeId
    * @return typeid
    */
   public Integer getTypeid() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 物资类别   b_material_type.typeId
    * @param typeid
    */
   public void setTypeid(Integer typeid) {
       this.typeid = typeid;
   }
	
   /**
    * 获取属性:name
    * 维修项目名称
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * 维修项目名称
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * 获取属性:feePercant
    * 费用比例 倍数
    * @return feePercant
    */
   public BigDecimal getFeePercant() {
       return feePercant;
   }
   /**
    * 设置属性:feePercant
    * 费用比例 倍数
    * @param feePercant
    */
   public void setFeePercant(BigDecimal feePercant) {
       this.feePercant = feePercant;
   }
	
   /**
    * 获取属性:feeUnit
    * 费用单位
    * @return feeUnit
    */
   public String getFeeUnit() {
       return feeUnit;
   }
   /**
    * 设置属性:feeUnit
    * 费用单位
    * @param feeUnit
    */
   public void setFeeUnit(String feeUnit) {
       this.feeUnit = feeUnit;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常 9：禁用
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常 9：禁用
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
    
}