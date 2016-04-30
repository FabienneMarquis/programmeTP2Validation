package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Service {

	private int idService;
	private String nomService;
	public Employe employe; // Remplace idEmploye et mdpEmploye
	public String[] parametreService;
	//Object resultatBrut;

	public Service(int idService, String nomService) {
		super();
		this.idService = idService;
		this.nomService = nomService;
	}

	public abstract String formaterMessage();
	
}
