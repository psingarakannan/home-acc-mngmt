package org.pradeep.txn.mngmt.services;

import org.pradeep.acc.mngmt.entities.Account;
import org.pradeep.acc.mngmt.entities.service.AccountService;
import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.service.ExpenseEntityService;
import org.pradeep.platform.beans.ExcelInput;
import org.pradeep.platform.beans.ExpenseInput;
import org.pradeep.platform.beans.TxnInput;
import org.pradeep.platform.beans.TxnOutput;
import org.pradeep.platform.enums.TxnStatus;
import org.pradeep.platform.enums.TxnType;
import org.pradeep.txn.mngmt.entities.Txn;
import org.pradeep.txn.mngmt.entities.service.TxnService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psingarakannan on 28/12/18
 **/

@Service
public class PaymentService {

    @Autowired
    private XLService xlService;

    @Autowired
    private TxnService txnService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExpenseEntityService expenseEntityService;


    public List<TxnOutput> excelPay(ExcelInput excelInput) throws Exception{

        List<TxnInput> txnInputs = xlService.mapExpenseToTxnInput ( excelInput );
        return
                txnInputs
                        .stream ()
                        .map ( this::pay )
                        .collect ( Collectors.toList () );

    }

    public TxnOutput pay(TxnInput txnInput) {
        TxnOutput txnOutput = new TxnOutput ();
        Txn txn ; Account account ;
        ExpenseInput expenseInput = txnInput.getExpenseInput ( );
        Expense expense;
        Long originalBalance = null;
        massageTxnInput ( expenseInput, txnInput );
        txn = txnService.createTxn ( txnInput );
        txnInput.setTxnId ( txn.getId () );
        System.out.println ( "Txn created with id " + txn.getId ( ) );
        expense = expenseEntityService.createExpense ( expenseInput );
        System.out.println ( "Expense created with id " + expense.getId ( ) );
        txn.setExpenseId ( expense.getId () );

        account = accountService.findByName ( expense.getAccountCategory ( ) );
        System.out.println ( "Account fetched with id " + account.getId ( ) + " with balance " + originalBalance );
        originalBalance = account.getBalance ( );

        account.setBalance ( account.getBalance ( ) - txn.getAmount ( ) );
        accountService.saveOrUpdate ( account );
        txn.setAccountId ( account.getId () );

        try {
            txn.setAmountRemainingAmount ( account.getBalance () );
            txn.setAmountBalanceAmount ( originalBalance );
            txn.setTxnStatus ( TxnStatus.SETTLED );
            txn.setCellIndex ( expenseInput.getCellIndex () );
            txnService.saveOrUpdate ( txn );
        }
        catch (Exception e){
            e.printStackTrace ();
            BeanUtils.copyProperties ( txn, txnInput );
            txnOutput = reverse ( txnInput );
            return txnOutput;
        }
        finally {
            BeanUtils.copyProperties ( txnInput, txnOutput );
        }
        return txnOutput;
        
    }


    public TxnOutput reverse(TxnInput txnInput){

        TxnOutput txnOutput = new TxnOutput ();

        if(StringUtils.isEmpty ( txnInput.getTxnId () )){
            throw new IllegalArgumentException ( "Txn Id is null in Txn Input" );
        }else if(StringUtils.isEmpty ( txnInput.getAccountId () )){
            throw new IllegalArgumentException ( "Account Id is null in Txn Input" );
        }
        Txn txn = txnService.findById ( txnInput.getTxnId () );

        Account account = accountService.findById ( txn.getAccountId () );
        System.out.println ( "Account fetched with id " + account.getId ( ) + " with balance " + account.getBalance () );
        account.setBalance ( account.getBalance () + txn.getAmount () );

        accountService.saveOrUpdate ( account );

        txn.setTxnStatus ( TxnStatus.REVERSED );
        txnService.saveOrUpdate ( txn );
        BeanUtils.copyProperties ( txnInput, txnOutput );
        return txnOutput;

    }
    private void massageTxnInput (ExpenseInput expenseInput, TxnInput txnInput){
//        System.out.println (" Expense input "+ JsonUtils.deepSerialize ( expenseInput) );

        txnInput.setTxnType ( TxnType.EXPENSE );
        txnInput.setAmount ( expenseInput.getAmount () );
        txnInput.setPaymentMode ( expenseInput.getAccountCategory ().getPaymentMode () );
        txnInput.setDescription ( expenseInput.getDescription () );
        txnInput.setTxnDate ( expenseInput.getSpentDate () );

    }

}
