package model;

public class RequeteReponse extends Service {
	
	public RequeteReponse(Employe employe, String[] args) {
		super(3, "RequeteReponse");
		this.employe = employe;
		this.parametreService = args;
	}

}
