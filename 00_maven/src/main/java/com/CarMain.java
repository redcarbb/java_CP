package com;

import com.model.BenzEngine;
import com.model.Toyota;

/** */
public class CarMain {

	public static void main(String[] args) {
		// Toyota toyota = new Toyota(new ToyotaEngine());
		
		Toyota toyota = new Toyota(new BenzEngine());
		toyota.move();

	}

}
