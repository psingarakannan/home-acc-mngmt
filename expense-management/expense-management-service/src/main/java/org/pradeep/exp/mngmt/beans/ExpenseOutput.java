package org.pradeep.exp.mngmt.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.pradeep.exp.mngmt.enums.AccountCategory;
import org.pradeep.exp.mngmt.enums.ExpenseCategory;

import java.util.Date;

/**
 * @author psingarakannan on 9/12/18
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ExpenseOutput {

    public ExpenseOutput(){
        this.success = true;
    }
    @Getter @Setter

    private boolean success;

    @Getter
    @Setter
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
    String errorMessage;

    @Getter @Setter
    String exception;

}
