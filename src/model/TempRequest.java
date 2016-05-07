package model;

import java.util.HashMap;
import java.util.Map;

public class TempRequest extends RequeteReponse {
	
	public static Map<String, TemperatureChaudiere> chaudieres = new HashMap<>();

	static {
		try {
			chaudieres.put("TE2016vvvv", new TemperatureChaudiere("TE2016vvvv"));
			chaudieres.put("TE2016wwww", new TemperatureChaudiere("TE2016wwww"));
			chaudieres.put("TE2016xxxx", new TemperatureChaudiere("TE2016xxxx"));
			chaudieres.put("TE2016yyyy", new TemperatureChaudiere("TE2016yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String chaudiereId;
	private static TempRequest instance;
	
	private TempRequest(String id) throws Exception {
		super(id);
	}

	@Override
	public Map<Employe, String> lancer(SMSEntrant sms) {
		this.sms = sms;
		this.chaudiereId = sms.getTrailingArgs()[0];
		Map<Employe,String> reponse = new HashMap<>();
		try {
			reponse.put(
					null, 
					String.format("La chaudière %s a une température de %f °C", 
							this.chaudiereId, 
							TempRequest.chaudieres.get(this.chaudiereId).releverTemperature()
							)
					);
		} catch (Exception e) {
			reponse.put(null, String.format("Chaudière %s inexistante.", this.chaudiereId));
		}
		return reponse;
	}

	public static TempRequest getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new TempRequest(id);
		}
		//System.out.println(instance);
		return instance;
	}
}
