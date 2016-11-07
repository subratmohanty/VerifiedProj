package com.verified;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Configurable
@Entity
public class UserRole extends BaseEntity {

    /**
     */
    @NotNull
    private String RoleName;

	

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("RoleName");

	
	public static long countUserRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserRole o", Long.class).getSingleResult();
    }

	public static List<UserRole> findAllUserRoles() {
        return entityManager().createQuery("SELECT o FROM UserRole o", UserRole.class).getResultList();
    }

	public static List<UserRole> findAllUserRoles(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UserRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UserRole.class).getResultList();
    }

	public static UserRole findUserRole(Long id) {
        if (id == null) return null;
        return entityManager().find(UserRole.class, id);
    }

	public static List<UserRole> findUserRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserRole o", UserRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<UserRole> findUserRoleEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UserRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UserRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }



	public String getRoleName() {
        return this.RoleName;
    }

	public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
