package org.centrale.objet.WoE;

import java.util.*;

/**
 *
 * @author 33651
 */
/**
 *
 * Classe du personnage : Guerrier
 */
public class Guerrier extends Personnage implements Combattant {

    private Epee arme;

    /**
     * Constructeur principal
     *
     * @param arme Epée du guerrier
     * @param ptVie Points de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param distAttMax Distance maximale d'attaque
     * @param nom Nom du guerrier
     * @param pos Position du guerrier
     */
    public Guerrier(Epee arme, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos,ArrayList<Utilisable> inventaire,ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, nom, pos,inventaire, effets);
        this.arme = arme;
        this.setLettre("G");
    }

    /**
     * Constructeur par défaut
     */
    public Guerrier() {
        super();
        this.arme = new Epee();
        this.setLettre("G");
    }

    /**
     * Constructeur de copie
     *
     * @param arme Epee
     * @param pers Personnage
     */
    public Guerrier(Epee arme, Personnage pers) {
        super(pers);
        this.arme = arme;
        this.setLettre("G");
    }

    /**
     * Récupère les caractéristiques de l'arme
     *
     * @return arme Epee
     */
    public Epee getArme() {
        return arme;
    }

    /**
     * Modifie les caractéristiques de l'arme
     *
     * @param arme Epee
     */
    public void setArme(Epee arme) {
        this.arme = arme;
    }

    /**
     * Methode qui modélise le système de combat corps à corps
     *
     * @param c Creature
     */
    @Override
    public void combattre(Creature c) {
        boolean jce = (c.isEstJoueur() == true || this.isEstJoueur() == true);
        if (jce) {
            System.out.println(getNom() + "tente une attaque sur" + c.toString());
        }
        Random generateur = new Random();
        int entierAleatoire = generateur.nextInt(100);
        if (entierAleatoire > getPageAtt()) {
            if (jce) {
                System.out.println(getNom() + "loupe son attaque");
            }
        } else {
            if (jce) {
                System.out.println(getNom() + "à réussi son attaque");
                System.out.println(c.toString() + "tente de parer");
            }
            entierAleatoire = generateur.nextInt(100);
            if (entierAleatoire > getPagePar()) {
                if (jce) {
                    System.out.println(c.toString() + "loupe sa parade et perd" + getDegAtt() + "PV");
                }
                c.setPtVie(c.getPtVie() - getDegAtt(), this);
            } else {
                if (jce) {
                    System.out.println(c.toString() + "réussit sa parade et perd" + (getDegAtt() - c.getPtPar()) + "PV");
                }
                c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar(), this);
            }
        }
    }


/**
 *
 * @return La chaine de caractère : Guerrier
 */
@Override
public String toString() {
        return (getNom()+" le Guerrier");
    }

}
