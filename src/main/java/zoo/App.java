package zoo;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import zoo.animal.Animal;
import zoo.animal.Chat;
import zoo.animal.Chien;
import zoo.animal.TypeAnimal;
import zoo.exception.AnimalDansMauvaisSecteurException;
import zoo.exception.LimiteVisiteurException;

public class App {

  private static final Logger logger = LogManager.getLogger(App.class);
  


  public static void main(String[] args) throws AnimalDansMauvaisSecteurException, IOException {
	  logger.trace("Debut de l'application");
	  logger.debug("Debut de l'application");
	  logger.info("Debut de l'application");
	  logger.warn("Debut de l'application");
	  logger.error("Debut de l'application");
	  logger.fatal("Debut de l'application");
	  
	interfaceUtilisateur();
	  
      Zoo zoo = new Zoo();
      
		  zoo.ajouterSecteur(TypeAnimal.CHAT);
		  zoo.ajouterSecteur(TypeAnimal.CHIEN);
		  zoo.ajouterSecteur(TypeAnimal.CHAT);
	  	zoo.ajouterSecteur(TypeAnimal.CHIEN);
      zoo.ajouterSecteur(TypeAnimal.CHIEN);
      
      zoo.nouvelAnimal(new Chat("Felix"));
      zoo.nouvelAnimal(new Chat("Chaton"));
      zoo.nouvelAnimal(new Chat("Malot"));
      zoo.nouvelAnimal(new Chat("Griffe"));
      zoo.nouvelAnimal(new Chat("LeChat"));
      zoo.nouvelAnimal(new Chat("Truc"));
      
      
      
      System.out.println(zoo.nombreAnimaux());
      
      logger.error("Une erreur !!!!!");
	}
  
  private static void interfaceUtilisateur() throws IOException {
	  Zoo zoo = new Zoo();
	  zoo.setName("SuperZoo");
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  
	  boolean flag = true;
	  
	  while(flag) {
		  menu(zoo);
		  String s = br.readLine();
		  switch(s) {
	        case "1": 
	          System.out.println("Quel est le nom du nouveau zoo ?");
	          String name = br.readLine();
	          zoo = new Zoo();
	          zoo.setName(name);
	          System.out.println("Zoo " + name + " cr�� !");
		    break;
	  		case "2": 
	  			 System.out.println("Quel type d'animal ?");
	  			  String typeS = br.readLine();
	  			  try {
		  			  TypeAnimal type = TypeAnimal.valueOf(typeS);
		  			  zoo.ajouterSecteur(type);
	  			  }
	  			  catch (Exception e) {
		 	  			 System.out.println(typeS + "n'est pas un type reconnu.");
	  			  }
	  		  break;
	  		case "3": 
	  			 System.out.println("Quel type d'animal ?");
	  			 String typeA = br.readLine();
	  			 try {
		  			  TypeAnimal type = TypeAnimal.valueOf(typeA);
		  			  
		  			  System.out.println("Quel est le nom de l'animal ?");
			          String nameA = br.readLine();
		  			  Animal a;
		  			  if(type == TypeAnimal.CHAT) {
		  				  a = new Chat(nameA);
		  			  }else if(type == TypeAnimal.CHIEN) {
			  				  a = new Chien(nameA);
			  			 }
	  			  }
	  			  catch (Exception e) {
		 	  			 System.out.println(typeA + "n'est pas un type reconnu.");
	  			  }
	  			 break;
	  		case "4": 
				try {
					zoo.nouveauVisiteur();
		  			System.out.println("Ajout d'un visiteur.");
				} catch (LimiteVisiteurException e) {
		  			System.out.println("Impossible : zoo plein !");
				}
	  			 break;
	  		case "5": 
	  			serializeZoo(zoo);
	  			 System.out.println("Zoo sauvergard� !");
	  			 break;
	  		case "6": 
	  			deSerializeZoo(zoo);
	  			 System.out.println("Zoo charg� !");
	  			 break;
	  		case "0":
	  			 System.out.println("Au revoir.");
	  			 flag = false;
	  			 break;
	  			
	  			 
      }
		 
	  }
	 
  }
  
  private static void menu(Zoo zoo) {
	  System.out.println("" + zoo.getName() + " : Que souhaitez vous faire ?");
	  System.out.println("1 : Creer un nouveau zoo");
	  System.out.println("2 : Ajouter un nouvel secteur");
	  System.out.println("3 : Ajouter un nouvel animal");
	  System.out.println("4 : Ajouter un visiteur");
	  System.out.println("5 : Sauvegarder mon zoo");
	  System.out.println("6 : Charger un zoo");
	  System.out.println("0 : Pour quitter");
	  System.out.println("Votre choix");
  }
  
  private static void serializeZoo(Zoo zoo) {
	  XMLEncoder encoder = null;
	  
	  try {
		/*encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("zoo.xml")));
		encoder.writeObject(zoo);
		encoder.flush();*/
		  XStream xstream = new XStream();
		  xstream.toXML(zoo, new FileOutputStream(new File("zoo.xml")));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  finally {
		  if(encoder != null)
			  encoder.close();
	  }
  }
  
  private static void deSerializeZoo(Zoo zoo) {
	  FileInputStream file;
	  
	  try {
		  XStream xstream = new XStream();
		  file = new FileInputStream("zoo.xml");
		 zoo = (Zoo) xstream.fromXML(file);
		  file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
  }
}
