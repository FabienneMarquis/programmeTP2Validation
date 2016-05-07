package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import model.Employe;
import model.MessagerieRequest;
import model.RequeteReponse;
import model.SMSEntrant;
import model.Service;

/*
 * Controleur faisant le lien entre Employe et Service
 */
public class ControleurSMS {
	
	private static Employe SMSEmploye;
	
	static {
		try {
			SMSEmploye = new Employe("EM2016gdhe", "Serveur SMS", "", "090909", "090909");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void newSMS(String smsString, ControleurVue interfaceMobile) {
		
		SMSEntrant sms = new SMSEntrant(smsString);
		
		if (sms.getEmploye() == null) {
			String message = "Identifiant invalide";
			if (interfaceMobile == null) {
				System.out.println(message);
			} else {
				interfaceMobile.afficherMessage(message);
			}
		} else {
			if (interfaceMobile != null) {
				interfaceMobile.lierEmplolye(sms.getEmploye());
			}
			if (sms.isValid()) {
				newReponseSMS(RequeteReponse.traiter(sms), sms.getEmploye());
			} else if (sms.getEmploye() != null){
				newReponseSMS(SMSEmploye, sms.getError(), sms.getEmploye(), false);
			}
		}
	}
	
	private static void newReponseSMS(Map<Employe, String> reponses, Employe employe) {
		for (Entry<Employe,String> envoi : reponses.entrySet()) {
			Employe envoyeur;
			Employe receveur;
			if (envoi.getKey() == null) {
				envoyeur = ControleurSMS.SMSEmploye;
				receveur = employe;
			} else {
				envoyeur = employe;
				receveur = envoi.getKey();
			}
			ControleurSMS.newReponseSMS(envoyeur, envoi.getValue(), receveur, true);
		}
	}
	
	public static void newReponseSMS(Employe envoyeur, String message, Employe receveur, boolean allumer) {
		try {
			ControleurVue vue = ControleurVue.vues.get(receveur);
			if (vue == null && allumer) {
				vue = ControleurVue.allumerTelephone(receveur);
			}
			vue.afficherMessage(envoyeur.prenomEmploye + " " + envoyeur.nomEmploye + " : " + message);
			MessagerieRequest.donneesMessages.get(envoyeur).incrementer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
