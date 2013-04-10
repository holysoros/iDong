/**
 * @copyright JCBC limited Co.
 */
package com.jbcb.idong.model;

/**
 * Data model of iDong stadium.
 * @author Jiayu
 */
public class Stadium {
	
	private Long stadiumID;
	
	public Long getStadiumID() {
		return stadiumID;
	}
	public void setStadiumID(Long stadiumID) {
		this.stadiumID = stadiumID;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistricName() {
		return districName;
	}
	public void setDistricName(String districName) {
		this.districName = districName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getAddressPrefix() {
		return addressPrefix;
	}
	public void setAddressPrefix(String addressPrefix) {
		this.addressPrefix = addressPrefix;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getVote() {
		return vote;
	}
	public void setVote(Float vote) {
		this.vote = vote;
	}
	private String countryName;
	private String provinceName;
	private String cityName;
	private String districName;
	private String streetName;
	
	//Redundancy fields for higher version compatibility
	private String addressPrefix;
	
	private Float price;
	private Float vote;
}
