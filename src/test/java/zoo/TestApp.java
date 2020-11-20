package zoo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import zoo.animal.Chat;
import zoo.animal.Chien;
import zoo.animal.TypeAnimal;
import zoo.exception.AnimalDansMauvaisSecteurException;
import zoo.exception.LimiteVisiteurException;
import zoo.utils.SecteurComparator;

public class TestApp {

	@Test
	public void testNbVisiteurOK() {
		Zoo zoo = new Zoo();
		zoo.ajouterSecteur(TypeAnimal.CHAT);
		Throwable e = null;

		try {
			addVisiteur(zoo, 15);
		} catch (Throwable ex) {
			e = ex;
		}

		assertFalse(e instanceof LimiteVisiteurException);

	}

	@Test
	public void testNbVisiteurKO() {
		Zoo zoo = new Zoo();
		zoo.ajouterSecteur(TypeAnimal.CHAT);
		Throwable e = null;

		try {
			addVisiteur(zoo, 18);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof LimiteVisiteurException);

	}

	private void addVisiteur(Zoo zoo, int max) throws LimiteVisiteurException {
		for (int i = 0; i < max; i++) {
			zoo.nouveauVisiteur();
		}
	}

	@Test
	public void testNbAnimaux() {
		Throwable e = null;
		Zoo zoo = new Zoo();

		zoo.ajouterSecteur(TypeAnimal.CHIEN);
		try {
			zoo.nouvelAnimal(new Chien("Rex"));
		} catch (AnimalDansMauvaisSecteurException exc) {
			e = exc;
		}

		assertFalse(e instanceof AnimalDansMauvaisSecteurException);
		assertEquals(1,zoo.nombreAnimaux());

	}

	@Test
	public void testComparaisonSecteur() throws AnimalDansMauvaisSecteurException{
		Secteur secteur1 = new Secteur(TypeAnimal.CHAT);
		Secteur secteur2 = new Secteur(TypeAnimal.CHIEN);
		
		assertEquals(0,secteur1.compareTo(secteur2));

		secteur1.ajouterAnimal(new Chat("Rex"));


		assertTrue(secteur1.compareTo(secteur2)> 0);
		assertTrue(secteur2.compareTo(secteur1)< 0);


		SecteurComparator sc = new SecteurComparator();
		sc.compare(secteur1, secteur2);
	}
	
}
