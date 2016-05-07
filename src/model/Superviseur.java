package model;

import java.util.Arrays;

public class Superviseur extends Employe {

	public Superviseur(String idEmploye, String nomEmploye, String prenomEmploye, String mdp,
			String noTel) throws Exception {
		super(idEmploye, nomEmploye, prenomEmploye, mdp, Arrays.asList(new String[]{"VerfAbs"}), noTel);
	}
}
