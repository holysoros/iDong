/**
 * @copyright JCBC limited Co.
 */
package com.jbcb.idong.model;

/**
 * Data model of iDong user.
 * @author Jiayu
 */
public abstract class User {

	private String emailAddr;
	
	public User() {}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
}
