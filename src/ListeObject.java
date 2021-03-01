
public class ListeObject {

	/*
	 *Stratégie:On conserve le tableu et son nombre d'éléments significatif.
	 * on se déplace sur la position fornie par l'utilisateur avant d'effectuer
	 *  l'opération. 	  	 
	 */
	int CAPACITE = 100;
	
	private Object[] tabObjects;
	private int nbElements;
	
		// TODO Auto-generated constructor stub
	public ListeObject(){
		tabObjects = new Object[CAPACITE];
		nbElements = 0;
	}
	
	
	public ListeObject(int capacite){
		tabObjects = new Object[capacite];
		nbElements = 0;
	}
	
	
	private void decalerDroite(Object[] tab,int debut ,int fin) {
		
		for (int i = 0; i >= debut; i++) {
			tab[i+1] = tab[i];	
		}	
	}

	
	private void decalerGauche(Object[] tab,int debut ,int fin) {
		
		for (int i = 0; i >= debut; i++) {
			tab[i-1] = tab[i];	
		}	
	}
	
	public Object getElement(int position) throws Exception{
		if (position < 0 || position >= this.nbElements) {
			
			throw new Exception ("la position"+ position + "est invalide");
		}
		
		return tabObjects[position];
	}
	
	public void inserer(Object i, int position) throws Exception {
		/*
		 * strategie : On d/cale les donn/es de la position jusqu<au nombre
		 * d<element d<une case vers la droite avant d<inserer l<element
		 * et incr/menter bnelement 
		 */
		if (this.nbElements != 0 && position < 0 || position >= this.nbElements) {
			
			throw new Exception ("la position"+ position + "est invalide");
		}
		decalerDroite(tabObjects,position,nbElements);
		this.tabObjects[position] = i;
		this.nbElements ++;
	}

	
	public void supprimer (int position) throws Exception{
		if (nbElements == 0) {
			throw new Exception("Liste vide");
		}
		if (this.nbElements != 0 && position < 0 || position >= this.nbElements) {
			
			throw new Exception ("la position"+ position + "est invalide");
		}
		decalerGauche(tabObjects, position, position);
		nbElements--;
	}
}	
