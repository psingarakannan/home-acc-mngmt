package org.pradeep.txn.mngmt.entities.service;

import org.pradeep.txn.mngmt.entities.Txn;
import org.pradeep.txn.mngmt.entities.repository.TxnRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author psingarakannan on 28/12/18
 **/
public interface TxnService {
    TxnRepository getDao();

    Txn findById(Long id);
    List<Txn> findAll();
    List<Txn> findByIds(Collection<Long> ids);

    void saveOrUpdate(Txn entity);
    void saveOrUpdateInTransaction(Txn entity);
    void saveOrUpdateAll(Collection<Txn> entity);

    void delete(Long id);
    void delete(Txn entity);
    void deleteAll(Txn entity);
    void deleteInBatch(Collection<Txn> entities);
/*

    List<Txn> findByExpenseCategory(ExpenseCategory expenseCategory);
    List<Txn> findByAccountCategory(AccountCategory accountCategory);

*/
}
