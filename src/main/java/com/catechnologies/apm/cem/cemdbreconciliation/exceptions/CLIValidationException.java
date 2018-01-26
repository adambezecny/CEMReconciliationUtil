package com.catechnologies.apm.cem.cemdbreconciliation.exceptions;

/**
 * This exception is thrown by command line data validator class (CLIValidator) in case
 * of wrong command line syntax
 * @author bezad01
 *
 */
public class CLIValidationException extends Exception {

	private static final long serialVersionUID = -7917836296134467309L;

	public static final int INVALID_REASON_NO_ENTITY_TYPE = 1;
	public static final int INVALID_REASON_WRONG_ENTITY_TYPE = 2;
	public static final int INVALID_REASON_NO_ENTITY_NAME = 3;
	public static final int INVALID_REASON_NO_EXPORT_FILE = 4;
	public static final int INVALID_REASON_MISSING_BANAME = 5;
	public static final int INVALID_REASON_MISSING_BA_OR_BS_NAME = 6;
	
	String message;
	int invalidReason;
	
	public CLIValidationException(String message){
		super(message);
	}

	public CLIValidationException(String message, int invalidReason){
		this(message);
		this.message=message;
		this.invalidReason=invalidReason;
	}

	public String getMessage() {
		return message;
	}


	public int getInvalidReason() {
		return invalidReason;
	}
	
}
