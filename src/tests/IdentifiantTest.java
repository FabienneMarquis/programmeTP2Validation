package tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.ObjetIdentifie;
import model.employe.Employe;

public class IdentifiantTest {
	
	// Valeurs valides pour tester les identifiants d'un Employe
	private final static String NOM = "Savoie";
	private final static String PRENOM = "Marie";
	private final static String MOT_DE_PASSE = "C123WW4545&*88";
	private final static String NO_TELEPHONE = "2222";
	
	ObjetIdentifie obj;

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
	public void testsetIdString() {
		try {
			obj = new Employe("EM2016asod", NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			fail("Un objet ayant des informations valides ne peux être créé.");
		}
	}
	
	@Test
	public void testsetIdLongueur() {
		try {
			obj = new Employe("EM2016as", NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("L'identifiant doit avoir dix caractères.", e.getMessage());
		}
	}
	
	@Test
	public void testsetIdAnnee() {
		try {
			obj = new Employe("EM1999asod", NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Identifiant EM1999asod pour class Employe en 2016 est non valide."
					+ "\nDevrait ressembler à EM2016xxxx.", e.getMessage());
		}
	}
	
	@Test
	public void testsetIdPrefixClasse() {
		try {
			obj = new Employe("VA2016aseq", NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			System.out.println(e.getMessage().equals("Identifiant VA2016aseq pour class Employe en 2016 est non valide."
					+ "\nDevrait ressembler à EM2016xxxx."));
			Assert.assertEquals("Identifiant VA2016aseq pour class Employe en 2016 est non valide."
					+ "\nDevrait ressembler à EM2016xxxx.", e.getMessage());
		}
	}
	
	@Test
	public void testsetId4DerniersAlpha() {
		try {
			obj = new Employe("EM20165a47", NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			Assert.assertEquals("Identifiant EM20165a47 pour class Employe en 2016 est non valide."
					+ "\nDevrait ressembler à EM2016xxxx.", e.getMessage());
		}
	}

	@Test
	public void testGenerateId() {
		try {
			obj = new Employe(null, NOM, PRENOM, MOT_DE_PASSE, NO_TELEPHONE);
		} catch (Exception e) {
			fail("Erreur de générateur de ID.");
		}
	}
}
