package com.mindtree.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Accounts {
	@Column(name = "customer_id")
	private int customerId;
	@Column(name="account_number")
	@Id
	private long accountNumber;
	@Column(name="account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
	@Column(name = "create_dt")
	private String createDt;
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accounts(int customerId, long accountNumber, String accountType, String branchAddress, String createDt) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
		this.createDt = createDt;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	

}
