package org.pradeep.acc.mngmt.entities.service.impl;

import org.hibernate.Session;
import org.pradeep.acc.mngmt.entities.Account;
import org.pradeep.acc.mngmt.entities.repository.AccountRepository;
import org.pradeep.acc.mngmt.entities.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psingarakannan on 28/12/18
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public AccountRepository getDao() {
        return this.accountRepository;
    }

    @Override
    public Account findById(Long id) {
        return getDao ().findById ( id.toString () ).orElseGet ( null );
    }

    @Override
    public List<Account> findAll() {
        return getDao ().findAll ();
    }

    @Override
    public List <Account> findByIds(Collection<Long> ids) {
        return ids
                .parallelStream ()
                .map ( this::findById )
                .collect ( Collectors.toList () );    }

    @Override
    public void saveOrUpdate(Account entity) {
        saveOrUpdateInTransaction ( entity );

    }

    @Override
    public void saveOrUpdateInTransaction(Account entity) {
        entityManager.unwrap(Session.class).saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection <Account> entity) {
        entity
                .parallelStream ()
                .forEach ( this::saveOrUpdateInTransaction );
    }

    @Override
    public void delete(Long id) {
        getDao ().deleteById ( id.toString () );

    }

    @Override
    public void delete(Account entity) {
        getDao ().delete ( entity );

    }

    @Override
    public void deleteAll(Account entity) {
        getDao ().deleteAll ();

    }

    @Override
    public void deleteInBatch(Collection <Account> entities) {
        getDao ().deleteInBatch ( entities );
    }
}
