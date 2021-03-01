package modele.physique;
import java.util.Random;
/**
 * 
 * Représente un point dans un espace à deux dimensions
 *            
 * @author <a href="mailto:Pierre.Belisle@etsmtl.ca">Pierre Belisle</a>
 * @version octobre 2005
 */
public class Point2D
{
	/*
	 * Dans le cadre du cours inf111 : 
	 * 
	 * Sert à montrer :
	 *           -la mécanique d'appel des constructeurs lors du new
	 *           -la définition des observateurs et des mutateurs
	 *           -la définition de quelques comportements possibles
	 *            d'objets de cette classe
	 */            

          //correspond à la coordonnée (x,y) d'un point
          //dans le plan cartésien en 2 dimensions
           private double x;
           private double y;
           
          /**************************************************
           * 
           * LES CONSTRUCTEURS
           * 
           **************************************************/
          /**
           * Constructeur par défaut 
           *    construit le point (0,0)
           */
          public Point2D(){};
          
          /**
           * Constructeur par copie d'attributs
           *    construit le point (x,y)
           */
          public Point2D(double x, double y){
              this.x = x;
              this.y = y;
          }
          
          /**
           * Constructeur par copie d'objet 
           *    construit une copie de point
           */
          public Point2D(Point2D point){
              
              //on peut faire point.x et point.y puisqu'on est dans la classe
              this.x = point.x;
              this.y = point.y;
          }
                   
          
          /**************************************************
           * 
           * LES OBSERVATEURS
           * 
           **************************************************/
          /**
           * Retourne l'abscisse du point
           * @return La valeur de la coordonnée x  
           */
          public double getX(){
               return x;
           }
           
          /**
           * Retourne l'ordonnée du point
           * @return La valeur de la coordonnée y  
           */
           public double getY(){
               return y;
           }
           
          /**************************************************
           * 
           * LES MUTATEURS
           * 
           **************************************************/

           /**
            * Permet de modifier l'abscisse du point
            * @param x La nouvelle coordonnée x
            */
           public void setX(double x){
               this.x = x;
           }
           
           /**
            * Permet de modifier l'ordonnée du point
            * @param x La nouvelle coordonnée y
            */
           public void setY(double y){
               this.y = y;
           }
           
           
          /**************************************************
           * 
           * LES COMPORTEMENTS
           * 
           **************************************************/
           /**
            * Calcul la distance entre le point actuel 
            * et un autre point
            * @param point Le point à considérer
            * @return La racine carrée de la somme des différences en x et en y au carré
            */
           public double distance (Point2D point){
               return Math.sqrt(Math.pow(point.x-x,2) + Math.pow(point.y-y,2));
           }
           
           
           /**
            * Calcul la pente de la droite formée par le point actuel 
            * et un autre point
            * @param point Le point à considérer
            * @return La différence de y divisé par la différence des x
            * 
            */
           public double pente (Point2D point){
               return (point.y-y) / (point.x-x);
           }
           
           
           /**
            * Calcul le point au centre entre le point actuel et un autre point
            * @param point Le point à considérer
            * @return Les différences en x,y divisé par 2
            * 
            */
           public Point2D milieu (Point2D point){
               return new Point2D(Math.abs((x-point.x)/2),Math.abs((y-point.y)/2));
           }
           
           
           /**
            * Retourne le point actuel sous forme de la chaîne "(x,y)"
            * @return Une chaîne représentant la coordonnée (x,y) du point
            */
           public String toString(){
               return "(" + x + "," + y + ")";
           }
           
           
           /**
            * Retourne vrai si le point actuel est égal à un autre point
            * @param point Le point à considérer
            * @return this.getX() == point.getX() && this.getY() == point.getY() 
            */
           public boolean equals(Point2D point){
               return point.x == x && point.y == y;
           }
           
           /**
            * Retourne une copie du point actuel
            * @return Un nouveau point identique à this 
            */
           public Point2D copie(){
               return new Point2D(x,y);
           }
           
           public Point2D randomPoint2d(int maximumx, int maximumy){
        	   Random rx = new Random();
               Random ry = new Random();

               
        	   int x = rx.nextInt((maximumx) + 1);
        	   int y = ry.nextInt((maximumx) + 1);
        	   
               return new Point2D(x,y);
           }
           
           
}