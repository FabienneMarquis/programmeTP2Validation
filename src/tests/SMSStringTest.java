package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.service.SMSEntrant;

public class SMSStringTest {
	private final static String DEF_IDENTIFIANT = "em2016asod";
	private final static String DEF_MDP = "F16poux!";
	private final static String DEF_SERVICE = "TE2016aaaa";
	private final static String DEF_SOUS_SERVICE = "TE2016vvvv";
	private final static String DEF_NO_TEL= "418-345-2345";
	private final static String DEF_MESSAGE = "Bonjour Johanne";
	
	SMSEntrant sms;

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
	public void smsValideServTest() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					DEF_MDP,
					DEF_SERVICE,
					DEF_SOUS_SERVICE
					)
				);
		Assert.assertTrue("SMS valide.", sms.isFormatValide());
	}
	
	@Test
	public void smsValideMessagerieTest() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					DEF_MDP,
					DEF_NO_TEL,
					DEF_MESSAGE
					)
				);
		Assert.assertTrue(sms.isFormatValide());
	}
	
	@Test
	public void smsIdentifiantTest() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					"Bonjour",
					DEF_MDP,
					DEF_SERVICE,
					DEF_SOUS_SERVICE
					)
				);
		Assert.assertTrue(sms.getFormErrors().contains("Format d'identifiant non valide."));
	}
	
	@Test
	public void smsMDPValideTest() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					"F16poasdds",
					DEF_SERVICE,
					DEF_SOUS_SERVICE
					)
				);
		Assert.assertTrue(sms.getFormErrors().contains("Format de mot de passe invalide."));
	}
	
	@Test
	public void smsServServiceValide() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					DEF_MDP,
					"VerfTemp",
					DEF_SOUS_SERVICE
					)
				);
		Assert.assertNull(sms.getService());
	}
	
	@Test
	public void smsTelServiceValide() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					DEF_MDP,
					"418-34sdfa778asd5",
					DEF_MESSAGE
					)
				);
		Assert.assertNull(sms.getService());
	}
	
	@Test
	public void smsSousServiceValide() {
		sms = new SMSEntrant(
				String.format("%s %s %s %s", 
					DEF_IDENTIFIANT,
					DEF_MDP,
					DEF_SERVICE,
					"TE2016vvv"
					)
				);
		Assert.assertTrue(sms.getFormErrors().contains("Format de sous-service non valide."));
	}

}
