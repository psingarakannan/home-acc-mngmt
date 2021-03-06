package org.pradeep.acc.mngmt.entities;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.platform.enums.AccountType;
import org.pradeep.platform.enums.TxnType;
import org.pradeep.platform.hibernate.AuditedEntity;

import javax.persistence.*;

/**
 * @author psingarakannan on 28/12/18
 **/
@Entity
@Table(name="account")
public class Account extends AuditedEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id") private Long id;

    @Getter @Setter
    @Column(name="description")
    private String description;

    @Getter @Setter
    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private AccountCategory name;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private AccountType accountType;

    @Getter @Setter
    @Column(name="balance")
    private Long balance;


}
