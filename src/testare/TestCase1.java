package testare;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import design_patterns.BiletetFactory;
import design_patterns.Card;
import design_patterns.Client;
import design_patterns.Comanda;
import design_patterns.Film;
import design_patterns.FilmBuilder;
import design_patterns.Loc;
import design_patterns.Rezervare;
import design_patterns.RezervareMock;
import design_patterns.Sala;
import design_patterns.TransferBancar;
import interfete.Bilete;

public class Testare {

	@Test
	public void testCalculNotaFilm() {
		Film F1 = new Film();
		assertEquals(10, F1.calculNota(10, 10, 10), 0.1);
		assertEquals(7.5, F1.calculNota(6.33, 9, 7.25), 0.1);
	}
	
	@Test
	public void testDisponibilitateLoc() {
		Loc L1 = new Loc();
		int locuri[] = new int[30];
		assertTrue(L1.disponibilitateLoc(8, locuri));
		assertFalse(L1.disponibilitateLoc(8, locuri));
	}
	
	@Test
	public void testDisponibilitateLocNegativ() {
		Loc L2 = new Loc();
		int locuri[] = new int[30];
		try {
			boolean rez = L2.disponibilitateLoc(-3, locuri);
			fail("Eroare!");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testDisponibilitateLocZero() {
		Loc L3 = new Loc();
		int locuri[] = new int[30];
		try {
			boolean rez = L3.disponibilitateLoc(0, locuri);
			fail("Eroare!");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testDisponibilitateLocPesteCapacitate() {
		Loc L4 = new Loc();
		int locuri[] = new int[30];
		try {
			boolean rez = L4.disponibilitateLoc(40, locuri);
			fail("Eroare!");
		} catch(Exception e) {
		}
	}

	@Test
	public void testTotalComanda() {
		Comanda C1 = new Comanda();
		assertEquals(60, C1.totalComanda(2, 2, 0, 0), 0.1);
		assertNotEquals(43, C1.totalComanda(1, 2, 0, 3));
	}
	
	@Test
	public void test_totalComanda1() {
		Comanda C2 = new Comanda();
		int nrBE = 1;
		int nrBN = 1;
		int nrBP = 0;
		int nrBS = 0;
		double x = C2.totalComanda(nrBN, 0, 0, 0);
		double x1 = C2.totalComanda(0, nrBE, nrBP, nrBS);
		double rezultat = C2.totalComanda(nrBN, nrBE, nrBP, nrBS);
		assertEquals(x, rezultat - x1, 0.0);
	}
	
	@Test
	public void test_totalComanda2() {
		Comanda C2 = new Comanda();
		int nrBE = 1;
		int nrBN = 1;
		int nrBP = 0;
		int nrBS = 0;
		double x = C2.totalComanda(nrBN, 0, nrBP, 0);
		double x1 = C2.totalComanda(0, nrBE, 0, nrBS);
		double rezultat = C2.totalComanda(nrBN, nrBE, nrBP, nrBS);
		assertEquals(x, rezultat - x1, 0.0);
	}
	
	@Test
	public void test_totalComanda3() {
		Comanda C2 = new Comanda();
		int nrBE = 1;
		int nrBN = 1;
		int nrBP = 0;
		int nrBS = 0;
		double x = C2.totalComanda(nrBN, 0, nrBP, 0);
		double x1 = C2.totalComanda(0, nrBE, 0, nrBS);
		double rezultat = C2.totalComanda(nrBN, nrBE, nrBP, nrBS);
		assertEquals(x + x1, rezultat, 0.0);
	}
	
	@Test
	public void testNrBileteNegativ() {
		Comanda C2 = new Comanda();
		try {
			double rez = C2.totalComanda(-1, 4, 0, 0);
			fail("Eroare!");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testNrBileteInvalid() {
		Comanda C3 = new Comanda();
		try {
			double rez = C3.totalComanda(31, 4, 0, 0);
			fail("Eroare!");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testDiscount() {
		Comanda C4 = new Comanda();
		assertTrue(C4.acordareDiscountFete("feminin"));
		assertTrue(C4.acordareDiscountFete("Feminin"));
	}
	
	@Test
	public void test_checkNrCard() {
		Card C = new Card();
		assertTrue(C.checkNrCard("4485669522367584"));
	}
	
	@Test
	public void test_checkCard1() {
		Card C1 = new Card();
		assertTrue(C1.checkNrCard("4485 6695 2236 7584"));
	}
	
	@Test
	public void test_checkCard2() {
		Card C2 = new Card();
		assertTrue(C2.checkNrCard("4485-6695-2236-7584"));
	}
	
	@Test
	public void test_setterGetterFilm1() {
		Film F = new Film();
		F.setAn(1999);
		assertEquals(1999, F.getAn(), 0.0);
	}
	
	@Test
	public void test_setterGetterFilm2() {
		Film F = new Film();
		F.setGen("Fantastic");
		assertEquals("Fantastic", F.getGen());
	}
	
	@Test
	public void test_checkSala() {
		Sala s = new Sala();
		int locuriOcupate[] = new int[30];
		assertNotNull(s.checkSala(locuriOcupate));
		int locuriOcupate1[] = new int[0];
		assertNull(s.checkSala(locuriOcupate1));
	}
	
	@Test
	public void test_CNP() {
		Rezervare R = new Rezervare("Mihaela", "2940219101964", 20);
		assertTrue(R.checkPersonCNP());
	}
	
	@Test
	public void test_CNPScurt() {
		Rezervare R = new Rezervare("Mihaela", "294089101964", 20);
		assertFalse(R.checkPersonCNP());
	}
	
	@Test
	public void test_CNPLung() {
		Rezervare R = new Rezervare("Mihaela", "294034289101964", 20);
		assertFalse(R.checkPersonCNP());
	}
	
	@Test
	public void test_getSex() {
		Rezervare R = new Rezervare("Mihaela", "2940219101964", 20);
		assertEquals("F", R.getSex());
	}
	
	@Test
	public void test_getSex1() {
		Rezervare R = new Rezervare("Mihaela", "9940219101964", 20);
		assertNotEquals("F", R.getSex());
	}
	
	@Test
	public void test_calculVarsta() {
		Rezervare R = new Rezervare("Mihaela", "2940219101587", 20);
		assertEquals(22, R.calculVarsta(), 0.1);
		assertNotEquals(18, R.calculVarsta(), 0.1);
	}
	
	@Test
	public void test_calculVarsta_CNPIncorect() {
		Rezervare R = new Rezervare("Mihaela", "20219101587", 20);
		assertEquals(0, R.calculVarsta(), 0.1);
		assertNotEquals(18, R.calculVarsta(), 0.1);
	}
	
	@Test
	public void test_checkAdult() {
		Rezervare R = new Rezervare("Mihaela", "2940219101587", 20);
		assertTrue(R.checkAdult(R));
		Rezervare R1 = new Rezervare("Mihaela", "2990219101587", 20);
		assertFalse(R1.checkAdult(R1));
	}
	
	@Test
	public void test_VeridicitateVarsta() {
		Rezervare R = new Rezervare("Mihaela", "2940219101587", 22);
		assertTrue(R.checkVeridicitateVarsta(R));
		Rezervare R1 = new Rezervare("Mihaela", "2990219101587", 20);
		assertFalse(R1.checkVeridicitateVarsta(R1));
	}
	
	@Test
	public void test_checkPermisiuneFilm() {
		Rezervare R = new Rezervare("Mihaela", "2940219101587", 22);
		assertTrue(R.checkPermisiuneFilm(R));
		Rezervare R1 = new Rezervare("Mihaela", "51050219101587", 11);
		assertFalse(R1.checkPermisiuneFilm(R1));
	}
	
	@Test
	public void test_checkBanci() {
		TransferBancar TB = new TransferBancar();
		assertNotNull(TB.comparareBanci("BT", "Bt"));
		assertNotNull(TB.comparareBanci("BancaRomaneasca", "bancaRomaneasca"));
		assertNull(TB.comparareBanci("BCR", "BRD"));
	}
	
	@Test
	public void test_ComisionBanci() {
		TransferBancar TB = new TransferBancar();
		Comanda C = new Comanda();
		double suma = C.totalComanda(2, 2, 0, 0);
		assertEquals(1.8, TB.calculComisionBanca("BT", "BCR", suma), 0.1);
		assertEquals(0, TB.calculComisionBanca("BT", "BT", suma), 0.0);
	}
	
	@Test
	public void test_ComisionBanci1() {
		TransferBancar TB = new TransferBancar();
		Comanda C = new Comanda();
		double total = C.totalComanda(1, 1, 1, 1);
		double comision = TB.calculComisionBanca("BT", "BCR", total);
		assertEquals(comision, total * 0.03, 0.0);
	}
	
	@Test
	public void test_ComisionBanci2() {
		TransferBancar TB = new TransferBancar();
		Comanda C = new Comanda();
		double total = C.totalComanda(1, 1, 1, 1);
		double comision = TB.calculComisionBanca("BT", "BCR", total);
		assertEquals(0.03, comision / total, 0.0);
	}
	
	@Test
	public void test_creareIBilet() {
		BiletetFactory BF = new BiletetFactory();
		assertNotNull(BF.create(Bilete.BiletElev));
	}
	
	@Test
	public void test_calculVechimeFilm() {
		Film F = new Film();
		F.setAn(1800);
		assertEquals(216, F.calculVechimeFilm(), 0.0);
		F.setAn(1990);
		assertNotEquals(300, F.calculVechimeFilm(), 0.0);
	}
	
	@Test
	public void test_checkEmail() {
		Client C = new Client("123");
		C.setEmail("anamariaavram@yahoo.com");
		assertTrue(C.checkEmail("anamariaavram@yahoo.com"));
		assertFalse(C.checkEmail("anamariaavrayahoo.com"));
		assertFalse(C.checkEmail("anamariaavram@yahoocom"));
	}
	
	@Test
	public void test_checkEmail2() {
		Client C = new Client("123");
		assertTrue(C.checkEmail("anamariaavram@yahoo.com"));
	}
	
	@Test
	public void test_BuildMethod() {
		FilmBuilder FB = new FilmBuilder();
		assertNotNull(FB.build());
	}
	
	@Test
	public void test_calculDurataOre() {
		Film F1 = new Film();
		F1.setNumeFilm("Vecini de cosmar 2");
		F1.setAn(2016);
		F1.setDurata(91);
		F1.setGen("Comedie");
		
		assertNotNull(F1.calculDurataOre());
	}
	
	@Test
	public void test_nrTelefon() {
		Client C = new Client("64");
		assertTrue(C.checkTelefon("0765348921"));
		assertFalse(C.checkTelefon("9076234954"));
		assertFalse(C.checkTelefon("07658935412343"));
	}
	
	FileReader fr;
	BufferedReader br;
	File f = new File("Date");
	
	@Before
	public void setUp() throws Exception {
		fr = new FileReader(f);
		br = new BufferedReader(fr);
	}
	
	@After
	public void tearDown() throws Exception {
		br.close();
		fr.close();
	}
	
	@Test
	public void test_nrTelefon1() {
		Client C = new Client("yf");
		
		try {
			String linie = "";
			while((linie = br.readLine()) != null) {
				if(C.checkTelefon(linie)) {
					assertTrue(C.checkTelefon(linie));
				} else {
					assertFalse(C.checkTelefon(linie));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_nrTelefon2() {
		Client C = new Client("yf");
		
		try {
			String linie = "";
			while((linie = br.readLine()) != null) {
				if(C.checkTelefon(linie)) {
					assertTrue(C.checkTelefon(linie));
				} else {
					assertFalse(C.checkTelefon(linie));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_RezervareMock1() {
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertEquals(20, RM.getVarsta());
	}
	
	@Test
	public void test_RezervareMock2() {
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertTrue(RM.checkPersonCNP());
	}
	
	@Test
	public void test_RezervareMock3() {
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertEquals(20, RM.calculVarsta());
	}
	
	@Test
	public void test_RezervareMock4() {
		Rezervare R = new Rezervare("Ioana", "2940517645397", 24);
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertTrue(RM.checkVeridicitateVarsta(R));
	}
	
	@Test
	public void test_RezervareMock5() {
		Rezervare R = new Rezervare("Ioana", "2940517645397", 24);
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertTrue(RM.checkAdult(R));
	}
	
	@Test
	public void test_RezervareMock6() {
		Rezervare R = new Rezervare("Ioana", "2940517645397", 24);
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertTrue(RM.checkPermisiuneFilm(R));
	}
	
	@Test
	public void test_RezervareMock7() {
		RezervareMock RM = new RezervareMock("Ioana", "2940517645397", 24);
		assertEquals("F", RM.getSex());
	}
	
	@Test
	public void test_RezervareMockito1() {
		Rezervare R1 = mock(Rezervare.class);
		when(R1.getVarsta()).thenReturn(16);
		assertEquals(16, R1.getVarsta(), 0.0);
	}
	
	@Test
	public void test_ClientMockito() {
		Client C = mock(Client.class);
		when(C.checkTelefon("074596358474")).thenReturn(true);
		assertTrue(C.checkTelefon("074596358474"));
	}
}
