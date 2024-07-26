package com.example.demo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.ServletException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException() {
        logger.error("페이지를 찾을 수 없습니다.");
        return "404";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException() {
        logger.error("허용되지 않는 HTTP 메서드입니다.");
        return "405";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        logger.error("알 수 없는 예외가 발생했습니다.", ex);
        return "500";
    }

    @ExceptionHandler(ServletException.class)
    public String handleServletExcption(ServletException ex) {
        logger.error("서블릿 예외가 발생했습니다.", ex);
        return "404";
    }
    
    
}
