package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
public class CStockTotalflow extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** 单据号**/
    private String orderNo;
	/** 自增序号**/
    private String id;
	/** 所属租赁中心的ID**/
    private Integer deptid;
	/** 源商户号**/
    private String fromMchcode;
	/** 目的商户号**/
    private String toMchcode;
	/** 交易类型**/
    private String orderType;
    
	/** 运输费用**/
    private BigDecimal transportFee;
	/** 装卸费用**/
    private BigDecimal zxFee;
	/** 杂费**/
    private BigDecimal otherFee;
	/** 钢管汇总**/
    private BigDecimal totalGguan;
	/** 扣件汇总**/
    private BigDecimal totalKjian;
	/** 短管汇总**/
    private BigDecimal totalDguan;
	/** 套管汇总**/
    private BigDecimal totalTguan;
	/** 轮扣架汇总**/
    private BigDecimal totalLkjia;
	/** 槽钢汇总**/
    private BigDecimal totalCgang;
	/** 12工字钢汇总**/
    private BigDecimal totalTwgzgang;
	/** 工字钢汇总**/
    private BigDecimal totalGzgang;
	/** 铁网汇总**/
    private BigDecimal totalTwang;
	/** 备用1汇总**/
    private BigDecimal totalItem1;
	/** 备用2汇总**/
    private BigDecimal totalItem2;
	/** 交易时间**/
    private Date tradetime;
    /** 合同编号**/
    private String htcode;
    /** 物资分类Id **/
    private String typeid;
	/** 汇总数据**/
    private BigDecimal totalAmt;
    
    
    private String tradeDate;
    private String mchname;
    private String toMchname;
    private String deptname; //中心名称
    /** 合同名**/
    private String htname;
	
   /**
    * 获取属性:orderNo
    * 单据号
    * @return orderNo
    */
   public String getOrderNo() {
       return orderNo;
   }
   /**
    * 设置属性:orderNo
    * 单据号
    * @param orderNo
    */
   public void setOrderNo(String orderNo) {
       this.orderNo = orderNo;
   }
	
   /**
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(String id) {
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
    * 获取属性:fromMchcode
    * 源商户号
    * @return fromMchcode
    */
   public String getFromMchcode() {
       return fromMchcode;
   }
   /**
    * 设置属性:fromMchcode
    * 源商户号
    * @param fromMchcode
    */
   public void setFromMchcode(String fromMchcode) {
       this.fromMchcode = fromMchcode;
   }
	
   /**
    * 获取属性:toMchcode
    * 目的商户号
    * @return toMchcode
    */
   public String getToMchcode() {
       return toMchcode;
   }
   /**
    * 设置属性:toMchcode
    * 目的商户号
    * @param toMchcode
    */
   public void setToMchcode(String toMchcode) {
       this.toMchcode = toMchcode;
   }
	
   /**
    * 获取属性:orderType
    * 交易类型
    * @return orderType
    */
   public String getOrderType() {
       return orderType;
   }
   /**
    * 设置属性:orderType
    * 交易类型
    * @param orderType
    */
   public void setOrderType(String orderType) {
       this.orderType = orderType;
   }
	
   /**
    * 获取属性:transportFee
    * 运输费用
    * @return transportFee
    */
   public BigDecimal getTransportFee() {
       return transportFee;
   }
   /**
    * 设置属性:transportFee
    * 运输费用
    * @param transportFee
    */
   public void setTransportFee(BigDecimal transportFee) {
       this.transportFee = transportFee;
   }
	
   /**
    * 获取属性:zxFee
    * 装卸费用
    * @return zxFee
    */
   public BigDecimal getZxFee() {
       return zxFee;
   }
   /**
    * 设置属性:zxFee
    * 装卸费用
    * @param zxFee
    */
   public void setZxFee(BigDecimal zxFee) {
       this.zxFee = zxFee;
   }
	
   /**
    * 获取属性:otherFee
    * 杂费
    * @return otherFee
    */
   public BigDecimal getOtherFee() {
       return otherFee;
   }
   /**
    * 设置属性:otherFee
    * 杂费
    * @param otherFee
    */
   public void setOtherFee(BigDecimal otherFee) {
       this.otherFee = otherFee;
   }
	
   /**
    * 获取属性:totalGguan
    * 钢管汇总
    * @return totalGguan
    */
   public BigDecimal getTotalGguan() {
       return totalGguan;
   }
   /**
    * 设置属性:totalGguan
    * 钢管汇总
    * @param totalGguan
    */
   public void setTotalGguan(BigDecimal totalGguan) {
       this.totalGguan = totalGguan;
   }
	
   /**
    * 获取属性:totalKjian
    * 扣件汇总
    * @return totalKjian
    */
   public BigDecimal getTotalKjian() {
       return totalKjian;
   }
   /**
    * 设置属性:totalKjian
    * 扣件汇总
    * @param totalKjian
    */
   public void setTotalKjian(BigDecimal totalKjian) {
       this.totalKjian = totalKjian;
   }
	
   /**
    * 获取属性:totalDguan
    * 短管汇总
    * @return totalDguan
    */
   public BigDecimal getTotalDguan() {
       return totalDguan;
   }
   /**
    * 设置属性:totalDguan
    * 短管汇总
    * @param totalDguan
    */
   public void setTotalDguan(BigDecimal totalDguan) {
       this.totalDguan = totalDguan;
   }
	
   /**
    * 获取属性:totalTguan
    * 套管汇总
    * @return totalTguan
    */
   public BigDecimal getTotalTguan() {
       return totalTguan;
   }
   /**
    * 设置属性:totalTguan
    * 套管汇总
    * @param totalTguan
    */
   public void setTotalTguan(BigDecimal totalTguan) {
       this.totalTguan = totalTguan;
   }
	
   /**
    * 获取属性:totalLkjia
    * 轮扣架汇总
    * @return totalLkjia
    */
   public BigDecimal getTotalLkjia() {
       return totalLkjia;
   }
   /**
    * 设置属性:totalLkjia
    * 轮扣架汇总
    * @param totalLkjia
    */
   public void setTotalLkjia(BigDecimal totalLkjia) {
       this.totalLkjia = totalLkjia;
   }
	
   /**
    * 获取属性:totalCgang
    * 槽钢汇总
    * @return totalCgang
    */
   public BigDecimal getTotalCgang() {
       return totalCgang;
   }
   /**
    * 设置属性:totalCgang
    * 槽钢汇总
    * @param totalCgang
    */
   public void setTotalCgang(BigDecimal totalCgang) {
       this.totalCgang = totalCgang;
   }
	
   /**
    * 获取属性:totalTwgzgang
    * 12工字钢汇总
    * @return totalTwgzgang
    */
   public BigDecimal getTotalTwgzgang() {
       return totalTwgzgang;
   }
   /**
    * 设置属性:totalTwgzgang
    * 12工字钢汇总
    * @param totalTwgzgang
    */
   public void setTotalTwgzgang(BigDecimal totalTwgzgang) {
       this.totalTwgzgang = totalTwgzgang;
   }
	
   /**
    * 获取属性:totalGzgang
    * 工字钢汇总
    * @return totalGzgang
    */
   public BigDecimal getTotalGzgang() {
       return totalGzgang;
   }
   /**
    * 设置属性:totalGzgang
    * 工字钢汇总
    * @param totalGzgang
    */
   public void setTotalGzgang(BigDecimal totalGzgang) {
       this.totalGzgang = totalGzgang;
   }
	
   /**
    * 获取属性:totalTwang
    * 铁网汇总
    * @return totalTwang
    */
   public BigDecimal getTotalTwang() {
       return totalTwang;
   }
   /**
    * 设置属性:totalTwang
    * 铁网汇总
    * @param totalTwang
    */
   public void setTotalTwang(BigDecimal totalTwang) {
       this.totalTwang = totalTwang;
   }
	
   /**
    * 获取属性:totalItem1
    * 备用1汇总
    * @return totalItem1
    */
   public BigDecimal getTotalItem1() {
       return totalItem1;
   }
   /**
    * 设置属性:totalItem1
    * 备用1汇总
    * @param totalItem1
    */
   public void setTotalItem1(BigDecimal totalItem1) {
       this.totalItem1 = totalItem1;
   }
	
   /**
    * 获取属性:totalItem2
    * 备用2汇总
    * @return totalItem2
    */
   public BigDecimal getTotalItem2() {
       return totalItem2;
   }
   /**
    * 设置属性:totalItem2
    * 备用2汇总
    * @param totalItem2
    */
   public void setTotalItem2(BigDecimal totalItem2) {
       this.totalItem2 = totalItem2;
   }
   /**
    * 获取属性:tradetime
    * 交易时间
    * @return tradetime
    */
   public Date getTradetime() {
       return tradetime;
   }
   /**
    * 设置属性:tradetime
    * 交易时间
    * @param tradetime
    */
   public void setTradetime(Date tradetime) {
       this.tradetime = tradetime;
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
    * 获取属性:typeid
    * 物资大类ID
    * @return typeid
    */
   public String getTypeId() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 物资大类ID
    * @param typeid
    */
   public void setTypeId(String typeid) {
       this.typeid = typeid;
   }
   
   
   /**
    * 获取属性:totalAmt
    * 数据汇总
    * @return totalAmt
    */
   public BigDecimal getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalItem2
    * 数据汇总
    * @param totalItem2
    */
   public void setTotalAmt(BigDecimal totalAmt) {
       this.totalAmt = totalAmt;
   }
   
   
	public String getTradeDate() {
	return tradeDate;
    }
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getMchname() {
		return mchname;
	}
	public void setMchname(String mchname) {
		this.mchname = mchname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getHtname() {
		return htname;
	}
	public void setHtname(String htname) {
		this.htname = htname;
	}
	public String getToMchname() {
		return toMchname;
	}
	public void setToMchname(String toMchname) {
		this.toMchname = toMchname;
	}
    
}