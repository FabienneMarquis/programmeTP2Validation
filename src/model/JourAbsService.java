package model;

import java.util.Map;

public class JourAbsService extends AbsRequest {

	private static JourAbsService instance;

	private JourAbsService(String id) {
		super(id);
	}
	
	public static JourAbsService getInstance(String id) {
		if (instance == null) {
			instance = new JourAbsService(id);
		}
		return instance;
	}

	@Override
	public Map<Employe, String> lancer() {
		Absences.faireListeJour();
		return AbsRequest.createResponse();
	}

}
