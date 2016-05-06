package model;

import java.util.Map;

public class SemaineAbsService extends AbsRequest {
	
	private static SemaineAbsService instance;

	private SemaineAbsService(String id) {
		super(id);
	}
	
	public static SemaineAbsService getInstance(String id) {
		if (instance == null) {
			instance = new SemaineAbsService(id);
		}
		return instance;
	}

	@Override
	public Map<Employe, String> lancer() {
		Absences.faireListeSemaine();
		return AbsRequest.createResponse();
	}
}
