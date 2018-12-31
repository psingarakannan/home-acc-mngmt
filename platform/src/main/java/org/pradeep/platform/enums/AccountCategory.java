package org.pradeep.platform.enums;

import lombok.Getter;

/**
 * @author psingarakannan on 9/12/18
 **/
public enum AccountCategory {

    P_CC_HDFC("Pradeep HDFC Credit Card", AccountType.CREDIT, PaymentMode.CREDIT_CARD),
    K_CC_HDFC("Kalai HDFC Credit Card", AccountType.CREDIT, PaymentMode.CREDIT_CARD),
    P_DC_HDFC("Pradeep HDFC Debit Card", AccountType.DEBIT, PaymentMode.DEBIT_CARD),
    K_DC_HDFC("Kalai HDFC Debit Card", AccountType.DEBIT, PaymentMode.DEBIT_CARD),
    P_DC_SBI("Pradeep SBI Debit Card", AccountType.DEBIT, PaymentMode.DEBIT_CARD),
    P_SODEXO("Pradeep Sodexo Card", AccountType.DEBIT, PaymentMode.DEBIT_CARD),
    P_CASH("Pradeep Cash", AccountType.DEBIT, PaymentMode.CASH),
    K_CASH("Kalai Cash", AccountType.DEBIT, PaymentMode.CASH),
    P_PAYTM("Pradeep Paytm", AccountType.DEBIT, PaymentMode.WALLET),
    K_PAYTM("Kalai Paytm", AccountType.DEBIT, PaymentMode.WALLET),
    P_AMAZON_PAY("Pradeep Amazon Pay", AccountType.DEBIT, PaymentMode.WALLET),
    P_HDFC("Pradeep Hdfc", AccountType.DEBIT, PaymentMode.NB),
    P_SBI("Pradeep SBI", AccountType.DEBIT, PaymentMode.NB),
    K_CANARA("Kalai Canara", AccountType.DEBIT, PaymentMode.NB),
    K_HDFC("Kalai Hdfc", AccountType.DEBIT, PaymentMode.NB)

    ;

    @Getter
    private String name;

    @Getter
    private AccountType accountType;

    @Getter
    private PaymentMode paymentMode;

    AccountCategory(String name, AccountType accountType, PaymentMode paymentMode ){
        this.paymentMode = paymentMode;
        this.name = name;
        this.accountType = accountType;
    }

}
