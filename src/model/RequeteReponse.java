package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequeteReponse extends Service {

	/*
	 * Données. Selon le diagramme des classes, le Classe RequeteReponse connaît
	 * toutes les données de chaudière, d'absence et de messages.
	 */

	// Cardinalité déduite : RR - 1 : 0..* - TemperatureChaudiere
	public static Map<Integer, TemperatureChaudiere> chaudieres = new HashMap<>();

	static {
		chaudieres.put(1, new TemperatureChaudiere(1));
		chaudieres.put(2, new TemperatureChaudiere(2));
		chaudieres.put(3, new TemperatureChaudiere(3));
		chaudieres.put(4, new TemperatureChaudiere(4));
	}

	// Cardinalité déduite : RR - 1 : 0..* - MessageGratuit
	static Map<Integer, MessageGratuit> donneesMessages = new HashMap<>();

	static {
		for (Integer idEmp : Employe.employes.keySet()) {
			donneesMessages.put(idEmp, new MessageGratuit(idEmp));
		}
	}

	// Cardinalité déduite : RR - 1 : 1 - Absences

	public RequeteReponse() {
		super(3, "RequeteReponse");
	}

	private int maxMessage = 5;

	@Override
	/*
	 * Selon le diagramme, la méthode formaterMessage devrait à la fois lancer
	 * le traitement et formater le résultat.
	 */

	public String formaterMessage() {

		String serviceRRDemande = this.parametreService[0];

		switch (serviceRRDemande) {
		case "VerfTemp":
			int idChaudiere = Integer.valueOf(this.parametreService[1]);
			try {
				return String.format("La chaudière %d a une température de %f °C", idChaudiere,
						RequeteReponse.chaudieres.get(idChaudiere).releverTemperature());
			} catch (Exception e) {
				return String.format("Chaudière %d inexistante.", idChaudiere);
			}
		case "VerfAbs":
			String duree = this.parametreService[1];

			if (duree.equals("Jour")) {
				Absences.faireListeJour();
			} else if (duree.equals("Semaine")) {
				Absences.faireListeSemaine();
			}

			if (Absences.nombreEmployes == 0) {
				return "Il n'y a aucune absence";
			} else {
				return String.format("Les employés %s ont été absent.",
						String.join(", ", Absences.listIdEmployes.stream().map(id -> {
							if ((employe = Employe.employes.get(id)) != null) {
								return employe.prenomEmploye + " " + employe.nomEmploye;
							} else {
								return null;
							}
						}).filter(nom -> nom != null).collect(Collectors.toList())));
			}
		default: // Messages entre usagers

			if (RequeteReponse.donneesMessages.get(employe.idEmploye).verifierNbMessages() > maxMessage) {
				return "Nombre de messages dépassé";
			}

			String telDest = this.parametreService[0];
			String telDestNorm = telDest.replaceAll("\\D", "");
			if (telDestNorm.equals(employe.noTel)) {
				return "Vous ne pouvez pas vous contactez";
			}

			if (Employe.employes.values().stream().noneMatch(emp -> emp.noTel.equals(telDestNorm))) {
				return "Erreur de numéro";
			} else {
				return null;
			}
		}
	}

}
