package org.pradeep.txn.mngmt.api.controllers;

import org.pradeep.acc.mngmt.entities.Account;
import org.pradeep.acc.mngmt.entities.service.AccountService;
import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.platform.beans.AccountInput;
import org.pradeep.platform.beans.AccountOutput;
import org.pradeep.platform.beans.ExpenseInput;
import org.pradeep.platform.beans.ExpenseOutput;
import org.pradeep.txn.mngmt.services.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psingarakannan on 30/12/18
 **/

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/fetchAll")
    public List<AccountOutput> fetchAllExpenses(){
        return accountService
                .findAll ()
                .parallelStream ()
                .map ( (source) -> {
                    AccountOutput accountOutput = new AccountOutput ( );
                    BeanUtils.copyProperties ( source, accountOutput );
                    return accountOutput;
                })
                .collect ( Collectors.toList ( ) );
    }


    @PostMapping("/add")
    public @ResponseBody AccountOutput addExpenses(@RequestBody AccountInput accountInput){

        Account account = new Account () ;
        BeanUtils.copyProperties ( accountInput, account );
        accountService.saveOrUpdate ( account );
        return new AccountOutput ();

    }

}
