package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbsRequest extends RequeteReponse {
	
	private String duree;
	
	public AbsRequest(Employe emp, String duree) {
		super(emp);
		this.duree = duree;
	}

	@Override
	public Map<Employe, String> lancer() {
		Map<Employe,String> reponse = new HashMap<>();
		
		if (duree.equals("Jour")) {
			Absences.faireListeJour();
		} else if (duree.equals("Semaine")) {
			Absences.faireListeSemaine();
		}

		if (Absences.nombreEmployes == 0) {
			reponse.put(null, "Il n'y a aucune absence");
		} else {
			reponse.put(null, String.format(
					"Les employés %s ont été absents.", 
					String.join(", ", this.getListeAbsents())
					));
		}
		return reponse;
	}
		
	private List<String> getListeAbsents() {
		return Absences.listIdEmployes
				.stream()
				.map(id -> Employe.getFullName(id))
				.filter(nom -> nom != null)
				.collect(Collectors.toList());
	}
}
