package zoo.animal;

import java.io.Serializable;

/**
Classe d'animaux
 */
public abstract class Animal implements Serializable{
	protected String nomAnimal;
	
	protected TypeAnimal typeAnimal;

	public String getNomAnimal() {
		return nomAnimal;
	}

	public TypeAnimal getTypeAnimal() {
		return typeAnimal;
	}
	
}
