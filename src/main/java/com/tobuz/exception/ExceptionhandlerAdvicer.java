package com.tobuz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.tobuz.constant.Constants;
import com.tobuz.dto.ResponseDTO;

@RestControllerAdvice
public class ExceptionhandlerAdvicer {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionhandlerAdvicer.class);
	
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	    public ResponseEntity<ResponseDTO<String>> handleMaxUploadSizeExceededException(Exception ex){
		 logger.error("File size exceeds limit of 10 MB"); 
		 ResponseDTO<String> response = new ResponseDTO<String>();	     
			response.setMessage("File size exceeds limit of 10 MB");
			response.setStatus(Constants.STATUS_FAILED);
			return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(response);
		 
	    }
}
