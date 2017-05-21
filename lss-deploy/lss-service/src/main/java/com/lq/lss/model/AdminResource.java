package com.lq.lss.model;

import com.lq.easyui.model.easyui.EasyUiModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 
 * @since 2016-11-10
 */
public class AdminResource extends EasyUiModel<Integer> {



    /** 序列化ID */
    private static final long serialVersionUID = 1L;

    /** ID（手动维护） **/
    private Integer id;
    /** 代码字段（大写字母，下划线，数字组成，不能以数字打头），且只能初始化不能修改； **/
    private String code;
    /** 系统标识 **/
    private Long appId;
    /** 类型（1:菜单；2：操作或按钮 3: 虚拟菜单）  **/
    private Integer type;
    /** 资源名称 **/
    private String name;
    /** 是否叶子菜单 **/
    private Integer menuLeafFlag;
    /** 叶子菜单url **/
    private String menuLeafUrl;
    /** 叶子菜单打开方式（1：tab；2：dialogue） **/
    private Integer menuLeafMode;
    /** 上级ID **/
    private Long pid;
    /**  当前层级 **/
    private Integer layer;
    /** 排序 **/
    private Integer orderNo;
    /** 说明 **/
    private String remark;
    /** 创建时间 **/
    private Date createTime;


    /**
     * 获取属性:id
     * ID（手动维护）
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置属性:id
     * ID（手动维护）
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取属性:code
     * 代码字段（大写字母，下划线，数字组成，不能以数字打头），且只能初始化不能修改；
     * @return code
     */
    public String getCode() {
        return code;
    }
    /**
     * 设置属性:code
     * 代码字段（大写字母，下划线，数字组成，不能以数字打头），且只能初始化不能修改；
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取属性:appId
     * 系统标识
     * @return appId
     */
    public Long getAppId() {
        return appId;
    }
    /**
     * 设置属性:appId
     * 系统标识
     * @param appId
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 获取属性:type
     * 类型（1:菜单；2：操作或按钮 3: 虚拟菜单）
     * @return type
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置属性:type
     * 类型（1:菜单；2：操作或按钮 3: 虚拟菜单）
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取属性:name
     * 资源名称
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * 设置属性:name
     * 资源名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性:menuLeafFlag
     * 是否叶子菜单
     * @return menuLeafFlag
     */
    public Integer getMenuLeafFlag() {
        return menuLeafFlag;
    }
    /**
     * 设置属性:menuLeafFlag
     * 是否叶子菜单
     * @param menuLeafFlag
     */
    public void setMenuLeafFlag(Integer menuLeafFlag) {
        this.menuLeafFlag = menuLeafFlag;
    }

    /**
     * 获取属性:menuLeafUrl
     * 叶子菜单url
     * @return menuLeafUrl
     */
    public String getMenuLeafUrl() {
        return menuLeafUrl;
    }
    /**
     * 设置属性:menuLeafUrl
     * 叶子菜单url
     * @param menuLeafUrl
     */
    public void setMenuLeafUrl(String menuLeafUrl) {
        this.menuLeafUrl = menuLeafUrl;
    }

    /**
     * 获取属性:menuLeafMode
     * 叶子菜单打开方式（1：tab；2：dialogue）
     * @return menuLeafMode
     */
    public Integer getMenuLeafMode() {
        return menuLeafMode;
    }
    /**
     * 设置属性:menuLeafMode
     * 叶子菜单打开方式（1：tab；2：dialogue）
     * @param menuLeafMode
     */
    public void setMenuLeafMode(Integer menuLeafMode) {
        this.menuLeafMode = menuLeafMode;
    }

    /**
     * 获取属性:pid
     * 上级ID
     * @return pid
     */
    public Long getPid() {
        return pid;
    }
    /**
     * 设置属性:pid
     * 上级ID
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取属性:layer
     * 1
     * @return layer
     */
    public Integer getLayer() {
        return layer;
    }
    /**
     * 设置属性:layer
     * 1
     * @param layer
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    /**
     * 获取属性:orderNo
     * 排序
     * @return orderNo
     */
    public Integer getOrderNo() {
        return orderNo;
    }
    /**
     * 设置属性:orderNo
     * 排序
     * @param orderNo
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取属性:remark
     * 说明
     * @return remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置属性:remark
     * 说明
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取属性:createTime
     * 创建时间
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置属性:createTime
     * 创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}