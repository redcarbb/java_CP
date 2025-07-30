package com;

import javaclass.User;

public class LombokMain {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setName("AAAA");
		User user2 = new User(1L, "AAAA");
		System.out.println(user1.equals(user2));
	}

}
