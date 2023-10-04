
package org.centrale.objet.WoE;

/**
 *Classe Objet
 * @author 33651
 */

public class Objet {
    private int nombre;
    private String nom;
    private Point2D pos;

    /**
     *Constructeur principal
     * @param nombre Nombre d'objets
     * @param nom Nom de l'objet
     * @param pos Position de l'objet
     */
    public Objet(int nombre, String nom, Point2D pos) {
        this.nombre = nombre;
        this.nom = nom;
        this.pos= pos;
    }

    /**
     *Constructeur par défaut
     */
    public Objet() {
        this.nombre = 1;
        this.nom = "Objet1";
        Point2D pdep=new Point2D(2,4);
        this.pos = pdep;
    }
    
    /**
     *Constructeur de copie
     * @param o Objet
     */
    public Objet(Objet o){
        this.nom=o.getNom();
        this.nombre=o.getNombre();
        this.pos = new Point2D(o.getPos());
    }

    /**
     *Récupère la quantité d'un objet
     * @return nombre Nombre d'objets
     */
    public int getNombre() {
        return nombre;
    }

    /**
     *Récupère le nom d'un objet
     * @return nom Nom de l'objet
     */
    public String getNom() {
        return nom;
    }

    /**
     *Récupère la position d'un objet
     * @return pos position
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     *Modifie la quantité d'un objet
     * @param nombre Nombre d'objet
     */
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    /**
     *Modifie le nom d'un objet
     * @param nom Nom de l'objet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     *Modifie la position d'un objet
     * @param pos position
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
}
