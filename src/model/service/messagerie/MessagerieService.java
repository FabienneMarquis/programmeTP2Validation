package model.service.messagerie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.employe.Employe;
import model.service.RequeteReponseService;
import model.service.SMSEntrant;

public class MessagerieService extends RequeteReponseService {
	
	String noDest;
	String message;
	private static int maxMessage = 5;
	private static MessagerieService instance;
	
	public static Map<Employe, MessageGratuit> donneesMessages = new HashMap<>();
	
	public MessagerieService(String id) throws Exception {
		super(id);
	}

	@Override
	public Map<Employe, String> lancer(SMSEntrant sms) {
		this.sms = sms;
		this.noDest = sms.getArg3();
		this.message = String.join(" ", Arrays.asList(sms.getTrailingArgs()));
		
		Map<Employe,String> reponse = new HashMap<>();
		
		if (donneesMessages.get(this.sms.getEmploye()).verifierNbMessages() >= maxMessage) {
			reponse.put(null, "Nombre de messages dépassé");
			return reponse;
		}

		String telDestNorm = this.noDest.replaceAll("\\D", "");
		if (telDestNorm.equals(this.sms.getEmploye().noTel)) {
			reponse.put(null, "Vous ne pouvez pas vous contactez");
			return reponse;
		}

		Optional<Employe> destEmploye = Employe.employes.values().stream().filter(emp -> emp.noTel.equals(telDestNorm)).findFirst();
		if (destEmploye.isPresent()) {
			reponse.put(destEmploye.get(), this.message);
			return reponse;
		} else {
			reponse.put(null, "Erreur de numéro");
			return reponse;
		}
	}

	public static MessagerieService getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new MessagerieService(id);
		}
		return instance;
	}
}
