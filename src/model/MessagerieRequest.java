package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessagerieRequest extends RequeteReponse {
	
	String noDest;
	String message;
	private static int maxMessage = 5;
	
	public static Map<Employe, MessageGratuit> donneesMessages = new HashMap<>();
	
	public MessagerieRequest(Employe emp, String[] args) {
		super(emp);
		this.noDest = args[0];
		this.message = String.join(" ", 
				Arrays.asList(
						Arrays.copyOfRange(args, 1, args.length)
						)
				);
	}

	@Override
	public Map<Employe, String> lancer() {
		Map<Employe,String> reponse = new HashMap<>();
		
		if (donneesMessages.get(this.demandeur).verifierNbMessages() >= maxMessage) {
			reponse.put(null, "Nombre de messages dépassé");
			return reponse;
		}

		String telDestNorm = this.noDest.replaceAll("\\D", "");
		if (telDestNorm.equals(this.demandeur.noTel)) {
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
}
