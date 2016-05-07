package model.service.temperature;

import java.util.HashMap;
import java.util.Map;

import model.employe.Employe;
import model.service.RequeteReponseService;
import model.service.SMSEntrant;

public class TempService extends RequeteReponseService {
	
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
	private static TempService instance;
	
	private TempService(String id) throws Exception {
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
							TempService.chaudieres.get(this.chaudiereId).releverTemperature()
							)
					);
		} catch (Exception e) {
			reponse.put(null, String.format("Chaudière %s inexistante.", this.chaudiereId));
		}
		return reponse;
	}

	public static TempService getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new TempService(id);
		}
		//System.out.println(instance);
		return instance;
	}
}
