package org.pradeep.platform.beans;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.platform.enums.ExpenseCategory;

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

    @Getter @Setter
    private String cellIndex;


}
