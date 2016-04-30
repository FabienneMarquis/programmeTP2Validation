package model;

import java.util.HashMap;
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
		employes.put(1, new Employe(1, "Jodoin", "Bill", "11111", new String[] { "VerfTemp", "Messagerie" }, "4182564852"));
		employes.put(2, new Employe(2, "Bilodeau", "Johanne", "11111", new String[] { "VerfAbs", "Messagerie" }, "2525"));
	}

	public int idEmploye;
	public String nomEmploye;
	public String prenomEmploye;
	public String mdp; // Déplacé de Service
	public String[] servicesAuth; // Respect du cahier de charge
	public String noTel;

	public Employe(
			int idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			String[] servicesAuth,
			String noTel) {
		super();
		this.idEmploye = idEmploye;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.mdp = mdp; 
		this.servicesAuth = servicesAuth;
		this.noTel = noTel;
	}
}
