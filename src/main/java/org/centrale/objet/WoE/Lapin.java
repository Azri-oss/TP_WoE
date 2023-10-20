


/**
 *
 * @author 33651
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;


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
     * @param distAttMax
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
   public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, int distAttMax,ArrayList<Utilisable> inventaire,ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, inventaire,effets);
        this.setLettre("L");
    }
    
    /**
     *Constructeur par défaut
     */
    public Lapin(){
        super();
        this.setLettre("L");
    }

    /**
     *Constructeur de copie
     * @param l Lapin
     */
    public Lapin(Lapin l){
        super((Monstre)l);
        this.setLettre("L");
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
