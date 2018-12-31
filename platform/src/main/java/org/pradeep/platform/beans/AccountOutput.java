package org.pradeep.platform.beans;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.platform.enums.AccountType;
import org.pradeep.platform.enums.TxnType;

import javax.persistence.AccessType;

/**
 * @author psingarakannan on 30/12/18
 **/
public class AccountOutput {

    @Getter @Setter
    private String description;

    @Getter @Setter
    private AccountCategory name;

    @Getter @Setter
    private Long balance;
    @Getter @Setter
    private AccountType accountType;
}
