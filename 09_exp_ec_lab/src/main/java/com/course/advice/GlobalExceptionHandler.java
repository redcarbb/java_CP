package com.course.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.exception.ActionException;
import com.course.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiResponse<Map<String, String>> exceptionHandler(MethodArgumentNotValidException e) {
		
		logger.error("出事了!!!!", e);
		
        Map<String, String> errorMap = new HashMap<>();

        // 把所有欄位錯誤放入 map 中
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
        	System.out.println(fieldError.getDefaultMessage());
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        // 回傳自訂格式的錯誤回應（HTTP 200，responseCode 代表錯誤）
        ApiResponse<Map<String, String>> response = ApiResponse.error("401-999", "參數驗證錯誤", errorMap);

        return response;
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ApiResponse<Map<String, String>> allExceptionHandler(Exception e) {
//		
//        // 回傳自訂格式的錯誤回應（HTTP 200，responseCode 代表錯誤）
//        ApiResponse<Map<String, String>> response = ApiResponse.error("505", "系統忙碌中", null);
//
//        return response;
//	}
//	
//	@ExceptionHandler(value = ActionException.class)
//	public ApiResponse<Map<String, String>> actionExceptionHandler(ActionException ae) {
//		logger.error("Action error", ae);
//        ApiResponse<Map<String, String>> response = ApiResponse.error("999", "系統忙碌中，請稍後再試", null);
//
//        return response;
//	}
//	
	
}
