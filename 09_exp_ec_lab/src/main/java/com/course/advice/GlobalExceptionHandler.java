package com.course.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiResponse<Map<String, String>> excepcionHandler(MethodArgumentNotValidException e) {
		
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
}
