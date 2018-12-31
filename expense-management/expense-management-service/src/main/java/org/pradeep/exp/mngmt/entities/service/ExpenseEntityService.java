package org.pradeep.exp.mngmt.entities.service;

import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.repository.ExpenseRepository;
import org.pradeep.platform.beans.ExpenseInput;
import org.pradeep.platform.enums.ExpenseCategory;

import java.util.Collection;
import java.util.List;

/**
 * @author psingarakannan on 9/12/18
 **/
public interface ExpenseEntityService {
    ExpenseRepository getDao();

    Expense findById(Long id);
    List<Expense> findAll();
    List<Expense> findByIds(Collection<Long> ids);

    void saveOrUpdate(Expense entity);
    void saveOrUpdateInTransaction(Expense entity);
    void saveOrUpdateAll(Collection<Expense> entity);

    Expense createExpense(ExpenseInput expenseInput);
    void delete(Long id);
    void delete(Expense entity);
    void deleteAll(Expense entity);
    void deleteInBatch(Collection<Expense> entities);

    List<Expense> findByExpenseCategory(ExpenseCategory expenseCategory);


}

