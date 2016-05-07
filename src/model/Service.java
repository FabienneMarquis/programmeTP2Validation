package model;

import java.util.List;
import java.util.Map;

public abstract class Service {

	public String idService;
	public List<Employe> empAuth;
	
	public Service(String idService) {
		this.idService = idService;
	}

	public abstract Map<Employe,String> lancer();
	
	public boolean authentifier(Employe emp) {
		for (String serviceAuthorise : emp.servicesAuth) {
			System.out.println(serviceAuthorise);
			if (idService.equals(serviceAuthorise)) {
				return true;
			}
		}
		return false;
	}
}
