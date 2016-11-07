package com.verified.domain.UserRole;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.verified.BaseEntity;

@MappedSuperclass
public class AbstractUserRole extends BaseEntity{
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleId")
    private Integer roleId;

	@NotNull
	@Column(name = "RoleName")
    private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
