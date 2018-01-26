package com.catechnologies.apm.cem.cemdbreconciliation.exceptions;

/**
 * Database exception thrown by AppDAO class
 * @author bezad01
 *
 */
public class AppDAOException extends Exception {

	private static final long serialVersionUID = 1736093487790643969L;
	
	public static final int REASON_BA_NOT_FOUND_BY_ID = 1;
	public static final int REASON_BS_NOT_FOUND_BY_ID = 2;
	public static final int REASON_BT_NOT_FOUND_BY_ID = 3;
	
	String message;
	int reason;
	

	public AppDAOException(String message){
		super(message);
	}
	
	
	public AppDAOException(String message, int reason) {
		this(message);
		this.message = message;
		this.reason = reason;
	}


	public String getMessage() {
		return message;
	}
	
	public int getReason() {
		return reason;
	}
	
	
	
}
