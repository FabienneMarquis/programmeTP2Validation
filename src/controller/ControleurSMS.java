package controller;

import java.util.Arrays;

import model.Employe;
import model.RequeteReponse;

public class ControleurSMS {
	
	ControleurVue ctrlVue;
	
	public void recevoirSMS(String sms) {
		
		String[] args = sms.split(" ");
		
		// verifier format
		if (verifierFormat(args)) {
			
			String identifiant = args[0];
			String motDePasse = args[1];
			String serviceDemande = args[2];
			Employe employe;
			
			if ((employe = Employe.authentifier(identifiant, motDePasse, serviceDemande)) != null) {
				
				RequeteReponse rr = new RequeteReponse(employe, Arrays.copyOfRange(args, 2, args.length));
				
				rr.traiter();
				
				String reponse = rr.formaterMessage();
				if (reponse != null) {
					ctrlVue.afficherMessage(reponse);
				}
				
			} else {
				this.retournerReponse("Echec_authentification", serviceDemande);
			}
			
		} else {
			this.retournerReponse("Echec_format", null);
		}
	}
	
	private boolean verifierFormat(String[] args) {
		return true;
	}
	
	private boolean authentifier(String id, String mdp, String serviceDemande) {
		return true;
	}
	
	private void traiter() {
		
	}
	
	private String formaterMessage(String brute, String service) {
		return "La..... fait 12.4 degres";
	}
	
	public static void main(String[] args) {
		ControleurSMS ctrl = new ControleurSMS();
		ctrl.recevoirSMS("1 11111 VerfTemp C123");
	}
	
	
}
