/**
 * @copyright JCBC limited Co.
 */
package com.jbcb.idong.model;

/**
 * Data model of iDong user.
 * @author Jiayu
 */
public class TencentUser extends User{

	// For Open Authentication
	private String tencentName;   		//Tencent related
	
	public TencentUser() {}
	
	/**
	 * Initiated using QQ ID(name)
	 * @param tencentName
	 */
	public TencentUser(String tencentName) {
		this.setTencentName(tencentName);
	}
	/**
	 * @return the tencentName
	 */
	public String getTencentName() {
		return tencentName;
	}

	/**
	 * @param tencentName the tencentName to set
	 */
	public void setTencentName(String tencentName) {
		this.tencentName = tencentName;
	}
	
}
