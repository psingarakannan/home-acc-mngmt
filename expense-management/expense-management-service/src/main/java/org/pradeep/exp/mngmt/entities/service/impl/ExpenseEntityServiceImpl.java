package org.pradeep.exp.mngmt.entities.service.impl;

import org.hibernate.Session;
import org.pradeep.exp.mngmt.entities.Expense;
import org.pradeep.exp.mngmt.entities.repository.ExpenseRepository;
import org.pradeep.exp.mngmt.entities.service.ExpenseEntityService;
import org.pradeep.exp.mngmt.enums.ExpenseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psingarakannan on 9/12/18
 **/
@Service("expenseEntityService")
public class ExpenseEntityServiceImpl implements ExpenseEntityService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ExpenseRepository getDao() {
        return this.expenseRepository;
    }

    @Override
    public Expense findById(Long id) {
        return getDao ().findById ( id.toString () ).orElseGet ( null );
    }

    @Override
    public List<Expense> findAll() {
        return getDao ().findAll ();
    }

    @Override
    public List <Expense> findByIds(Collection<Long> ids) {

        return ids
                .parallelStream ()
                .map ( this::findById )
                .collect ( Collectors.toList () );
    }

    @Override
    public void saveOrUpdate(Expense entity) {
        saveOrUpdateInTransaction ( entity );
    }

    @Override
    public void saveOrUpdateInTransaction(Expense entity) {
        entityManager.unwrap(Session.class).saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection <Expense> entity) {
        entity
                .parallelStream ()
                .forEach ( this::saveOrUpdateInTransaction );
    }

    @Override
    public void delete(Long id) {
        getDao ().deleteById ( id.toString () );
    }

    @Override
    public void delete(Expense entity) {
        getDao ().delete ( entity );
    }

    @Override
    public void deleteAll(Expense entity) {
        getDao ().deleteAll ();
    }

    @Override
    public void deleteInBatch(Collection <Expense> entities) {
        getDao ().deleteInBatch ( entities );
    }

    @Override
    public List <Expense> findByExpenseCategory(ExpenseCategory expenseCategory) {
        return getDao ().findByExpenseCategory ( expenseCategory );
    }

}
