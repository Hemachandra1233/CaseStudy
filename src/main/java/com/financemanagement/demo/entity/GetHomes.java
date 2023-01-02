package com.financemanagement.demo.entity;

public class GetHomes {
	private long id;
	private String description;
	private boolean showUpdate;
	private Long status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isShowUpdate() {
		return showUpdate;
	}
	public void setShowUpdate(boolean showUpdate) {
		this.showUpdate = showUpdate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}
