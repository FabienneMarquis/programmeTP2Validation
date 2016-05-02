package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classe qui représente un employé
 * 
 * Diagramme de classe:
 * 	enoyerMessageGratuit() écarté car il n'y pas d'instance de la classe en
 * mémoire avant d'envoyer un message : Seulement après l'authentification
 * avec les données du message.
 * 
 * 	recevoirNotifications() (ou recevoirMessageSMS()) pourrait exister seulement
 * pour des fins d'historique et non pas pour transmettre à une vue. 
 * Alors on devrait renommer ces méthodes (e.g. enregistrerLogNotifications());
 */
public class Employe {

	/*
	 * En absence d'une base de données, les données des employés
	 * auraient pu aussi pu être mises dans Service.
	 * 
	 * Cardinalité déduite : Service - 1 : 0..* - Employe
	 */
	public static Map<Integer, Employe> employes = new HashMap<Integer, Employe>();
	static {
		employes.put(1, new ResponsableChaudiere(1, "Jodoin", "Bill", "11111", "4182564852"));
		employes.put(2, new Superviseur(2, "Bilodeau", "Johanne", "11111", "2525"));
		employes.put(3, new Employe(3, "Savoie", "Marie", "11111", "2222"));
	}

	public int idEmploye;
	public String nomEmploye;
	public String prenomEmploye;
	public String mdp; // Déplacé de Service
	public List<String> servicesAuth; // Respect du cahier de charge
	public String noTel;

	public Employe(
			int idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			List<String> servicesAuth,
			String noTel) {
		super();
		this.idEmploye = idEmploye;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.mdp = mdp; 
		this.servicesAuth = new ArrayList<>();
		this.servicesAuth.add("Messagerie");
		this.servicesAuth.addAll(servicesAuth);
		this.noTel = noTel;
	}
	
	public Employe(
			int idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			String noTel) {
		this(idEmploye, nomEmploye, prenomEmploye, mdp, new ArrayList<>(), noTel);
	}
}
