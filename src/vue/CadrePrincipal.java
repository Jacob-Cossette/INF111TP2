package vue;
/**
 * Cadre simple, plein écran qui contient un panneau dessin.
 * 
 * Intègre la logique de confirmation de fermeture de fenêtre.
 * 
 * @author Fred Simard
 * @version Hiver 2021
 * @révision Pierre Bélisle (compléter commentaires et et mise en forme 
 *           au format utf8).
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadrePrincipal extends JFrame implements Runnable{

	
	@Override
	public void run() {
		
		initCadre();


		// cette ligne remplace le JPanel existant dans le JFrame
        // par une instance de notre classe définie.
		this.setContentPane(new PanneauPrincipal());

    	setVisible(true);
	}
	
	
	/*
	 * Initialise la fenêtre et ajoute un écouteur du X pour 
	 * confirmer la fermeture.
	 */
	private void initCadre() {

    	// maximize la fenêtre
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
    	
    	
    	// Ajoute une gestion du EXIT par confirmation pop-up.
		this.addWindowListener(new WindowAdapter() {
		      
			// Gestionnaire d'événement pour la fermeture.
			public void windowClosing(WindowEvent we) {
				obtenirConfirmation();
		      }
		});
		
	}


	/*
	 * Sollicite l'utilisateur pour confirmer sa sortie de l'application.
	 */
	private void obtenirConfirmation() {

		// Ajoute une demande de confirmation.
        int result = JOptionPane.showConfirmDialog(null,
            "Voulez-vous quitter?", "Confirmation de sortie: ",
            JOptionPane.YES_NO_OPTION);
        
        // Si la réponse est oui
        if (result == JOptionPane.YES_OPTION){
        	
        	// Ferme la fenêtre en activant la gestion de l'événement.
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if (result == JOptionPane.NO_OPTION){
        	
        	// Sinon, désactive la gestion de l'événement.
        	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
		
	}

}
