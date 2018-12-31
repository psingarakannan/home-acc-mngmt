package org.pradeep.platform.beans;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.platform.enums.PaymentMode;
import org.pradeep.platform.enums.TxnType;

import java.util.Date;

/**
 * @author psingarakannan on 30/12/18
 **/
@Getter @Setter
public class TxnInput {

    private ExpenseInput expenseInput;

    private Date txnDate;

    private String description;

    private Long amount;

    private TxnType txnType;

    private PaymentMode paymentMode;

    private String username;

    private String externalRefNumber;

    private String externalRefNumber2;

    private String externalRefNumber3;

    private String externalRefNumber4;

    private String externalRefNumber5;

    private Long expenseId;

    private Long accountId;

    private Long txnId;

}
