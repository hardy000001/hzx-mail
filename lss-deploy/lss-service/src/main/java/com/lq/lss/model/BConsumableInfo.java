package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 * @author
 * @since 2016-08-31
 */
public class BConsumableInfo extends EasyUiModel<Integer> {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    private Integer id;
    /**
     * 所属易耗品类别
     **/
    private Integer typeid;
    /**
     * 易耗品名称
     **/
    private String name;
    /**
     * 单价
     **/
    private BigDecimal price;
    /**
     * 单位
     **/
    private String unit;
    /**
     * 库存总数量
     **/
    private Integer quantity;
    /**
     * 状态 0：正常 9：禁用
     **/
    private String status;
    /**
     * 创建时间
     **/
    private Date createtime;
    private String pyCode;
    private String typeName;
    private String code;


    /**
     * 获取属性:id
     * id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置属性:id
     * id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取属性:typeid
     * 所属易耗品类别
     *
     * @return typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 设置属性:typeid
     * 所属易耗品类别
     *
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取属性:name
     * 易耗品名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性:name
     * 易耗品名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性:price
     * 单价
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置属性:price
     * 单价
     *
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取属性:unit
     * 单位
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置属性:unit
     * 单位
     *
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取属性:quantity
     * 库存总数量
     *
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置属性:quantity
     * 库存总数量
     *
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取属性:status
     * 状态 0：正常 9：禁用
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置属性:status
     * 状态 0：正常 9：禁用
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取属性:createtime
     * 创建时间
     *
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置属性:createtime
     * 创建时间
     *
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}