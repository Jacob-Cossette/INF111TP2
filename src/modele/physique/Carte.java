package modele.physique;



public class Carte {

private static int HAUTEUR_CARTE = 1080;
private static int LARGEUR_CARTE = 1920;

		private Point2D taille; // static constante 1920 1080

		public Carte(Point2D taille) {
			this.taille = taille;
		}
		
		
		public Carte() {
			Point2D taille = new Point2D(HAUTEUR_CARTE,LARGEUR_CARTE);
			this.taille = taille;
		}
		
		
		public Carte(Carte carte) {
			this.taille = carte.taille;
		}
		
		
		public Point2D getTaille() {
			return taille;
		}
		
		
		public void setTaille(Point2D taille) {
			this.taille = taille;
		}

		
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Carte other = (Carte) obj;
			if (taille == null) {
				if (other.taille != null)
					return false;
			} else if (!taille.equals(other.taille))
				return false;
			return true;
		}


		public Point2D clone(){
			return new Point2D(taille);
		}	
		
		
		public Point2D generateurPoint2DCarte() {	
			return  new Point2D().randomPoint2d(LARGEUR_CARTE, HAUTEUR_CARTE);
		}
		
		public void ajusterPositionPoint2d (Point2D position) {
			
			if (position.getX()>LARGEUR_CARTE) {
				position.setX(0);
			}
			if (position.getX()<0) {
				position.setX(LARGEUR_CARTE);
			}
			if (position.getY()>HAUTEUR_CARTE) {
				position.setY(0);
			}
			if (position.getY()<0) {
				position.setY(HAUTEUR_CARTE);
			}	
		}
}
