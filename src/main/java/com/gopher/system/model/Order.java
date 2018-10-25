package com.gopher.system.model;
/**
 * 订单
 * @author dongyangyang
 *
 */
public class Order extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7935986002398694816L;
	
	/**
	 * 订单号 除id主键外 唯一标识
	 */
	private String number;
	/**
	 * 下订单用户
	 */
	private int userId;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	} 
	
	

}