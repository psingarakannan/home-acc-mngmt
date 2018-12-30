package org.pradeep.txn.mngmt.api.controllers;

import org.pradeep.platform.beans.ExceptionOutput;
import org.pradeep.platform.beans.ExpenseOutput;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author psingarakannan on 19/12/18
 **/
@ControllerAdvice(basePackages = {"org.pradeep"})
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public @ResponseBody    ExceptionOutput defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace ();
        ExceptionOutput exceptionOutput =  new ExceptionOutput ();
        exceptionOutput.setSuccess ( false );
        exceptionOutput.setException ( e.getClass ().getName () );
        exceptionOutput.setErrorMessage ( e.getMessage () );
        return exceptionOutput;
    }
}
