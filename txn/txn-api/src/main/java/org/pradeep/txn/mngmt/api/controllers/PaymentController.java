package org.pradeep.txn.mngmt.api.controllers;

import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.service.ExpenseEntityService;
import org.pradeep.platform.beans.*;
import org.pradeep.txn.mngmt.services.PaymentService;
import org.pradeep.txn.mngmt.services.XLService;
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
@RequestMapping("/txn")
public class PaymentController {

    @Autowired
    @Qualifier("expenseEntityService")
    private ExpenseEntityService expenseEntityService;

    @Autowired
    private XLService xlService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/ping")
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


    @PostMapping("/post")
    public @ResponseBody
    ExpenseOutput addExpenses(@RequestBody ExpenseInput expenseInput){

        Expense expense = new Expense () ;
        BeanUtils.copyProperties ( expenseInput, expense );
        expenseEntityService.saveOrUpdate ( expense );

        return new ExpenseOutput ();
    }

    @PostMapping ("/excel")
    public @ResponseBody  ExpenseOutput addExpensesThruExcel(@RequestBody ExcelInput excelInput) throws Exception{

        List<ExpenseInput> expenseInputList = xlService.mapExcelToExpenseInput ( excelInput );

        expenseInputList
                .forEach ( expenseInput1->{
                    Expense expense = new Expense () ;
                    BeanUtils.copyProperties ( expenseInput1, expense );
                    expenseEntityService.saveOrUpdate ( expense );
                } );
        return new ExpenseOutput ();
    }
    @PostMapping ("/pay")
    public @ResponseBody
    TxnOutput pay(@RequestBody TxnInput txnInput) throws Exception{

        return paymentService.pay ( txnInput );
    }
}
