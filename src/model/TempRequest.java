package model;

import java.util.HashMap;
import java.util.Map;

public class TempRequest extends RequeteReponse {
	
	public static Map<Integer, TemperatureChaudiere> chaudieres = new HashMap<>();

	static {
		chaudieres.put(1, new TemperatureChaudiere(1));
		chaudieres.put(2, new TemperatureChaudiere(2));
		chaudieres.put(3, new TemperatureChaudiere(3));
		chaudieres.put(4, new TemperatureChaudiere(4));
	}
	
	int chaudiereId;
	
	public TempRequest(Employe emp, int chaudiereId) {
		super(emp);
		this.chaudiereId = chaudiereId;
	}

	@Override
	public Map<Employe, String> lancer() {
		Map<Employe,String> reponse = new HashMap<>();
		try {
			reponse.put(
					null, 
					String.format("La chaudière %d a une température de %f °C", 
							this.chaudiereId, 
							TempRequest.chaudieres.get(this.chaudiereId).releverTemperature()
							)
					);
		} catch (Exception e) {
			reponse.put(null, String.format("Chaudière %d inexistante.", this.chaudiereId));
		}
		return reponse;
	}
}
