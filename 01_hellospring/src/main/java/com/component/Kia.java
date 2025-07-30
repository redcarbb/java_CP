package com.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.Engine;

@Component
@Scope("prototype")
public class Kia {

	// @Autowired
	// @Qualifier(value = "benzEngine")
	private Engine engine;

	@Autowired
	public Kia(@Qualifier(value = "benzEngine") Engine engine) {
		super();
		this.engine = engine;
	}

	public Engine getEngine() {
		return engine;
	}

	// @Autowired
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

}
