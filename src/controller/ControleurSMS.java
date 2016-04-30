package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.Employe;
import model.RequeteReponse;
import model.Service;

/*
 * Controleur faisant le lien entre Employe et Service
 */
public class ControleurSMS {

	// ControleurVue ctrlVue = new ControleurVue();

	private static Service requeteReponse = new RequeteReponse();
	// Autres services non pris en compte
	
	private static List<String> servicesRR = Arrays.asList(new String[]{"VerfTemp","VerfAbs","Messagerie"});

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
						interfaceMobile.afficherMessage("Nom de service invalide.");
					} else {
					
						boolean authorise = false;
						for (String serviceAuthorise : employe.servicesAuth) {
							
							if (serviceDemande.equals(serviceAuthorise)) {
								authorise = true;
								ControleurSMS.requeteReponse.employe = employe;
								ControleurSMS.requeteReponse.parametreService = Arrays.copyOfRange(args, 2, args.length);
								
								String reponse = ControleurSMS.requeteReponse.formaterMessage();
								
								if (reponse != null) {
									interfaceMobile.afficherMessage(reponse);
								} else if (serviceDemande.equals("Messagerie")) {
									String destTel = args[2];
									String message = String.join(" ", 
											Arrays.asList(
													Arrays.copyOfRange(args, 3, args.length)
													)
											);
									newReponseSMS(employe, message, destTel, true);
								}
							}
						}
						if (!authorise) {
							interfaceMobile.afficherMessage("Authorisation invalide.");
						}
					}
				}
				else 
				{
					interfaceMobile.afficherMessage("Mot de passe invalide.");
				}
			}
		}
	}
	
	public static void newReponseSMS(Employe envoyeur, String message, String noTel, boolean allumer) {
		Optional<Employe> destEmploye = Employe.employes.values().stream().filter(emp -> emp.noTel.equals(noTel)).findFirst();
		destEmploye.ifPresent(emp -> {
			try {
				ControleurVue vue = ControleurVue.vues.get(emp);
				if (vue == null && allumer) {
					vue = ControleurVue.allumerTelephone(emp);
				}
				vue.afficherMessage(envoyeur.prenomEmploye + " " + envoyeur.nomEmploye + " : " + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
