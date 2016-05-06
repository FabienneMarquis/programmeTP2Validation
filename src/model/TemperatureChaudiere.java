package model;

public class TemperatureChaudiere {
		
	private String id;
	
	public TemperatureChaudiere(String id) {
		this.id = id;
	}
	
	public double releverTemperature() {
		return Math.random() * 100;
	}
}
