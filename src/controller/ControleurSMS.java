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

/*
 * Controleur faisant le lien entre Employe et Service
 */
public class ControleurSMS {

	// ControleurVue ctrlVue = new ControleurVue();

	//private static Service requeteReponse = new RequeteReponse();
	// Autres services non pris en compte
	
	private static List<String> servicesRR = Arrays.asList(new String[]{"VerfTemp","VerfAbs","Messagerie"});
	private static Employe SMSEmploye = new Employe(999, "Serveur SMS", "", "090909", "090909");

	public static void newSMS(String sms, ControleurVue interfaceMobile) {
		
		String[] args = sms.split(" ");
		
		if (args.length >= 4) {
			
			String identifiant = args[0];
			String motDePasse = args[1];
			String serviceDemande = args[2];
			serviceDemande = (!serviceDemande.equals("VerfTemp") && !serviceDemande.equals("VerfAbs")) ? 
					"Messagerie" : serviceDemande;
			Employe employe = null;
			
			employe = Employe.employes.get(Integer.valueOf(identifiant));
			
			if (employe == null) 
			{
				interfaceMobile.afficherMessage("Identifiant inexistant.");
			}
			else 
			{
				interfaceMobile.lierEmplolye(employe);
				
				if (employe.mdp.equals(motDePasse)) {
					
					if (!servicesRR.contains(serviceDemande)) {
						ControleurSMS.newReponseSMS(SMSEmploye, "Nom de service invalide.", employe, false);
					} else {
					
						boolean authorise = false;
						for (String serviceAuthorise : employe.servicesAuth) {
							
							if (serviceDemande.equals(serviceAuthorise)) {
								authorise = true;
								
								RequeteReponse requete = RequeteReponse.newRequete(employe, Arrays.copyOfRange(args, 2, args.length));

								Map<Employe,String> reponses = requete.lancer();
								
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
						}
						if (!authorise) {
							ControleurSMS.newReponseSMS(SMSEmploye, "Authorisation invalide.", employe, false);
						}
					}
				}
				else 
				{
					ControleurSMS.newReponseSMS(SMSEmploye, "Mot de passe invalide.", employe, false);
				}
			}
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
