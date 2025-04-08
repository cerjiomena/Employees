package com.employees.error;

import com.employees.util.MessageError;

public class AppException extends Exception {
	

	private static final long serialVersionUID = 1L;
	private static MessageError code = null;

	public AppException(String msj){
		super(msj);
	}
	
	public AppException(String message, MessageError codeE) {
        super(message);
        code = codeE;
    }
	
	public AppException(MessageError codeE) {
       code = codeE;
    }
	
	public MessageError getCodeError() {
        return code;
    }

}
