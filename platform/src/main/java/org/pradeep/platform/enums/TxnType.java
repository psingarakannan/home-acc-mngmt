package org.pradeep.platform.enums;

import lombok.Getter;

/**
 * @author psingarakannan on 28/12/18
 **/
public enum TxnType {

    EXPENSE("Expense", true, true),
    CREDIT("Amount Received",false, false),
    TRANSFER("Transfer", false, true);

    @Getter
    private String description;
    @Getter
    private boolean expenseType;
    @Getter
    private boolean debit;
    /**
     * For future purpose to show the txn type in reports or in readable format
     * @param description describe the type of the transaction
     */
    TxnType(String description, boolean isExpense, boolean isDebit){
        this.description = description;
        this.expenseType = isExpense;
        this.debit = isDebit;
    }
}
