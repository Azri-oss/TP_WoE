/**
 *
 * @author 33651
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;

/**
 * Classe Monstre
 *
 */
public abstract class Monstre extends Creature {

    /**
     * Constructeur principal
     *
     * @param ptVie Points de vie
     * @param degAtt Degats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param pos position du monstre
     * @param distAttMax Distance maximale d'attaque
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, int distAttMax, ArrayList<Utilisable> inventaire, ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, inventaire, effets);
    }

    /**
     * Constructeur de copie
     *
     * @param m Monstre à copier
     */
    public Monstre(Monstre m) {
        super((Creature) m);
    }

    /**
     * Constructeur par défaut
     */
    public Monstre() {
    }

}
