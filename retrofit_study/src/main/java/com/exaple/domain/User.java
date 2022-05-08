package com.exaple.domain;

import com.google.gson.annotations.SerializedName;

public class User {
	// JSON 문자열의 name 속성과 일치하지 않을 때
	// @SerializedName로 name 속성과 일치 시키면 됨
	@SerializedName("firstName")
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	public User() {} // 기본 생성자는 JAVA bean용으로 필요
	
	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
}
