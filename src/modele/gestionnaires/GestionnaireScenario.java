package modele.gestionnaires;

/**
 * Le gestionnaire de scénario est un module utilitaire gérant:
 * la création de numéro de téléphone et des messages
 *  
 *  Les fonctionnalités sont offertes pour les numéros normaux et les numéros
 *  de criminels.
 *  
 *  @author Fred Simard | ETS
 *  @revision hiver 2021
 *  @adaptation : Le code a été quelque peu modifié pour différer de 
 *                la version de Fred
 *  @révision Pierre Bélisle (compléter commentaires et et mise en forme 
 *            au format utf8). */

import java.io.File;  // Ne pas confondre avec une file, c'est d'un 
                      // fichier dont il est question.

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// à implémenter.
import modele.physique.Carte;
import modele.reseau.Antenne;
import modele.reseau.GestionnaireReseau;

public class GestionnaireScenario {

	// Fichier contenant le texte qui sert à la simulation.
	public static final String FICHIER_CONVERSATION = 
			                                     "ressources/conversations.txt";
	
	// Pour préfixer les numéros de téléphone.  Le 3 et le 4
	// qui sert à générer la séquence (XXX-XXXX) ne sont pas en constantes.
	// Si ça changeait, il faut modifier obtenirNouveauNumeroAlea().
	public static final String PREFIX = "514-";
	
	// Permet d'obtenir des chiffres au hasard de 0 à BASE.
	public static final int BASE = 10;
	
	// On obtient la seule instance du gestionnaire de réseau.
	GestionnaireReseau reseau = GestionnaireReseau.getInstance();
	
	// Vous devez déclarer l'attribut "file". 
	// C'est la déclaration de la file de chaînes de caractères.
	

    // Vous devez déclarer l'attribut numeroCriminel
	// C'est l'instanciation de la collection de criminels.
	
	 // Vous devez déclarer l'attribut numeroCriminel 
	// C'est l'instanciation de la collection des numéros standards.
	
	// Permet d'obtenir des nombres aléatoires.
    Random rand = new Random();

    // Instanciation du singleton pour gérer les scénarios.
	private static GestionnaireScenario instance = new GestionnaireScenario();

	
	/**
	 * Méthode utilitaire pour générer une chaîne de caractères aléatoire
	 * 
	 * @return String aléatoire
	 * @ref: https://www.baeldung.com/java-random-string
	 */
	private static String generatingRandomAlphabeticString() {
		
		/*
		 * Stratégie : L'utilisation des classes IntStream et StringBuilder
		 * facilite la tâche.  On donne des limites avec une série d'entiers 
		 * provenant de rand.ints.  Cela retourne une IntStream qu'on configure
		 * pour obtenir unStringBuilder et finalement un String.
		 */
		
	    int leftLimit = 97; // letter 'a' en ASCII
	    int rightLimit = 122; // letter 'z' en ASCII
	    
	    // Un nombre au hasard.
	    int targetStringLength = instance.rand.nextInt((int)Math.pow(BASE,2))+1;
	    
	    String generatedString = instance.rand.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, 
	    		   StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
	/**
	 * Méthode retournant un message. Le message renvoyé dépend  
	 * si le numéro est standard ou criminel. Si criminel, le message est tiré
	 * du scénario. Si standard, chaine de caractère aléatoire
	 * 
	 * @param numero utilisé pour envoyer le message
	 * @return le message à envoyer
	 */
	public static String obtenirMessage(String numero) {
		
		if(instance.numeroCriminel.contains(numero)) {
			try {
				return instance.file.defiler();
			}
			catch(Exception exception){
				instance.reseau.finDeSimulation();
				return null;
			}
		} else {
			return generatingRandomAlphabeticString();
		}
	}
	

	/**
	 * Méthode qui retourne un numéro choisi aléatoirement parmi la
	 * liste des numéros criminels, à l'exception de celui reçu en
	 * paramètre
	 * 
	 * @param exclus le numéro à exclure des possibilités
	 * @return le numéro appartenant aux numéros criminels
	 */
	public static String obtenirNumeroCriminelAlea(String exclus) {
		
		return obtenirNumeroAlea(instance.numeroCriminel, exclus);
	}

	/**
	 * Méthode qui retourne un numéro choisi aléatoirement parmi la
	 * liste des numéros standard, à l'exception de celui reçu en
	 * paramètre
	 * 
	 * @param exclus le numéro a exclure des possibilittés
	 * @return le numéro appartenant aux numéros standards
	 */
	public static String obtenirNumeroStandardAlea(String exclus) {

		return obtenirNumeroAlea(instance.numeroStandard, exclus);
	}

	
	/**
	 * Évite la répétition du code de la recherche pour un numéro de criminel
	 * ou un numéro standard aléatoire.
	 * 
	 * @param collectionNumero
	 * @param exclus
	 * @return
	 */
	private static String obtenirNumeroAlea(ArrayList collectionNumero, 
			                                String exclus) {
		

		/*
		 * Stratégie : On obtient un numéro au hasard et on obtient le numéro de
		 * criminel à cet indice qui ne doit être celui exclus.
		 */
		
		int index = instance.rand.nextInt(collectionNumero.size());
		
		String numero = (String) collectionNumero.get(index);
		
		while(numero.equals(exclus)){
			
			index = instance.rand.nextInt(collectionNumero.size());
			numero = (String) collectionNumero.get(index);
		}
		return numero;		
	}
	  
	/**
	 * Méthode qui retourne un numéro de téléphone aléatoire
	 * après l'avoir ajouté à la liste des numéros criminels
	 * 
	 * @return le numéro sous forme the String
	 */
	public static String obtenirNouveauNumeroCriminel() {
		String numero = obtenirNouveauNumeroAlea();
		instance.numeroCriminel.add(numero);
		return numero;
	}

	/**
	 * Méthode qui retourne un numéro de téléphone aléatoire
	 * après l'avoir ajouté à la liste des numéros standards
	 * 
	 * @return le numéro sous forme the String
	 */
	public static String obtenirNouveauNumeroStandard() {
		String numero = obtenirNouveauNumeroAlea();
		instance.numeroStandard.add(numero);
		return numero;
	}

	/**
	 * Méthode qui construit un numéro de téléphone aléatoire
	 * avec un préfix constant, tel que PPP-XXX-YYYY
	 * @return le numéro sous forme the String
	 */
	private static String obtenirNouveauNumeroAlea() {
		
		/*
		 * Stratégie : On obtient les 3 premiers chiffres concaténés
		 * avec les 4 derniers.
		 * 
		 */
		String numero = PREFIX;
		
		numero += obtenirChiffre(3);
		
		
		numero += "-";
		
		numero += obtenirChiffre(4);
		
		
		return numero;
		
	}

	/** 
	 * Évite la répétition de code pour obtenir une suite de chiffres
	 * au hasard retournés dans un objet de la classe String.
	 * 
	 * @param nbChiffres Le nombre de chiffres voulu.
	 * 
	 * @return Une chaîne contenant le nombre de chiffre voulus.
	 */
	private static String obtenirChiffre(int nbChiffres) {
		
		String numero = "";
		
		for(int i=0;i<nbChiffres;i++) {
			
			numero += instance.rand.nextInt(BASE);
		}
		
		return numero;
	}
}
