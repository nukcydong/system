package com.gopher.system.model.vo.request;

import java.util.List;

import com.gopher.system.model.OrderCommodity;

public class OrderRequst {

	private int id;
	/**
	 * 备注、留言
	 */
	private String remark;
	
	private int send;
	
	private int change;
	
	private String commodityListJson;

	private List<OrderCommodity> comodityList;

	public List<OrderCommodity> getComodityList() {
		return comodityList;
	}

	public void setComodityList(List<OrderCommodity> comodityList) {
		this.comodityList = comodityList;
	}

	public String getCommodityListJson() {
		return commodityListJson;
	}

	public void setCommodityListJson(String commodityListJson) {
		this.commodityListJson = commodityListJson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

}
