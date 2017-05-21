package com.lq.lss.model;

public class CompensateDetail {

		private String htCode;
		
		private String comName;
		
		private String mchCode;
	
		/** 物资编号 b_material_info.code**/
	    private String materialcode;
	    
	    private String name;
		/** 入库总数量  **/
	    private String totalS;
		/** 赔偿单价**/
	    private String amt;
		/** unit**/
	    private String unit;
	    
	    private String fname;
	    private String typeId;
	    private String covertratio;
	    private String accountflag;
	    
		public String getHtCode() {
			return htCode;
		}
		public void setHtCode(String htCode) {
			this.htCode = htCode;
		}
		public String getComName() {
			return comName;
		}
		public void setComName(String comName) {
			this.comName = comName;
		}
		public String getMchCode() {
			return mchCode;
		}
		public void setMchCode(String mchCode) {
			this.mchCode = mchCode;
		}
		public String getMaterialcode() {
			return materialcode;
		}
		public void setMaterialcode(String materialcode) {
			this.materialcode = materialcode;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTotalS() {
			return totalS;
		}
		public void setTotalS(String totalS) {
			this.totalS = totalS;
		}
		public String getAmt() {
			return amt;
		}
		public void setAmt(String amt) {
			this.amt = amt;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		public String getCovertratio() {
			return covertratio;
		}
		public void setCovertratio(String covertratio) {
			this.covertratio = covertratio;
		}
		public String getAccountflag() {
			return accountflag;
		}
		public void setAccountflag(String accountflag) {
			this.accountflag = accountflag;
		}
	    
	    
	    
	    
	    
}
