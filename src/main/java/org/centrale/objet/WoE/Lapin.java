


/**
 *
 * @author 33651
 */
package org.centrale.objet.WoE;


/** Classe du monstre : Lapin
 * 
 */
public class Lapin extends Monstre {

    /**
     *Constructeur principal
     * @param ptVie Nombre de points de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'attaque
     * @param pagePar Pourcentage de reussite de parade
     * @param pos Position du lapin
     */
    public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }
    
    /**
     *Constructeur par défaut
     */
    public Lapin(){
        
    }

    /**
     *Constructeur de copie
     * @param l Lapin
     */
    public Lapin(Lapin l){
        super((Monstre)l);
    }

    /**
     * 
     * @return La chaine de caractère : Lapin
     */
    @Override
    public String toString() {
        return "Lapin";
    }
    
    
}
