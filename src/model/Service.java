package model;

import java.util.Map;

public abstract class Service extends ObjetIdentifie {
	
	public Service(String idService) throws Exception {
		super(idService);
	}

	public abstract Map<Employe,String> lancer();
	
	public boolean authentifier(Employe emp) {
		for (String serviceAuthorise : emp.servicesAuth) {
			//System.out.println(serviceAuthorise);
			if (id.equals(serviceAuthorise)) {
				return true;
			}
		}
		return false;
	}
}
