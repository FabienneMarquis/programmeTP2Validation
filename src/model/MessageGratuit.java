package model;

import java.util.HashMap;
import java.util.Map;

public class MessageGratuit {
	
	private String id;
	// Attribut ajouter par nécessité
	private int compteur;
	
	public MessageGratuit(String idEmploye) {
		this.id = idEmploye;
		this.compteur = 0;
	}
	
	public int verifierNbMessages() {
		return this.compteur;
	}
	
	public void incrementer() {
		this.compteur += 1;
	}

}
