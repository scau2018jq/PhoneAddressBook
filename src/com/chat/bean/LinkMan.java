package com.chat.bean;

import java.io.Serializable;

public class LinkMan implements Serializable{
	private int ID = 0;
	private String sex = null;
	private String name = null;
	private String telephone = null;
	private String QQ = null;
	private String email = null;
	private String birthday = null;
	private String Fname = null;
	private String SortID = null;
	private String group = null;

	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getSortID() {
		return SortID;
	}

	public void setSortID(String sortID) {
		SortID = sortID;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public LinkMan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkMan(String telephone) {
		super();
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "LinkMan [ID=" + ID + ", sex=" + sex + ", name=" + name + ", telephone=" + telephone + ", QQ=" + QQ
				+ ", email=" + email + ", birthday=" + birthday + ", Fname=" + Fname + ", SortID=" + SortID + ", group="
				+ group + "]";
	}

	public LinkMan(String name, String sex) {
		super();
		this.name = name;
	}

	public LinkMan(String name, String sex,String group) {
		super();
		this.group = group;
	}

	public LinkMan(String name, String sex, String telephone, String qQ, String email, String birthday, String fname,
			String sortID, String group) {
		super();
		this.sex = sex;
		this.name = name;
		this.telephone = telephone;
		QQ = qQ;
		this.email = email;
		this.birthday = birthday;
		Fname = fname;
		SortID = sortID;
		this.group = group;
	}

	public LinkMan(String name, String sex, String telephone, String qQ, String email, String birthday, String group) {
		super();
		this.sex = sex;
		this.name = name;
		this.telephone = telephone;
		QQ = qQ;
		this.email = email;
		this.birthday = birthday;
		this.group = group;
	}

}
