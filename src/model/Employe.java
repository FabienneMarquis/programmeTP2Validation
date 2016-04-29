package model;

import java.util.HashMap;
import java.util.Map;

public class Employe {
	
	static Map<Integer,Employe> employes = new HashMap<Integer,Employe>();
	static {
		employes.put(
				1, 
				new Employe(1, "Bill", "Jodoin", "11111", new String[]{"VerfTemp"})
				);
	}
	
	int idEmploye;
	String nomEmploye;
	String prenomEmploye;
	String mdp;
	String[] servicesAuth;

	public Employe(
			int idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			String[] servicesAuth) {
		super();
		this.idEmploye = idEmploye;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.mdp = mdp;
		this.servicesAuth = servicesAuth;
	}
	
	public static Employe authentifier(String id, String passwd, String serviceDemande) throws Exception {
		Employe emp = employes.get(id);
		if (emp == null) {
			throw new Exception("Identifiant inexistant.");
		} else if (emp.getPW().equals(passwd)) {
			for (String serviceAuthorise : emp.getServicesAuth()) {
				if (serviceDemande.equals(serviceAuthorise)) {
					return emp;
				}
			}
			throw new Exception("Authorisation invalide.");
		} else {
			throw new Exception("Mot de passe invalide.");
		}
	}
	
	private String getPW() {
		return this.mdp;
	}
	
	private String[] getServicesAuth() {
		return this.servicesAuth;
	}
	
}
