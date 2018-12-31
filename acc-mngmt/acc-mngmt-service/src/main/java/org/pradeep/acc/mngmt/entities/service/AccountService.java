package org.pradeep.acc.mngmt.entities.service;

import org.pradeep.acc.mngmt.entities.Account;
import org.pradeep.acc.mngmt.entities.repository.AccountRepository;
import org.pradeep.platform.enums.AccountCategory;

import java.util.Collection;
import java.util.List;

/**
 * @author psingarakannan on 28/12/18
 **/
public interface AccountService {
    AccountRepository getDao();

    Account findById(Long id);
    List<Account> findAll();
    List<Account> findByIds(Collection<Long> ids);

    void saveOrUpdate(Account entity);
    void saveOrUpdateInTransaction(Account entity);
    void saveOrUpdateAll(Collection<Account> entity);
    Account findByName(AccountCategory name);

    void delete(Long id);
    void delete(Account entity);
    void deleteAll(Account entity);
    void deleteInBatch(Collection<Account> entities);

}
