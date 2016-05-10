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
		
		this.setMDP(mdp);
		this.servicesAuth = new ArrayList<>();
		this.servicesAuth.add(RequeteReponseService.messService.id);
		this.servicesAuth.addAll(servicesAuth);
		this.noTel = noTel;

		assert validerLongueurMDP(mdp) : "Longueur du mot de passe doit entre 8 à 15 caractères";
		assert valider2chiffres(mdp) : "Le mot de passe doit contenir au moins 2 chiffres";
		assert validerMajusculeDebut(mdp) : "Le mot de passe doit commencer par une lettre majuscule";
		assert valider2lettres(mdp) : "Le mot de passe doit contenir au moins 2 lettres";
		assert validerCarSpecial(mdp) : "Le mot de passe doit contenir au moins 1 caractère spécial";
		
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
	
	private void setMDP(String mdp) throws Exception {
		if (!validerMDP(mdp)) {
			throw new Exception("Mot de passe invalide.");
		} else {
			this.mdp = mdp;
		}
	}
	
	public static boolean validerMDP(String mdp) {
		return (validerLongueurMDP(mdp) 
				&& valider2chiffres(mdp)
				&& validerMajusculeDebut(mdp)
				&& valider2lettres(mdp)
				&& validerCarSpecial(mdp)
				);
	}

	private static boolean validerLongueurMDP(String mdp) {
		return ((mdp.length() >= 8) && (mdp.length() <= 15));
	}

	private static boolean valider2chiffres(String mdp) {			
		return Pattern.compile("\\d.*\\d").matcher(mdp).find();		
	}

	private static boolean validerMajusculeDebut(String mdp) {		
		return (mdp.matches("[A-Z].*"));
	}

	private static boolean valider2lettres(String mdp) {			
		return Pattern.compile("[a-zA-Z].*[a-zA-Z]").matcher(mdp).find();
	}

	private static boolean validerCarSpecial(String mdp) {
		return Pattern.compile("\\W").matcher(mdp).find();
	}
}
