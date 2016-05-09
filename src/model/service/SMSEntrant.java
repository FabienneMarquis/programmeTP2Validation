package model.service;

import java.nio.file.FileVisitResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import model.ObjetIdentifie;
import model.employe.Employe;

public class SMSEntrant {
	
	private Employe employe;
	private String motDePasse;
	private String service;
	private String arg3;
	private String[] trailingArgs;
	private List<String> contentError = new ArrayList<>();
	private List<String> formError = new ArrayList<>();
	
	public SMSEntrant(String smsString) {
		if (validerLongueur(smsString)) {
			String[] args = getArgs(smsString);
			setEmploye(args[0]);
			setMotDePasse(args[1]);
			this.arg3 = args[2];
			setService(args[2]);
			this.trailingArgs = Arrays.copyOfRange(args, 3, args.length);
			validerTrailing();
		} else {
			formError.add("Format de sms invalide.");
		}
	}
	
	private void validerTrailing() {
		if (this.service != null && !this.service.equals("Messagerie")) {
			if (!ObjetIdentifie.validerIdentifiantGenerique(this.trailingArgs[0])) {
				this.formError.add("Format de sous-service non valide.");
			}
		}
	}

	private static boolean validerLongueur(String string) {
		return (getArgs(string).length >= 4);
	}
	
	private void setEmploye(String id) {
		if (!ObjetIdentifie.validerIdentifiantGenerique(id)) {
			this.formError.add("Format d'identifiant non valide.");
		} else {
			employe = Employe.employes.get(id);
			if (employe == null) {
				this.contentError.add("Identifiant inexistant.");
			}
		}
	}
	
	private void setMotDePasse(String mdp) {
		if (!Employe.validerMDP(mdp)) {
			this.formError.add("Format de mot de passe invalide.");
		} else {
			this.motDePasse = mdp;
			this.validerMotDePasse();
		}
	}
	
	private boolean validerMotDePasse() {
		if (employe != null && !employe.mdp.equals(motDePasse)) {
			contentError.add("Mot de passe incorrect.");
			return false;
		}
		return true;
	}
	
	private void setService(String servNom) {
		if (ObjetIdentifie.validerIdentifiantGenerique(servNom)) {
			this.service = servNom;
		} else if (isServMessagerieDemande(servNom)) {
			this.service = "Messagerie";
		} else {
			this.formError.add("Format de service invalide.");
		}
	}
	
	public static boolean isServMessagerieDemande(String arg) {
		return !Pattern.compile("[a-zA-Z]").matcher(arg).find();
	}

	public boolean isValid() {
		return (isFormatValide() && contentError.size() == 0);
	}
	
	public boolean isFormatValide() {
		return (formError.size() == 0);
	}
	
	public String getError() {
		String firstError = this.formError.isEmpty() ? null : this.formError.get(0);
		if (firstError != null) {
			return firstError;
		}
		return this.contentError.isEmpty() ? null : this.contentError.get(0);
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
	
	public List<String> getFormErrors() {
		return formError;
	}
}