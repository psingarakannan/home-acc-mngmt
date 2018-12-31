package org.pradeep.platform.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * @author psingarakannan on 30/12/18
 **/
@Service
public class BaseServiceImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveOrUpdate(AuditedEntity entity) {
        saveOrUpdateInTransaction ( entity );

    }

    public void saveOrUpdateInTransaction(AuditedEntity entity) {
        entity.setCreatedBy ( "system" );
        entity.setModifiedBy ( "system" );
        entity.setCreatedTime ( new Date (  ) );
        entity.setModifiedTime ( new Date (  ) );
        entityManager.unwrap(Session.class).saveOrUpdate(entity);

    }

}
