package org.pradeep.exp.mngmt.controllers;

import org.pradeep.platform.beans.ExpenseOutput;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author psingarakannan on 19/12/18
 **/
@ControllerAdvice(basePackages = {"org.pradeep.exp.mngmt"})
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody

    public ExpenseOutput defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace ();
        ExpenseOutput expenseOutput =  new ExpenseOutput ();
        expenseOutput.setSuccess ( false );
        expenseOutput.setException ( e.getClass ().getName () );
        expenseOutput.setErrorMessage ( e.getMessage () );
        return expenseOutput;
    }
}
