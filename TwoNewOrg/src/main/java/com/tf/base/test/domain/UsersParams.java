package com.tf.base.test.domain;

import com.tf.base.common.domain.Pager;

public class UsersParams extends Pager{

	private String name;

    private Integer age;

    private Integer gender;

    private String tel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
    
}
