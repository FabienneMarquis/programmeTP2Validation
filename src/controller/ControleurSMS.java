package controller;

import model.Employe;

public class ControleurSMS {
	
	Employe[] employes = new Employe[]{new Employe(1, "Bill", "Jodoin")};
	ContoleurVue ctrlVue;
	
	public void recevoirSMS(String sms) {
		
		String[] args = sms.split(" ");
		
		// verifier format
		if (verifierFormat(args)) {
			
			String identifiant = args[0];
			String motDePasse = args[1];
			String serviceDemande = args[2];
			
			if (authentifier(identifiant, motDePasse, serviceDemande)) {
				
				RequeteReponse rr = new RequeteReponse(serviceDemande);
				
				String reponse = rr.traiter();
				if (reponse != null) {
					this.retournerReponse(reponse);
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
	
	private String retournerReponse(String reponseBrute, String service) {
		String reponseFormatee = this.formaterMessage(reponseBrute, service);
		ctrlVue.afficherMessage(reponseFormatee);
	}
	
	private String formaterMessage(String brute, String service) {
		return "La..... fait 12.4 degres";
	}
	
	public static void main(String[] args) {
		ControleurSMS ctrl = new ControleurSMS();
		ctrl.recevoirSMS("1 11111 VerfTemp C123");
	}
	
	
}
