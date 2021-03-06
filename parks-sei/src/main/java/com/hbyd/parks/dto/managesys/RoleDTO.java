package com.hbyd.parks.dto.managesys;

// Generated 2014-6-26 13:14:36 by Hibernate Tools 3.4.0.CR1

import com.hbyd.parks.common.base.BaseDTO;

/**
 * Role generated by hbm2java
 */
public class RoleDTO extends BaseDTO {

	private String roleName;
	private String roleDesc;

//	防止产生 Role <-> User 的双向死循环
//	private List<UserDTO> users;

	public RoleDTO() {
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
