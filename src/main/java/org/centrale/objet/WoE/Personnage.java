

/**
 *
 * @author 33651
 */
package org.centrale.objet.WoE;

    /**
     * 
     * Classe rassemblant tous les types de personnage
     */
public abstract class Personnage extends Creature {
    

    private int distAttMax;
    private String nom;

/**Constructeur de personnages à partir de plusieurs caractéristiques
 * 
 * @param ptVie nombre de point de vie
 * @param degAtt nombre de dégat d'attaque
 * @param ptPar point de parade
 * @param pageAtt capacité à attaquer
 * @param pagePar capacité à parer
 * @param distAttMax distance maximale d'attaque
 * @param nom nom du personnage
 * @param pos position du personnage
 * 
 */
    public Personnage(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos) {
        super(ptVie,degAtt,ptPar,pageAtt, pagePar,pos);
        this.distAttMax = distAttMax;
        this.nom = nom;  
    }

/** Constructeur de personnage sans paramètre d'entrée
 */
    public Personnage(){
        this.distAttMax = 2;
        this.nom = "Hercule";
       
    }
    
    /**
     *Constructeur de personnage à partir d'un objet
     * @param pers personnage
     */
    public Personnage(Personnage pers){
        super((Creature) pers);
        this.distAttMax = pers.getDistAttMax();
        this.nom = pers.getNom();
        
    }
    

    /**
     * Récupère le nom du personnage
     * @return  nom Nom du personnage
     */
 public String getNom() {
        return nom;
    }
 /**
  * Récupère la portée du personnage
  * @return  distAttMax Distance Maximale d'attaque
  */
 public int getDistAttMax() {
        return distAttMax;
    }

    /**
     *Modifie le nom du personnage
     * @param nom Nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     *Modifie la portée du personnage
     * @param distAttMax la distance maximale à laquelle on peut attaquer 
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    
   
    
    
    
}
