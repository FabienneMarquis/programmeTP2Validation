package model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import model.employe.Employe;
import model.service.absence.AbsService;
import model.service.messagerie.MessagerieService;
import model.service.temperature.TempService;

public abstract class RequeteReponseService extends Service {

	protected SMSEntrant sms;
	public static TempService tempService;
	public static AbsService asbService;
	public static MessagerieService messService;
	
	public static Map<String,RequeteReponseService> services;
	static {
		services = new HashMap<>();
		try {
			tempService = TempService.getInstance("TE2016aaaa");
			services.put(tempService.id, tempService);
			
			asbService = AbsService.getInstance("AB2016aaaa");
			services.put(asbService.id, asbService);
			
			messService = MessagerieService.getInstance("ME2016aaaa");
			services.put(messService.id, messService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RequeteReponseService(String id) throws Exception {
		super(id);
	}
	
	public Map<Employe,String> lancer() {
		return null;
	}
	
	public abstract Map<Employe,String> lancer(SMSEntrant sms);
	
	public static Map<Employe,String> traiter(SMSEntrant sms) {
		RequeteReponseService service;
		if (!Pattern.compile("[a-zA-Z]").matcher(sms.getService()).find()) {
			service = RequeteReponseService.messService;
		} else {
			service = services.get(sms.getService());
		}
		//System.out.println(service.authentifier(sms.getEmploye()));
		Map<Employe,String> r = new HashMap<>();
		
		if (service == null) {
			r.put(sms.getEmploye(), "Service non disponible");
			return r;
		} 
		else if (!service.authentifier(sms.getEmploye())) {
			r.put(sms.getEmploye(), "Mot de passe invalide");
			return r;
		} 
		else {
			return service.lancer(sms);
		}
	}
}
