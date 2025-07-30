package com;

import javaclass.Toyota;
import javaclass.ToyotaEngine;

/** */
public class CarMain {

	public static void main(String[] args) {
		Toyota toyota = new Toyota(new ToyotaEngine());
		toyota.move();

	}

}
