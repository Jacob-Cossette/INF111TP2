package programme;
/**
 * MOdule =principal qui contient le programme qui démarre l'application 
 * graphique et le gestionnaire de réseau.
 * 
 * @author Fred Simard
 * @version H2021
 * @révision et commentaires Pierre Bélisle
 */
import modele.reseau.GestionnaireReseau;
import vue.CadrePrincipal;

public class ProgrammePrincipal {

	public static void main(String[] args){

		/*
		 * Stratégie : Il y a deux processus qui fonctionnent en parallèle.
		 * Un pour la gestion du réseau et l'affichage dans la console et 
		 * l'autre pour voir se déplacer les cellulaires et voir les
		 * connexions.
		 * 
		 * Rien d'autres à implémenter ici.
		 * 
		 */
    	Thread t2 = new Thread(GestionnaireReseau.getInstance());
    	t2.start();
		
    	Thread t = new Thread(new CadrePrincipal());
    	t.start();
    
	}
}
