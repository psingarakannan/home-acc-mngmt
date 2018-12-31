package org.pradeep.txn.mngmt.entities;

import lombok.Getter;
import lombok.Setter;
import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.platform.enums.PaymentMode;
import org.pradeep.platform.enums.TxnStatus;
import org.pradeep.platform.enums.TxnType;
import org.pradeep.platform.hibernate.AuditedEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author psingarakannan on 28/12/18
 **/
@Entity
@Table(name="txn", indexes = { @Index(name="idx_txn_external_ref", columnList="external_ref"),
        @Index(name="idx_txn_desc", columnList="description")})
public class Txn extends AuditedEntity {


    public Txn(){
        this.txnStatus = TxnStatus.PENDING;
    }

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id") private Long id;

    @Getter @Setter
    @Column(name="txn_date")
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date txnDate;

    @Getter @Setter
    @Column(name="description")
    private String description;

    @Getter @Setter
    @Column(name="account_type")
    @Enumerated(EnumType.STRING)
    private AccountCategory accountCategory;

    @Getter @Setter
    @Column(name="expense_id")
    private Long expenseId;

    @Getter @Setter
    @Column(name="account_id")
    private Long accountId;

    @Getter @Setter
    @Column(name="amount")
    private Long amount;

    @Getter @Setter
    @Column(name="acc_balance_amount")
    private Long amountBalanceAmount;

    @Getter @Setter
    @Column(name="acc_remaining_amount")
    private Long amountRemainingAmount;

    @Getter @Setter
    @Column(name="txn_type")
    private TxnType txnType;

    @Getter @Setter
    @Column(name="payment_mode")
    private PaymentMode paymentMode;

    @Getter @Setter
    @Column(name="username",length=20, nullable=false)
    private String username;

    @Getter @Setter
    @Column(name="external_ref", length=56)
    private String externalRefNumber;

    @Getter @Setter
    @Column(name="external_ref2", length=56)
    private String externalRefNumber2;

    @Getter @Setter
    @Column(name="external_ref3", length=56)
    private String externalRefNumber3;

    @Getter @Setter
    @Column(name="external_ref4", length=56)
    private String externalRefNumber4;

    @Getter @Setter
    @Column(name="external_ref5", length=56)
    private String externalRefNumber5;

    @Getter @Setter
    @Column(name="cell_index")
    private String cellIndex;

    @Getter @Setter
    @Column(name="status")
    private TxnStatus txnStatus;

}
