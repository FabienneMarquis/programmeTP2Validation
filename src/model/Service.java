package model;

public abstract class Service {
	int idService;
	String nomService;
	Employe employe;
	String[] parametreService;
	String resultatBrut;
	
	public Service(int idService, String nomService) {
		super();
		this.idService = idService;
		this.nomService = nomService;
	}

	public abstract void traiter();
	public abstract String formaterMessage();
	
	
}
