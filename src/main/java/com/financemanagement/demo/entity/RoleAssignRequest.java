package com.financemanagement.demo.entity;

public class RoleAssignRequest {

	private String email;
	private long assignerId;
	private long homeId;
	private long roleId;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAssignerId() {
		return assignerId;
	}
	public void setAssignerId(long assignerId) {
		this.assignerId = assignerId;
	}
	public long getHomeId() {
		return homeId;
	}
	public void setHomeId(long homeId) {
		this.homeId = homeId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	
	public RoleAssignRequest(String email, long assignerId, long homeId, long roleId) {
		super();
		this.email = email;
		this.assignerId = assignerId;
		this.homeId = homeId;
		this.roleId = roleId;
	}
	
	
}
