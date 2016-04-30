package model;

import java.util.HashMap;
import java.util.Map;

public class TemperatureChaudiere {
		
	private Integer id;
	
	public TemperatureChaudiere(Integer id) {
		this.id = id;
	}
	
	public double releverTemperature() {
		return Math.random() * 100;
	}
}
