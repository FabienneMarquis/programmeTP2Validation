package model.employe;

import java.util.Arrays;

public class ResponsableChaudiere extends Employe {

	public ResponsableChaudiere(String idEmploye, String nomEmploye, String prenomEmploye, String mdp, String noTel) throws Exception {
		super(idEmploye, nomEmploye, prenomEmploye, mdp, Arrays.asList(new String[]{"VerfTemp"}), noTel);
	}
	
}
