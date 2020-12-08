package com.cy.asset.login.controller;

import com.cy.asset.common.exception.UnauthorizedException;
import com.cy.asset.common.response.SuccessResponse;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王成
 * @date 2020/11/24
 * @description
 **/
@RestControllerAdvice
public class ExceptionController {

    /** 捕捉shiro的异常 **/
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public SuccessResponse handle401(ShiroException e) {
        return new SuccessResponse(e.getMessage());
    }

    /** 捕捉UnauthorizedException **/
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public SuccessResponse handle401() {
        return new SuccessResponse("Unauthorized");
    }

    /** 捕捉其他所有异常 **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SuccessResponse globalException(HttpServletRequest request, Throwable ex) {
        return new SuccessResponse(ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
