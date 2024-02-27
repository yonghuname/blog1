package com.eoft.blog2.handler;

import com.eoft.blog2.web.NoFoundException;
import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.http.HttpStatus.ResponseStatus;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;


@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    /**
     * 异常处理
     * @param request
     * @param e
     * @throws Exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {
        System.out.println("handleException被调用----------");
        logger.error("Request URL:{},Exception:{}", request.getRequestURL(), e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=null) {
            throw e;
        }

        // 如果异常已经被标记为某个特定的HTTP响应状态，直接抛出
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());

        if (e instanceof NoFoundException) {
            mav.setViewName("error/404");
        }  else if (e instanceof NoResourceFoundException){
            mav.setViewName("error/404");

        }
            else {
            mav.setViewName("error/error");
        }


//        mav.setViewName("error/error");
        mav.addObject("exception", e);
        return mav;
    }
}


// todo 完善 错误抛出前端 html ，解决  。



