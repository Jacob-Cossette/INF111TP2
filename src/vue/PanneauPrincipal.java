package vue;
/**
 * Panneau qui affiche l'état de la simulation.
 * 
 * Les antennes sont représentés par des cercles gris et les Cellulaire par des cercles bleus.
 * Lorsque deux cellulaires sont connectés, ils changent de couleur et prenne une couleur aléatoire
 * commune.
 * 
 * Le panneau se met à jour suivant une notification d'un modèle Observable 
 * pour lequel il est abonné.
 * 
 * @author Fred Simard
 * @version Hiver 2021
 * @révision Pierre Bélisle (compléter commentaires et et mise en forme 
 *           au format utf8).
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import modele.physique.Point2D;
import modele.reseau.Antenne;
import modele.reseau.Cellulaire;
import modele.reseau.GestionnaireReseau;
import observer.MonObserver;

public class PanneauPrincipal extends JPanel implements MonObserver{

	// Rayon pour l'affichage.
	private static int RAYON_ANTENNE = 20;
	private static int RAYON_INDIVIDU = 10;
	
	// Retient la taille de l'écran et sert à l'effacer.
	Dimension tailleEcran;
	
	//  On doit communiquer avec le réseau.
	GestionnaireReseau reseau = GestionnaireReseau.getInstance();
	
	/**
	 * Constructeur qui dimensionne le panneau plein écran.
	 * 
	 * @param taille de la fenêtre
	 */
	public PanneauPrincipal() {
		
		this.tailleEcran = getPreferredSize();
        
		rafraichirDessin();
		
		reseau.attacherObserver(this);
		
	}
	
	/**
	 * appelé pour mettre à jour le dessin à l'écran.
	 */
	private void rafraichirDessin(){
		
		validate();
		repaint();		

	}
	
	/**
	 * méthode hérité qui dessine à la fenêtre
	 */
	public void paintComponent(Graphics g) {

		// convertie en engin graphique 2D
		Graphics2D g2 = (Graphics2D) g;
		
		// appel au paint de base
		super.paintComponent(g);
		
		// efface l'écran
		g2.clearRect(0, 0, tailleEcran.width, tailleEcran.height);
		
		dessineCellulaires(g2);
		dessineAntennes(g2);

        //gets rid of the copy
        g2.dispose();
	}
	
	/**
	 * Dessine les cellulaires à l'écran.
	 * 
	 * @param g référence à l'engin graphique.
	 */
	public void dessineCellulaires(Graphics2D g) {
		
		ArrayList<Cellulaire> cellulaires = reseau.getCellulaires();
		
		// dessine tous les cellulaires
		for(Cellulaire cellulaire : cellulaires) {
			
			Position position = cellulaire.getPosition();
			
			// si le cellulaire est connect�, choisi sa couleur � partir du num�ro de connexion
			if(cellulaire.estConnecte()) {
				
				double test = cellulaire.getNumeroConnexion()/
						                              (double)Integer.MAX_VALUE;
				
				float testFloat = (float) test;
				
				test *= 3;
				
				if(test<=1) {
					
					g.setColor(new Color(testFloat,0.0f,0.0f));
					
				}else if(test<=2) {
					
					testFloat -= 1.0;
					g.setColor(new Color(0.0f,testFloat,0.0f));
					
				}else {
					testFloat -= 2.0;
					g.setColor(new Color(testFloat,0.0f,testFloat));
				}
			}else {
				g.setColor(Color.BLUE);
			}
			
			dessinerOvale(g, position,RAYON_INDIVIDU);
			
		}
		
	}

	
	/*
	 * Fonctionprivée pour éviter la répétition de code lors
	 * pous dessiner les cellulaires et les antennes.
	 */
	private void dessinerOvale(Graphics2D g, Point2D position, int rayon) {
		
		int diametre = 2 * rayon;
		
		g.fillOval((int)position.getX()-rayon, 
				   (int)position.getY()-rayon, 
				   diametre, 
				   diametre);	
		
	}

	/**
	 * Dessine les antennes à l'écran.
	 * 
	 * @param g référence à l'engin graphique.
	 */
	public void dessineAntennes(Graphics2D g) {

		ArrayList<Antenne> antennes = reseau.getAntennes();
		
		// dessine toutes les antennes selon les param�tres
		for(Antenne antenne : antennes) {

			Position position = antenne.getPosition();
			g.setColor(Color.DARK_GRAY);
			
			dessinerOvale(g, position, RAYON_ANTENNE);	
		}
	}

	/**
	 * Est appelé lors d'une modification au modèle.
	 * 
	 * @Override
	 */
	public void avertir() {
		rafraichirDessin();		
	}
	
}
