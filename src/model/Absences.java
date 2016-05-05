package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Classe qui génère la liste des absences.
 */
public class Absences {
	
	/* Nous avons fait le choix
	 * de conserver les données d'absence dans un attribut statique.
	 */
	public static Map<Integer, LocalDate> absences = new HashMap<>();

	static {
		absences.put(1, LocalDate.now());
		absences.put(2, LocalDate.now().minusDays(3));
		absences.put(3, LocalDate.now().minusDays(14));
		absences.put(4, LocalDate.now().minusDays(264));
	}
	
	public static List<Integer> listIdEmployes; // Déduit que ce sont des employes absents
	public static int nombreEmployes; // Déduit que c'est le nombre d'employes absents
	
	public static void faireListeJour() {
		List<Integer> listAbsences = new ArrayList<>();
		for (Entry<Integer, LocalDate> entry : Absences.absences.entrySet()) {
			if (entry.getValue().equals(LocalDate.now())) {
				listAbsences.add(entry.getKey());
			}
		}
		Absences.listIdEmployes = listAbsences;
		Absences.nombreEmployes = listAbsences.size();
	}
	
	public static void faireListeSemaine() {
		List<Integer> listAbsences = new ArrayList<>();
		for (Entry<Integer, LocalDate> entry : Absences.absences.entrySet()) {
			if (entry.getValue().isAfter(LocalDate.now().minusDays(8))) {
				listAbsences.add(entry.getKey());
			}
		}
		Absences.listIdEmployes = listAbsences;
		Absences.nombreEmployes = listAbsences.size();
	}
}
