package org.pradeep.txn.mngmt.entities.service.impl;

import org.hibernate.Session;
import org.pradeep.platform.beans.TxnInput;
import org.pradeep.platform.hibernate.BaseServiceImpl;
import org.pradeep.txn.mngmt.entities.Txn;
import org.pradeep.txn.mngmt.entities.repository.TxnRepository;
import org.pradeep.txn.mngmt.entities.service.TxnService;
import org.springframework.beans.BeanUtils;
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
@Service("txnService")
public class TxnServiceImpl extends BaseServiceImpl implements TxnService {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TxnRepository txnRepository;


    @Override
    public TxnRepository getDao() {
        return this.txnRepository;
    }

    @Override
    public Txn findById(Long id) {
        return getDao ().findById ( id.toString () ).orElseGet ( null );
    }

    public Txn createTxn(TxnInput txnInput){
        Txn txn = new Txn ();
        BeanUtils.copyProperties ( txnInput, txn );
        this.saveOrUpdate ( txn );
        return txn;
    }

    @Override
    public List<Txn> findAll() {
        return getDao ().findAll ();    }

    @Override
    public List <Txn> findByIds(Collection<Long> ids) {
        return ids
                .parallelStream ()
                .map ( this::findById )
                .collect ( Collectors.toList () );    }

    @Override
    public void saveOrUpdate(Txn entity) {
        super.saveOrUpdate ( entity );
    }

    @Override
    public void saveOrUpdateInTransaction(Txn entity) {
        super.saveOrUpdateInTransaction ( entity );
    }


    @Override
    public void saveOrUpdateAll(Collection <Txn> entity) {
        entity
                .parallelStream ()
                .forEach ( this::saveOrUpdateInTransaction );
    }

    @Override
    public void delete(Long id) {
        getDao ().deleteById ( id.toString () );
    }

    @Override
    public void delete(Txn entity) {
        getDao ().delete ( entity );
    }

    @Override
    public void deleteAll(Txn entity) {
        getDao ().deleteAll ();
    }

    @Override
    public void deleteInBatch(Collection <Txn> entities) {
        getDao ().deleteInBatch ( entities );
    }

}
