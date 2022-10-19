package com.data.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinalFeeStructure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String govFee;
	
	private String serviceCharges;
	
	private String total;
	
	private String seriveName;
	
	private String urlName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGovFee() {
		return govFee;
	}

	public void setGovFee(String govFee) {
		this.govFee = govFee;
	}

	public String getServiceCharges() {
		return serviceCharges;
	}

	public void setServiceCharges(String serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSeriveName() {
		return seriveName;
	}

	public void setSeriveName(String seriveName) {
		this.seriveName = seriveName;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	
	

}
