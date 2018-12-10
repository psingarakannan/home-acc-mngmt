package org.pradeep.exp.mngmt.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.pradeep.exp.mngmt.enums.AccountCategory;
import org.pradeep.exp.mngmt.enums.ExpenseCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author psingarakannan on 9/12/18
 **/
@Entity
@Table(name="expenses", indexes = { @Index(name="expense_k1", columnList="expense_category"),
        @Index(name="expense_k12", columnList="spent_date")})

public class Expense {



    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id") private Long id;

    @Getter @Setter
    @Column(name="spent_date")
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date spentDate;
    @Getter @Setter
    @Column(name="description")
    private String description;

    @Getter @Setter
    @Column(name="account_category")
    @Enumerated(EnumType.STRING)
    private AccountCategory accountCategory;

    @Getter @Setter
    @Column(name="expense_category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;


    @Getter @Setter
    @Column(name="sub_type")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory.SubType subType;

    @Getter @Setter
    @Column(name="amount")
    private Long amount;



}
