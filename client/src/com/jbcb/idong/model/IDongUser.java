/**
 * @copyright JCBC limited Co.
 */
package com.jbcb.idong.model;

import java.util.Date;
import java.util.Set;

/**
 * Data model of iDong user.
 * @author Jiayu
 */
public class IDongUser extends User{
	
	private Long userID;
	private Long userAccountID;  		//FK for user account related info
	
	private String username;
	private String nickname;
	private String idname;
	private String phoneNumber;
	private String homeAddr;
	private String companyName;
	private String schoolName;
	private String major;
	
	private Set<String> userImageURLSet;	//Image URLs for user, should not be repeated
	
	public IDongUser() {}
		
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getUserAccountID() {
		return userAccountID;
	}

	public void setUserAccountID(Long userAccountID) {
		this.userAccountID = userAccountID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdname() {
		return idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Set<String> getUserImageURLSet() {
		return userImageURLSet;
	}

	public void setUserImageURLSet(Set<String> userImageURLSet) {
		this.userImageURLSet = userImageURLSet;
	}

	private Integer sex;  				//Integer for sex, 0 for male, 1 for female
	private Integer interest; 			//Integer for interest(sports), 0 for basketball etc.
	
	private Date birthDay;
}
