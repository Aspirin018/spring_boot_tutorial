package com.example.space.framework.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyu
 * @date 18-7-28
 */
@ControllerAdvice
//@RestControllerAdvice  = @ControllerAdvice + @ResponseBody , 如果返回页面，则使用@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request, MyException e){
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setCode(e.getMsgEnum().getCode());
        errorInfo.setMessage(e.getMsgEnum().getMessage());
        errorInfo.setData("some data");
        errorInfo.setUrl(request.getRequestURL().toString());
        return  errorInfo;
    }

}
