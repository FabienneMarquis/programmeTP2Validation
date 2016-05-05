package model;

import java.util.Arrays;

public class Superviseur extends Employe {

	public Superviseur(int idEmploye, String nomEmploye, String prenomEmploye, String mdp,
			String noTel) {
		super(idEmploye, nomEmploye, prenomEmploye, mdp, Arrays.asList(new String[]{"VerfAbs"}), noTel);
	}
}
