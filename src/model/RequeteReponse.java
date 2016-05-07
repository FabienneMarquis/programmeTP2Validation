package model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class RequeteReponse extends Service {

	protected SMSEntrant sms;
	public static TempRequest tempService;
	public static AbsRequest asbService;
	public static MessagerieRequest messService;
	
	public static Map<String,RequeteReponse> services;
	static {
		services = new HashMap<>();
		try {
			tempService = TempRequest.getInstance("TE2016aaaa");
			services.put(tempService.id, tempService);
			
			asbService = AbsRequest.getInstance("AB2016aaaa");
			services.put(asbService.id, asbService);
			
			messService = MessagerieRequest.getInstance("ME2016aaaa");
			services.put(messService.id, messService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RequeteReponse(String id) throws Exception {
		super(id);
	}
	
	public Map<Employe,String> lancer() {
		return null;
	}
	
	public abstract Map<Employe,String> lancer(SMSEntrant sms);
	
	public static Map<Employe,String> traiter(SMSEntrant sms) {
		RequeteReponse service;
		if (!Pattern.compile("[a-zA-Z]").matcher(sms.getService()).find()) {
			service = RequeteReponse.messService;
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
