package com.employees.util;

public enum  MessageError {
	ERROR_GENERAL("error.general"),
	ERROR_EMPLOYEE_DOES_NOT_EXIST("error.user_not_found"), ERROR_DATA_INTEGRITY("error.data.integrity"), ERROR_DATA_ACCESS("error.data.access");
	
	
	
	/**
	 * llave en message bundle
	 */
	private String keyMessage;

	MessageError(String keyMessage) {
		this.keyMessage = keyMessage;
	}

	public String getKeyMessage() {
		return keyMessage;
	}

}
