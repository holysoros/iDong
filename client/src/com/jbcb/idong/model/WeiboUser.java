/**
 * @copyright JCBC limited Co.
 */
package com.jbcb.idong.model;

/**
 * Data model of iDong user.
 * @author Jiayu
 */
public class WeiboUser extends User{

	// For Open Authentication
	private String weiboName;   		//Weibo related
	
	public WeiboUser() {}
	
	/**
	 * Initiated using Weibo ID(name)
	 * @param tencentName
	 */
	public WeiboUser(String weiboName) {
		this.setWeiboName(weiboName);
	}

	public String getWeiboName() {
		return weiboName;
	}

	public void setWeiboName(String weiboName) {
		this.weiboName = weiboName;
	}
	
}
