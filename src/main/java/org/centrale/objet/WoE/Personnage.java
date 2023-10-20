/**
 *
 * @author 33651
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Classe rassemblant tous les types de personnage
 */
public abstract class Personnage extends Creature {

    private String nom;

    /**
     * Constructeur de personnages à partir de plusieurs caractéristiques
     *
     * @param ptVie nombre de point de vie
     * @param degAtt nombre de dégat d'attaque
     * @param ptPar point de parade
     * @param pageAtt capacité à attaquer
     * @param pagePar capacité à parer
     * @param distAttMax distance maximale d'attaque
     * @param nom nom du personnage
     * @param pos position du personnage
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
    public Personnage(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos, ArrayList<Utilisable> inventaire, ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, inventaire, effets);
        this.nom = nom;
    }

    /**
     * Constructeur de personnage sans paramètre d'entrée
     */
    public Personnage() {

    }

    /**
     * Constructeur de personnage à partir d'un personnage
     *
     * @param pers personnage
     */
    public Personnage(Personnage pers) {
        super((Creature) pers);
        this.nom = pers.getNom();

    }

    /**
     * Récupère le nom du personnage
     *
     * @return nom Nom du personnage
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du personnage
     *
     * @param nom Nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void nomAlea(String[] listeNoms){
        Random g = new Random();
        int max = listeNoms.length-1;
        this.setNom(listeNoms[g.nextInt(max)]);
    }

}
