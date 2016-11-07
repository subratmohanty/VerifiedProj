package com.verified;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class BaseEntity {
	
	@PersistenceContext
    transient EntityManager entityManager;
	
	public static final EntityManager entityManager() {
        EntityManager em = new UserRole().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
	
	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
            this.entityManager.remove(this);
	}
	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public BaseEntity merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.merge(this);
        this.entityManager.flush();
        return this;
    }

}
