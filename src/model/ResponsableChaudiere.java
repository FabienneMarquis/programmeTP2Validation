package model;

import java.util.Arrays;

public class ResponsableChaudiere extends Employe {

	public ResponsableChaudiere(int idEmploye, String nomEmploye, String prenomEmploye, String mdp, String noTel) {
		super(idEmploye, nomEmploye, prenomEmploye, mdp, Arrays.asList(new String[]{"VerfTemp"}), noTel);
	}
	
}
