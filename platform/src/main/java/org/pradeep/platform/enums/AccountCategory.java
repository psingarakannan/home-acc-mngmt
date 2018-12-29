package org.pradeep.platform.enums;

import lombok.Getter;

/**
 * @author psingarakannan on 9/12/18
 **/
public enum AccountCategory {

    P_CC_HDFC("Pradeep HDFC Credit Card"),
    K_CC_HDFC("Kalai HDFC Credit Card"),
    P_DC_HDFC("Pradeep HDFC Debit Card"),
    K_DC_HDFC("Kalai HDFC Debit Card"),
    P_DC_SBI("Pradeep SBI Debit Card"),
    P_SODEXO("Pradeep Sodexo Card"),
    P_CASH("Pradeep Cash"),
    K_CASH("Kalai Cash"),
    P_PAYTM("Pradeep Paytm"),
    K_PAYTM("Kalai Paytm"),
    P_AMAZON_PAY("Pradeep Amazon Pay")
    ;

    @Getter
    private String name;
    AccountCategory(String name){
        this.name = name;
    }

}
