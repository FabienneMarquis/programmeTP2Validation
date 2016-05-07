package model.service.absence;

import java.util.Map;

import model.employe.Employe;

public class JourAbsService extends AbsService {

	private static JourAbsService instance;

	private JourAbsService(String id) throws Exception {
		super(id);
	}
	
	public static JourAbsService getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new JourAbsService(id);
		}
		return instance;
	}

	@Override
	public Map<Employe, String> lancer() {
		Absences.faireListeJour();
		return AbsService.createResponse();
	}

}
