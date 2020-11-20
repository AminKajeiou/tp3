package zoo.utils;

import java.util.Comparator;

import zoo.Secteur;

public class SecteurComparator implements Comparator<Secteur> {

	@Override
	public int compare(Secteur o1, Secteur o2) {
		return o1.getNombreAnimaux() - o2.getNombreAnimaux();
	}

}
