package modele.reseau;

/**
 * Le gestionnaire réseau est responsable de gérer les connexions cellulaires 
 * et de relayer  les appels, messages et fin d'appel.
 * 
 * Dans le cadre de la simulation, c'est également le gestionnaire réseau qui 
 * instancie Antennes et Cellulaire et qui gère l'exécution par tour 
 * des cellulaires.
 * 
 * Il ne peut y avoir qu'un gestionnaire réseau dans le projet (singleton).
 * 
 * @author Fred Simard
 * @version Hiver 2021
 * @révision et commentaires Pierre Bélisle
 */


import observer.MonObservable;  


 // Un objet de cette classe est observable.
public class GestionnaireReseau extends MonObservable implements Runnable {

	// Mis à faut s'il ne reste plus de messages à envoyer.
	private boolean mondeEnVie = true;
	
	// On instancie un objet de la classe GestionnaireReseau en privé.
	private static GestionnaireReseau instance = new GestionnaireReseau();

	/**
	 * Méthode permettant d'obtenir la référence sur le gestionnaire réseau.
	 * 
	 * @return La référence du réseau 
	 */
	public static GestionnaireReseau getInstance() {
		return instance;
	}
	
	
	/**
	 * Permet de mettre fin à la simulation.
	 * 
	 */
	public void finDeSimulation() {
		
		this.mondeEnVie = false;
	}


	/**
	 * S'exécute en continue pour simuler le système.
	 */
	@Override
	public void run() {
		
		/*
		creeAntennes();
		creeCellulaires();
		this.avertirLesObservers();

		while(this.mondeEnVie) {	
			
			for(Cellulaire cell : cellulaires) {
				cell.effectuerTour();
			}
			
			this.avertirLesObservers();
			
			
			try {
				Thread.sleep(PERIODE_SIMULATION_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}
	
}
