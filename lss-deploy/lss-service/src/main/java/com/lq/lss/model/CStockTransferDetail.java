package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 * @author
 * @since 2016-08-31
 */
public class CStockTransferDetail extends EasyUiModel<String> {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    private String id;
    /**
     * 调拨单据流水号
     **/
    private String tsfSerialno;
    /**
     * 物资编号 b_material_info.code
     **/
    private String materialCode;
    /**
     * 调拨总长度 m
     **/
    private BigDecimal totalM;
    /**
     * 调拨总数量
     **/
    private BigDecimal totalS;
    /**
     * 调拨总量
     **/
    private BigDecimal totalT;
    /**
     * 状态  0：正常
     **/
    private String status;

    private String unit;

    private String materialName;

    private String typeId;

    private String fname;

    
    private String covertratio;
    private String subtotal;
    
    private String accountflag;
    
    
    private String name;
    /**
     * 获取属性:id
     * id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置属性:id
     * id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取属性:tsfSerialno
     * 调拨单据流水号
     *
     * @return tsfSerialno
     */
    public String getTsfSerialno() {
        return tsfSerialno;
    }

    /**
     * 设置属性:tsfSerialno
     * 调拨单据流水号
     *
     * @param tsfSerialno
     */
    public void setTsfSerialno(String tsfSerialno) {
        this.tsfSerialno = tsfSerialno;
    }

    /**
     * 获取属性:materialcode
     * 物资编号 b_material_info.code
     *
     * @return materialcode
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * 设置属性:materialcode
     * 物资编号 b_material_info.code
     *
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * 获取属性:totalM
     * 调拨总长度 m
     *
     * @return totalM
     */
    public BigDecimal getTotalM() {
        return totalM;
    }

    /**
     * 设置属性:totalM
     * 调拨总长度 m
     *
     * @param totalM
     */
    public void setTotalM(BigDecimal totalM) {
        this.totalM = totalM;
    }

    /**
     * 获取属性:totalS
     * 调拨总数量
     *
     * @return totalS
     */
    public BigDecimal getTotalS() {
        return totalS;
    }

    /**
     * 设置属性:totalS
     * 调拨总数量
     *
     * @param totalS
     */
    public void setTotalS(BigDecimal totalS) {
        this.totalS = totalS;
    }

    /**
     * 获取属性:totalT
     * 调拨总量
     *
     * @return totalT
     */
    public BigDecimal getTotalT() {
        return totalT;
    }

    /**
     * 设置属性:totalT
     * 调拨总量
     *
     * @param totalT
     */
    public void setTotalT(BigDecimal totalT) {
        this.totalT = totalT;
    }

    /**
     * 获取属性:status
     * 状态  0：正常
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置属性:status
     * 状态  0：正常
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

	public String getCovertratio() {
		return covertratio;
	}

	public void setCovertratio(String covertratio) {
		this.covertratio = covertratio;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getAccountflag() {
		return accountflag;
	}

	public void setAccountflag(String accountflag) {
		this.accountflag = accountflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
    
}