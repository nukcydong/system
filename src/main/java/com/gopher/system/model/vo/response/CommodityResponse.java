package com.gopher.system.model.vo.response;

import org.springframework.data.annotation.Transient;

public class CommodityResponse {
	
     private int id;
     
     private String name;
     
     private String commodityTypeName;
     
     private int commodityTypeId;
     
     private String unit;
     
     private int amount;
     
     private int sendAmount;
     
     private int realAmount;
     
     private int price;
 	@Transient
 	private int commodityId;
 	
 	private int customerCommodityGroupId;
 	
 	private String customerCommodityGroupName;
 	
 	private int change;

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public int getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(int sendAmount) {
		this.sendAmount = sendAmount;
	}

	public int getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(int realAmount) {
		this.realAmount = realAmount;
	}

	public int getCustomerCommodityGroupId() {
		return customerCommodityGroupId;
	}

	public void setCustomerCommodityGroupId(int customerCommodityGroupId) {
		this.customerCommodityGroupId = customerCommodityGroupId;
	}

	public String getCustomerCommodityGroupName() {
		return customerCommodityGroupName;
	}

	public void setCustomerCommodityGroupName(String customerCommodityGroupName) {
		this.customerCommodityGroupName = customerCommodityGroupName;
	}

	public int getCommodityId() {
		this.commodityId = this.id;
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommodityTypeName() {
		return commodityTypeName;
	}

	public void setCommodityTypeName(String commodityTypeName) {
		this.commodityTypeName = commodityTypeName;
	}

	public int getCommodityTypeId() {
		return commodityTypeId;
	}

	public void setCommodityTypeId(int commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
     
     

}
