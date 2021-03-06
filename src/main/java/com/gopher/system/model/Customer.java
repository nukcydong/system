package com.gopher.system.model;

public class Customer extends BaseModel {
	private static final long serialVersionUID = 3832160238061757765L;
	/**
	 * 客户名称
	 */
	private String name;
	/**
	 * 客户描述
	 */
	private String description;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 微信号码
	 */
	private String wechat;

	private String qq;
	/**
	 * @see com.gopher.system.constant.State
	 */
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

}
