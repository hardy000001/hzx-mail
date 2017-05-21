package com.lq.lss.dto;

import java.math.BigDecimal;


/**
 *
 */
public class StockTransferDetailDto {



    /** id **/
    private Integer id;
    /** 调拨单据流水号 **/
    private String tsfSerialno;
    /** 物资编号 b_material_info.code **/
    private String materialcode;
    /** 调拨总长度 m **/
    private BigDecimal totalM;
    /** 调拨总数量   **/
    private BigDecimal totalS;
    /** 调拨总量 **/
    private BigDecimal totalT;
    /** 状态  0：正常   **/
    private String status;


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
     * 获取属性:tsfSerialno
     * 调拨单据流水号
     * @return tsfSerialno
     */
    public String getTsfSerialno() {
        return tsfSerialno;
    }
    /**
     * 设置属性:tsfSerialno
     * 调拨单据流水号
     * @param tsfSerialno
     */
    public void setTsfSerialno(String tsfSerialno) {
        this.tsfSerialno = tsfSerialno;
    }

    /**
     * 获取属性:materialcode
     * 物资编号 b_material_info.code
     * @return materialcode
     */
    public String getMaterialcode() {
        return materialcode;
    }
    /**
     * 设置属性:materialcode
     * 物资编号 b_material_info.code
     * @param materialcode
     */
    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    /**
     * 获取属性:totalM
     * 调拨总长度 m
     * @return totalM
     */
    public BigDecimal getTotalM() {
        return totalM;
    }
    /**
     * 设置属性:totalM
     * 调拨总长度 m
     * @param totalM
     */
    public void setTotalM(BigDecimal totalM) {
        this.totalM = totalM;
    }

    /**
     * 获取属性:totalS
     * 调拨总数量
     * @return totalS
     */
    public BigDecimal getTotalS() {
        return totalS;
    }
    /**
     * 设置属性:totalS
     * 调拨总数量
     * @param totalS
     */
    public void setTotalS(BigDecimal totalS) {
        this.totalS = totalS;
    }

    /**
     * 获取属性:totalT
     * 调拨总量
     * @return totalT
     */
    public BigDecimal getTotalT() {
        return totalT;
    }
    /**
     * 设置属性:totalT
     * 调拨总量
     * @param totalT
     */
    public void setTotalT(BigDecimal totalT) {
        this.totalT = totalT;
    }

    /**
     * 获取属性:status
     * 状态  0：正常
     * @return status
     */
    public String getStatus() {
        return status;
    }
    /**
     * 设置属性:status
     * 状态  0：正常
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
	

}