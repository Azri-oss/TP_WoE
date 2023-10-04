


/**
 *
 * @author noesa
 */
package org.centrale.objet.WoE;

/** Classe donnant la position en 2D
 * 
 */
public class Point2D {

    private int x;
    private int y;

    /**
     * Constucteur principal
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
  

    /**
     *Constructeur par défaut
     */
    public Point2D() {
        this.x = 1;
        this.y = 4;
    }
    
/**
 * Methode toString : obtenir des coordonnées en deux dimensions afffichables
 * @return  coordonnées
 */
    @Override
    public String toString() {
        return "["  + x + "," + y + ']';
    }

    /**
     *Constructeur de copie
     * @param p Point2D
     */
    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    
    /**
     *Donne la position x
     * @return x Coordonée x
     */
    public int getX() {
        return x;
    }

    /**
     *Donne la position y
     * @return y Coordonnée y
     */
    public int getY() {
        return y;
    }

    //Setters
    /**
     *Modifie la coordonnée x
     * @param x Coordonnée x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *Modifie la coordonnée y
     * @param y Coordonnée y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Change les coordonnées x et y
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translate le point en deux dimensions
     *
     * @param dx Déplacement selon x
     * @param dy Déplacement selon y
     */
    public void translate(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    /**
     * Affiche le point
     */
    public void affiche() {
        System.out.println("[" + x + ";" + y + "]");
    }

    /**
     * Calcule la distance avec un autre point
     *
     * @param p Point2D
     * @return Distance
     */
    public int distance(Point2D p) {
        return (int) (java.lang.Math.sqrt(((x - p.x) * (x - p.x)) + (y - p.y) * (y - p.y)));
    }
}
    