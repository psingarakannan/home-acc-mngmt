package org.pradeep.txn.mngmt.entities.repository;

import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.txn.mngmt.entities.Txn;
import org.pradeep.txn.mngmt.entities.custom.repository.TxnRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author psingarakannan on 28/12/18
 **/
public interface TxnRepository extends JpaRepository<Txn, String>, TxnRepositoryCustom {
    List<Txn> findByAccount(AccountCategory accountCategory);

}
