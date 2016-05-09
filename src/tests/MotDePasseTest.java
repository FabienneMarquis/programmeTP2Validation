package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.employe.Employe;

public class MotDePasseTest {
	
	// Valeurs valides pour tester les identifiants d'un Employe
	private final static String IDENTIFIANT = "EM2016asod";
	private final static String NOM = "Savoie";
	private final static String PRENOM = "Marie";
	private final static String NO_TELEPHONE = "2222";
	
	Employe emp;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void MDPtestValide() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"F16poux!", NO_TELEPHONE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erreur de création d'un employé valide.");
		}
	}
	@Test
	public void MDPtestLongueur() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"F16p!", NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Mot de passe invalide.", e.getMessage());
		}
	}
	@Test
	public void MDPtest2chiffres() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"Faapoux!", NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Mot de passe invalide.", e.getMessage());
		}
	}
	@Test
	public void MDPtestMajuscule() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"f16poux!", NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Mot de passe invalide.", e.getMessage());
		}
	}
	@Test
	public void MDPtest2lettress() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"F169874!", NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Mot de passe invalide.", e.getMessage());
		}
	}
	@Test
	public void MDPtestCarSpecial() {
		try {
			emp = new Employe(IDENTIFIANT, NOM, PRENOM,"F16poux7", NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Mot de passe invalide.", e.getMessage());
		}
	}

}
