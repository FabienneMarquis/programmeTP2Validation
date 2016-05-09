package model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public abstract class ObjetIdentifie {
	
	public String id;
	
	private static Set<String> identifiants = new HashSet<>();
	
	public ObjetIdentifie(String identifiant) throws Exception {
		this.setId(identifiant);
	}
	
	public void setId(String identifiant) throws Exception {
		if (identifiant == null) {
			identifiant = generateId();
		}
		
		if (!validerIdentifiant(identifiant)) {
			throw new Exception(
					String.format("Identifiant %s pour class %s "
						+ "en %s est non valide.\n"
						+ "Devrait ressembler à %s.",
						identifiant,
						this.getClass().getSimpleName(),
						ObjetIdentifie.getYear(),
						this.getPrefix() + "xxxx"
						)
					);
		}
		
		identifiants.add(identifiant);
		this.id = identifiant;
		assert validerLongeurId(this.id) : "Longueur de l'identifiant doit avoir 10 caractères";
		assert validerClassPrefix(this.id) : "les deux premiers caractère de l'identifiant ne correspondent pas au nom";
		assert validerAnneeDansId(this.id) : "L'année ne correspond pas à l'année courante";
		assert validerFin4lettres(this.id) : "les 4 derniers caractères de l'identifiant doit être alphabétiques";
		//System.out.println(identifiant + " valide");
	}
	
	public boolean validerIdentifiant(String identifiant) {
		if (!validerIdentifiantGenerique(identifiant)) {
			return false;
		}
		if (!validerClassPrefix(identifiant)) {
			return false;
		}
		return true;
	}
	
	public static boolean validerIdentifiantGenerique(String identifiant) {
		if (!(	validerLongeurId(identifiant)
				&& validerAnneeDansId(identifiant)
				&& validerFin4lettres(identifiant)
				)) {
			return false;
		}
		return true;
	}
	
	private String generateId() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String suffix = "";
		do {
			for (int i = 0; i < 4; i++) {
				suffix +=  getRandomLetter(alphabet);
			}
		} while (identifiants.contains(getPrefix() + suffix));
		
		return getPrefix() + suffix;
	}
	
	private static String getRandomLetter(String letters) {
		int index = (int) Math.floor(Math.random() * letters.length());
		return letters.substring(index, index + 1);
	}
	
	private String getPrefix() {
		return getClassPrefix() + getYear();
	}
	
	public String getClassPrefix() {
		return this.getClass().getSimpleName().substring(0, 2).toUpperCase();
	}
	
	private static String getYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR));
	}
	
	private static boolean validerLongeurId(String id) {
		return (id.length() == 10);
	}

	private boolean validerClassPrefix(String id) {
		return (id.substring(0, 2).equalsIgnoreCase(this.getClassPrefix()));
	}

	private static boolean validerAnneeDansId(String id) {
		String anneeEntree = id.substring(2, 6);
		return anneeEntree.equals(ObjetIdentifie.getYear());
	}

	private static boolean validerFin4lettres(String id) {
		return (id.substring(6, 9).matches("[A-Za-z]+"));
	}
}
