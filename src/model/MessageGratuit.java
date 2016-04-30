package model;

import java.util.HashMap;
import java.util.Map;

public class MessageGratuit {
	
	private int id;
	// Attribut ajouter par nécessité
	private int compteur;
	
	public MessageGratuit(int id) {
		this.id = id;
		this.compteur = 0;
	}
	
	public int verifierNbMessages() {
		return this.compteur;
	}

}
