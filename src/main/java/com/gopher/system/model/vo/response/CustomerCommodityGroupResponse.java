package com.gopher.system.model.vo.response;

import java.util.List;

public class CustomerCommodityGroupResponse {
	private int id;
	private String name;
	private String remark;
	private int sort;
	private int customerId;
	private List<CommodityResponse> commodityList;
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<CommodityResponse> getCommodityList() {
		return commodityList;
	}
	public void setCommodityList(List<CommodityResponse> commodityList) {
		this.commodityList = commodityList;
	}
	
}
