package model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ObjetIdentifie {
	
	public String id;
	
	private static Set<String> identifiants = new HashSet<>();
	
	public ObjetIdentifie(String identifiant) throws Exception {
//		System.out.println(this.getPrefix());
		if (!identifiant.substring(0, 6).equals(this.getPrefix())) {
			throw new Exception(
					String.format("Identifiant %s pour class %s "
						+ "en %s est non valide.\n"
						+ "Devrait ressembler à %s.",
						identifiant,
						this.getClass().getSimpleName(),
						this.getYear(),
						this.getPrefix() + "xxxx"
						)
					);
		}
		System.out.println(identifiant + " valide");
		this.id = identifiant;
		identifiants.add(this.id);
	}
	
	public ObjetIdentifie() {
		super();
		this.id = generateId();
	}
	
	public String generateId() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String suffix = "";
		for (int i = 0; i < 4; i++) {
			suffix +=  getRandomLetter(alphabet);
		}
		return getPrefix() + suffix;
	}
	
	public String getRandomLetter(String letters) {
		int index = (int) Math.floor(Math.random() * letters.length());
		return letters.substring(index, index + 1);
	}
	
	public String getPrefix() {
		return getClassPrefix() + getYear();
	}
	
	public String getClassPrefix() {
		return this.getClass().getSimpleName().substring(0, 2).toUpperCase();
	}
	
	public String getYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR));
	}
}
