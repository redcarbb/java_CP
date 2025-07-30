package com.model;

public class SingletonBean {

	// 私有靜態的欄位，用來存放單例實體
	private static SingletonBean instance;
	
	/**
	 * 私有建構式
     * 私有靜態的欄位，用來存放單例實體
     * 公有靜態的方法，來獲得單例實體
	 */
	
	// 私有建構式
	private SingletonBean() {
		
	}
	
	// 公有靜態的方法，來獲得單例實體
	public static SingletonBean getInstance() {
		if (instance == null) {
			instance = new SingletonBean();
		}
		return instance;
	}
}
