package model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.employe.Employe;

public class SMSEntrant {
	
	private Employe employe;
	private String motDePasse;
	private String service;
	private String arg3;
	private String[] trailingArgs;
	private List<String> errors = new ArrayList<>();
	
	public SMSEntrant(String smsString) throws IndexOutOfBoundsException {
		if (validerLongueur(smsString)) {
			String[] args = getArgs(smsString);
			setEmploye(args[0]);
			setMotDePasse(args[1]);
			this.arg3 = args[2];
			setService(args[2]);
			this.trailingArgs = Arrays.copyOfRange(args, 3, args.length);
		} else {
			errors.add("Format de sms invalide.");
		}
	}
	
	private static boolean validerLongueur(String string) {
		return (getArgs(string).length >= 4);
	}
	
	private void setEmploye(String id) {
		employe = Employe.employes.get(id);
		if (employe == null) {
			this.errors.add("Identifiant non valide.");
		}
	}
	
	private void setMotDePasse(String mdp) {
		this.motDePasse = mdp;
		this.validerMotDePasse();
	}
	
	private boolean validerMotDePasse() {
		if (employe != null && !employe.mdp.equals(motDePasse)) {
			errors.add("Mot de passe invalide.");
			return false;
		}
		return true;
	}
	
	private void setService(String servNom) {
		this.service = servNom;
	}

	public boolean isValid() {
		return (errors.size() == 0);
	}
	
	public String getError() {
		return this.errors.isEmpty() ? null : this.errors.get(0);
	}


	public Employe getEmploye() {
		return this.employe;
	}
	
	private static String[] getArgs(String string) {
		string = string.replaceAll("  +", " ");
		return string.split(" ");
	}
	
	public String getArg3() {
		return this.arg3;
	}

	public String[] getTrailingArgs() {
		return this.trailingArgs;
	}
	
	public String getService() {
		return this.service;
	}
}