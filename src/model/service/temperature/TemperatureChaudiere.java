package model.service.temperature;

import model.ObjetIdentifie;

public class TemperatureChaudiere extends ObjetIdentifie {
		
	public TemperatureChaudiere(String id) throws Exception {
		super(id);
	}
	
	public double releverTemperature() {
		return Math.random() * 100;
	}
}
