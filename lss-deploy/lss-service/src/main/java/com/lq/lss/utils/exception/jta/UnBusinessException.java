/**
 * 
 */
package com.lq.lss.utils.exception.jta;

/**
 * 主要用于事务不回滚专用
 * @author lanbo
 *
 */
public class UnBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829861279963760108L;

	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	public UnBusinessException() {
		super();
	}

	public UnBusinessException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	
	
}
