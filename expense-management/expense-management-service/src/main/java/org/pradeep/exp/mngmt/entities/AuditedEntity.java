package org.pradeep.exp.mngmt.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @author psingarakannan on 19/12/18
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity<I  extends Serializable>  {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name="created_time", nullable=false) private Date createdTime;
    @CreatedBy
    @Column(name="created_by", length=20, nullable=false) private String createdBy;
    @LastModifiedDate
    @Column(name="modified_time", nullable=false) private Date modifiedTime = new Date (  );
    @LastModifiedBy
    @Column(name="modified_by", length=20, nullable=false) private String modifiedBy;
    @Version
    @Column(name="lock_id", columnDefinition="int(11) default 0", nullable=false) private Integer lockId = 1;

    protected Integer getLockId() { return lockId; }

    public final Date getCreatedTime() {
        return createdTime;
    }
    public final void setCreatedTime(Date createDate) {
        this.createdTime = createDate;
    }
    public final String getCreatedBy() {
        return createdBy;
    }
    public final void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public final Date getModifiedTime() {
        return modifiedTime;
    }
    public final void setModifiedTime(Date modifiedDate) {
        this.modifiedTime = modifiedDate;
    }
    public final String getModifiedBy() {
        return modifiedBy;
    }
    public final void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}