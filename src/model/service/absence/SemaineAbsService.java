package model.service.absence;

import java.util.Map;

import model.employe.Employe;

public class SemaineAbsService extends AbsService {
	
	private static SemaineAbsService instance;

	private SemaineAbsService(String id) throws Exception {
		super(id);
	}
	
	public static SemaineAbsService getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new SemaineAbsService(id);
		}
		return instance;
	}

	@Override
	public Map<Employe, String> lancer() {
		Absences.faireListeSemaine();
		return AbsService.createResponse();
	}
}
