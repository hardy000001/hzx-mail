package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 13:27:03
 */
public class BusHtRepairinfoDto{

	
	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
	/** 物资简码**/
    private String materialcode;
	/** 单位**/
    private String unit;
	/** 维修价格**/
    private String price;
	/** 维修项目**/
    private Integer repairId;
    /** 维修项目名称 **/
    private String name;
    private String tName; //物资名
    /** 物资类别   b_material_type.typeId **/
    private Integer typeid;
    

 
	
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
    * 获取属性:materialcode
    * 物资简码
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资简码
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
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
    * 获取属性:price
    * 维修价格
    * @return price
    */
   public String getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 维修价格
    * @param price
    */
   public void setPrice(String price) {
       this.price = price;
   }
	
   /**
    * 获取属性:repairId
    * 维修项目
    * @return repairId
    */
   public Integer getRepairId() {
       return repairId;
   }
   /**
    * 设置属性:repairId
    * 维修项目
    * @param repairId
    */
   public void setRepairId(Integer repairId) {
       this.repairId = repairId;
   }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
    
}