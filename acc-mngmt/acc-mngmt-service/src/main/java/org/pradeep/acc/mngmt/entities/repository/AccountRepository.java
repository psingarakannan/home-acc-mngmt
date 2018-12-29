package org.pradeep.acc.mngmt.entities.repository;

import org.pradeep.acc.mngmt.entities.Account;
import org.pradeep.acc.mngmt.entities.custom.repository.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author psingarakannan on 28/12/18
 **/
public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {
}
