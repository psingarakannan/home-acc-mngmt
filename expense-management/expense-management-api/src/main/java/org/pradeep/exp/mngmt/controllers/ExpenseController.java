package org.pradeep.exp.mngmt.controllers;

import org.pradeep.exp.mngmt.beans.ExpenseInput;
import org.pradeep.exp.mngmt.beans.ExpenseOutput;
import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.service.ExpenseEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psingarakannan on 9/12/18
 **/

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    @Qualifier("expenseEntityService")
    private ExpenseEntityService expenseEntityService;
    @GetMapping ("/ping")
    public String ping(){
        return "pong";
    }


    @GetMapping ("/fetchAll")
    public List<ExpenseOutput> fetchAllExpenses(){
        return expenseEntityService
                .findAll ()
                .parallelStream ()
                .map ( (source) -> {
                    ExpenseOutput expenseOutput = new ExpenseOutput ( );
                    BeanUtils.copyProperties ( source, expenseOutput );
                    return expenseOutput;
                })
                .collect ( Collectors.toList ( ) );
    }


    @PostMapping ("/post")
    public @ResponseBody  ExpenseOutput addExpenses(@RequestBody ExpenseInput expenseInput){

        Expense expense = new Expense () ;
        BeanUtils.copyProperties ( expenseInput, expense );
        expenseEntityService.saveOrUpdate ( expense );

        return new ExpenseOutput ();
    }



}
