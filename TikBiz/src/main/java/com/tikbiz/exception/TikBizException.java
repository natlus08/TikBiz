/**
 * 
 */
package com.tikbiz.exception;

/**
 * @author 387090
 *
 */
public class TikBizException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TikBizException() {
		super();
	}
	
	public TikBizException(String msg) {
		super(msg);
	}
	
	public TikBizException(Throwable thr) {
		super(thr);
	}
	
	public TikBizException(String msg,Throwable thr) {
		super(msg, thr);
	}
}
