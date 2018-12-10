package org.pradeep.exp.mngmt.beans;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.exp.mngmt.enums.AccountCategory;
import org.pradeep.exp.mngmt.enums.ExpenseCategory;

import java.util.Date;

/**
 * @author psingarakannan on 9/12/18
 **/
public class ExpenseInput {

    @Getter @Setter
    private Long amount;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private Date spentDate;

    @Getter @Setter
    private ExpenseCategory expenseCategory;

    @Getter @Setter
    private ExpenseCategory.SubType subType;

    @Getter @Setter
    private AccountCategory accountCategory;
}
