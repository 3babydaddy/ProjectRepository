package com.tf.base.user.controller;

import com.tf.base.common.annotation.LogShowName;

public class UserInfo {

	private Long id; // 编号
	private String username; // 用户名
	@LogShowName("密码")
	private String password; // 密码
	@LogShowName("用户姓名")
	private String showname; // 用户显示名
	private String department; // 组织ID
	private String avail; // 是否有效
	
	private String loginDepartment;
	private String departmentShow;
	private String systemName;
	
	private String loginName;
	private String name;
	private String isAdmin;
	private String systemid;
	@LogShowName("联系方式")
	private String tel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAvail() {
		return avail;
	}
	public void setAvail(String avail) {
		this.avail = avail;
	}
	public String getLoginDepartment() {
		return loginDepartment;
	}
	public void setLoginDepartment(String loginDepartment) {
		this.loginDepartment = loginDepartment;
	}
	public String getDepartmentShow() {
		return departmentShow;
	}
	public void setDepartmentShow(String departmentShow) {
		this.departmentShow = departmentShow;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getSystemid() {
		return systemid;
	}
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
