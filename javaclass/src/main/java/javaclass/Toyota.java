package javaclass;

public class Toyota /** extends ToyotaEngine */ {

	// private ToyotaEngine engine;
	private Engine engine;
	
	public Toyota(Engine engine) {
		this.engine = engine;
	}
	
	public void move() {
		// ToyotaEngine engine = new ToyotaEngine();
		// engine.start();
		engine.start();
		System.out.println("Toyota 移動");
	}
}
