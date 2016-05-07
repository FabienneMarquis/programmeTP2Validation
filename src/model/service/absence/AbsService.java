package model.service.absence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.employe.Employe;
import model.service.RequeteReponseService;
import model.service.SMSEntrant;

public class AbsService extends RequeteReponseService {
	
	private static Map<String,AbsService> absServices;
	private static AbsService instance;
	public static SemaineAbsService semaineService;
	public static JourAbsService jourService;
	
	static {
		absServices = new HashMap<>();
		
		try {
			semaineService = SemaineAbsService.getInstance("SE2016aaaa");
			absServices.put(semaineService.id, semaineService);		
			
			jourService = JourAbsService.getInstance("JO2016aaaa");
			absServices.put(jourService.id, jourService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected AbsService(String id) throws Exception {
		super(id);
	}

	@Override
	public Map<Employe, String> lancer(SMSEntrant sms) {
		this.sms = sms;
		AbsService service = absServices.get(sms.getTrailingArgs()[0]);
		if (service == null) {
			Map<Employe,String> r = new HashMap<>();
			r.put(sms.getEmploye(), "Sous-service non disponible");
			return r;
		} else {
			return service.lancer();
		}
	}
	
	public static Map<Employe,String> createResponse() {
		List<String> noms = getListeAbsents();
		
		Map<Employe,String> reponse = new HashMap<>();
		if (Absences.nombreEmployes == 0) {
			reponse.put(null, "Il n'y a aucune absence");
		} else {
			reponse.put(null, String.format(
					"Les employés %s ont été absents.", 
					String.join(", ", noms)
					));
		}
		return reponse;
	}
		
	public static List<String> getListeAbsents() {
		return Absences.listIdEmployes
				.stream()
				.map(id -> Employe.getFullName(id))
				.filter(nom -> nom != null)
				.collect(Collectors.toList());
	}

	public static AbsService getInstance(String id) throws Exception {
		if (instance == null) {
			instance = new AbsService(id);
		}
		return instance;
	}
}
