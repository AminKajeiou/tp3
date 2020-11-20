package zoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import zoo.animal.Animal;
import zoo.animal.TypeAnimal;
import zoo.exception.AnimalDansMauvaisSecteurException;

public class Secteur implements Comparable<Secteur>,  Serializable{

	private TypeAnimal typeAnimauxDansSecteur;
	private ArrayList<Animal> animauxDansSecteur;
	
	public Secteur(TypeAnimal type) {
		typeAnimauxDansSecteur = type;
		animauxDansSecteur = new ArrayList<Animal>();
	}
	
	public void ajouterAnimal(Animal add) throws AnimalDansMauvaisSecteurException {
		if(add.getTypeAnimal() != typeAnimauxDansSecteur) {
			throw new AnimalDansMauvaisSecteurException();
		}
		animauxDansSecteur.add(add);
	}

	public TypeAnimal obtenirType() {
		return typeAnimauxDansSecteur;
	}

	public int getNombreAnimaux() {
		return animauxDansSecteur.size();
	}

	@Override
	public int compareTo(Secteur o) {
		return getNombreAnimaux() - o.getNombreAnimaux();
	}

	
}
