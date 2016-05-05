package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RequeteReponse extends Service {

	/*
	 * Données. Selon le diagramme des classes, le Classe RequeteReponse connaît
	 * toutes les données de chaudière, d'absence et de messages.
	 */

	// Cardinalité déduite : RR - 1 : 1 - Absences

	private Map<Employe, String> responses;
	public Employe demandeur;

	public RequeteReponse(Employe employe) {
		super(3, "RequeteReponse");
		this.demandeur = employe;
	}
	
	public abstract Map<Employe,String> lancer();

	public static RequeteReponse newRequete(Employe employe, String[] args) {
		String serviceRRDemande = args[0];
		RequeteReponse rr;
		
		switch (serviceRRDemande) {
		case "VerfTemp":
			int idChaudiere = Integer.valueOf(args[1]);
			rr = new TempRequest(employe, idChaudiere);
			break;
		case "VerfAbs":
			String duree = args[1];
			rr = new AbsRequest(employe, duree);
			break;
		default: // Messages entre usagers
			rr = new MessagerieRequest(employe, args);
		}
		return rr;
	}

}
