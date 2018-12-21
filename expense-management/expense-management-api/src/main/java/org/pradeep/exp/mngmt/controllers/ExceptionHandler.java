package org.pradeep.exp.mngmt.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pradeep.exp.mngmt.beans.ExpenseOutput;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
