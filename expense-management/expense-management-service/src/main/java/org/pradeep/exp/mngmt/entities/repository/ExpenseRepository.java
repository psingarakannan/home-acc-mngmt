package org.pradeep.exp.mngmt.entities.repository;

import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.custom.repository.ExpenseRepositoryCustom;
import org.pradeep.exp.mngmt.enums.AccountCategory;
import org.pradeep.exp.mngmt.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author psingarakannan on 9/12/18
 **/

public interface ExpenseRepository extends JpaRepository<Expense, String>, ExpenseRepositoryCustom {

    List<Expense> findByExpenseCategory(ExpenseCategory expenseCategory);

}
