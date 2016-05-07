package model.employe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.ObjetIdentifie;
import model.service.RequeteReponseService;
import model.service.messagerie.MessageGratuit;
import model.service.messagerie.MessagerieService;

public class Employe extends ObjetIdentifie {

	public static Map<String, Employe> employes = new HashMap<>();
	static {
		try {
			Employe emp = new ResponsableChaudiere("RE2016abcd", "Jodoin", "Bill", "A22aaaa-aaa", "4182564852");
			emp.servicesAuth.add(RequeteReponseService.tempService.id);
			employes.put(emp.id, emp);
			
			Employe emp2 = new Superviseur("SU2016abce", "Bilodeau", "Johanne", "B12356as??as", "2525");
			emp2.servicesAuth.add(RequeteReponseService.asbService.id);
			employes.put(emp2.id, emp2);
			
			employes.put("EM2016abcf", new Employe("EM2016abcf", "Savoie", "Marie","C123WW4545&*88", "2222"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String nomEmploye;
	public String prenomEmploye;
	public String mdp; // Déplacé de Service
	public List<String> servicesAuth; // Respect du cahier de charge
	public String noTel;

	public Employe(
			String idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			List<String> servicesAuth,
			String noTel) throws Exception {
		super(idEmploye);
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		
		assert(Pattern.compile("^.{8,15}$").matcher(mdp).find()) 
			: "Longueur du mot de passe invalide (entre 8 et 15 caractères).";

		assert(Pattern.compile("\\d.*\\d").matcher(mdp).find()) 
			: "Le mot de passe ne contient pas deux chiffres.";

		assert(Pattern.compile("^[A-Z]").matcher(mdp).find())
			: "Le mot de passe ne débute pas par une lettre majuscule.";

		assert(Pattern.compile("[a-zA-Z].*[a-zA-Z]").matcher(mdp).find())
			: "Le mote de passe ne contient pas au moins deux lettres.";

		assert(Pattern.compile("[^\\W]").matcher(mdp).find())
			: "Le mot de passe ne contient pas de caractère spécial.";
		
		this.mdp = mdp; 
		this.servicesAuth = new ArrayList<>();
		this.servicesAuth.add(RequeteReponseService.messService.id);
		this.servicesAuth.addAll(servicesAuth);
		this.noTel = noTel;
		
		String deuxPremLettres = nomEmploye.substring(0, 2);

		assert invariantId1(idEmploye) : "Longueur de l'identifiant doit avoir 10 caractères";
		assert invariantId2(idEmploye, deuxPremLettres) : "les deux premiers caractère de l'identifiant ne correspondent pas au nom";
		assert invariantId3(idEmploye) : "L'année ne correspond pas à l'année courante";
		assert invariantId4(idEmploye) : "les 4 derniers caractères de l'identifiant doit être alphabétiques";

		assert invariantMdp1(mdp) : "Longueur du mot de passe doit entre 8 à 15 caractères";
		assert invariantMdp2(mdp) : "Le mot de passe doit contenir au moins 2 chiffres";
		assert invariantMdp3(mdp) : "Le mot de passe doit commencer par une lettre majuscule";
		assert invariantMdp4(mdp) : "Le mot de passe doit contenir au moins 2 lettres";
		assert invariantMdp5(mdp) : "Le mot de passe doit contenir au moins 1 caractère spécial";
		
		MessagerieService.donneesMessages.put(this, new MessageGratuit(idEmploye));
	}
	
	public Employe(
			String idEmploye, 
			String nomEmploye, 
			String prenomEmploye, 
			String mdp, 
			String noTel) throws Exception {
		this(idEmploye, nomEmploye, prenomEmploye, mdp, new ArrayList<>(), noTel);
	}
	
	public static String getFullName(String id) {
		Employe employe;
		if ((employe = Employe.employes.get(id)) != null) {
			return employe.prenomEmploye + " " + employe.nomEmploye;
		} else {
			return null;
		}
	}
	
	private boolean invariantId1(String id) {
		return (id.length() == 10);
	}

	private boolean invariantId2(String id, String deuxPremLettres) {
		return (id.substring(0, 2).equalsIgnoreCase(deuxPremLettres));
	}

	private boolean invariantId3(String id) {
		String anneeEntree = id.substring(2, 6);
		return anneeEntree.equals(this.getYear());
	}

	private boolean invariantId4(String id) {
		return (id.substring(6, 9).matches("[A-Za-z]+"));
	}

	private boolean invariantMdp1(String mdp) {
		return ((mdp.length() >= 8) && (mdp.length() <= 15));
	}

	private boolean invariantMdp2(String mdp) {			
		return Pattern.compile("\\d.*\\d").matcher(mdp).find();		
	}

	private boolean invariantMdp3(String mdp) {		
		return (mdp.matches("[A-Z].*"));
	}

	private boolean invariantMdp4(String mdp) {			
		return Pattern.compile("[a-zA-Z].*[a-zA-Z]").matcher(mdp).find();
	}

	private boolean invariantMdp5(String mdp) {
		return Pattern.compile("[&.?.%.$./.*.-]").matcher(mdp).find();
	}
}
