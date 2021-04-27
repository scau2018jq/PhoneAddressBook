package com.chat.bean;

public class group {
	@Override
	public String toString() {
		return "group [group=" + groupn + ", gID=" + gID + "]";
	}

	private String groupn = null;
	private int gID = 0;

	public String getGroup() {
		return groupn;
	}

	public void setGroup(String groupn) {
		this.groupn = groupn;
	}

	public int getgID() {
		return gID;
	}

	public void setgID(int gID) {
		this.gID = gID;
	}

	public group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public group(String groupn, int gID) {
		super();
		this.groupn = groupn;
		this.gID = gID;
	}

	public group(String groupn) {
		super();
		this.groupn = groupn;
	}

}
