package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class BConsumableType extends EasyUiModel<Integer> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 类别ID **/
    private Integer typeid;
	/** 父ID **/
    private Integer parentid;
	/** 统计排序序号 **/
    private Integer position;
	/** 名称 **/
    private String name;
	/** 单价 **/
    private BigDecimal price;
	/** 单位 **/
    private String unit;
	/** 状态 0：正常 9：禁用 **/
    private String status;

    /** id **/
    private Integer id;
    /**
     * 获取属性:id
     * id
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置属性:id
     * id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
   /**
    * 获取属性:typeid
    * 类别ID
    * @return typeid
    */
   public Integer getTypeid() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 类别ID
    * @param typeid
    */
   public void setTypeid(Integer typeid) {
       this.typeid = typeid;
   }
	
   /**
    * 获取属性:parentid
    * 父ID
    * @return parentid
    */
   public Integer getParentid() {
       return parentid;
   }
   /**
    * 设置属性:parentid
    * 父ID
    * @param parentid
    */
   public void setParentid(Integer parentid) {
       this.parentid = parentid;
   }
	
   /**
    * 获取属性:position
    * 统计排序序号
    * @return position
    */
   public Integer getPosition() {
       return position;
   }
   /**
    * 设置属性:position
    * 统计排序序号
    * @param position
    */
   public void setPosition(Integer position) {
       this.position = position;
   }
	
   /**
    * 获取属性:name
    * 名称
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * 名称
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * 获取属性:price
    * 单价
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 单价
    * @param price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
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

}