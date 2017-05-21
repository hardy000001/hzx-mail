/**
 * 
 */
package com.lq.lss.utils.exception.jta;

/**
 * 主要用于事务回滚专用
 * @author lanbo
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1359764980554993221L;
	private String errorMsg;
	private Exception exception;
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	

	public BusinessException(){
		
	}
	public BusinessException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	
	public BusinessException(String errorMsg, Exception exception) {
		super();
		this.errorMsg = errorMsg;
		this.exception = exception;
	}
	
	
	
	
	
	
	
	

	
}
