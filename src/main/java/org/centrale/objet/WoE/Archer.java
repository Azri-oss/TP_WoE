package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 33651
 */
/**
 * Classe du personnage de type Archer
 *
 */
public class Archer extends Personnage implements Combattant {

    /**
     * Nombre de flèches
     */
    private int nbFleches;

    /**
     * Constructeur principal
     *
     * @param nbFleches Nombre de flèches
     * @param ptVie Nombre de point de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param distAttMax Distance d'attque maximale
     * @param nom Nom de l'archer
     * @param pos Position de l'acher
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
    public Archer(int nbFleches, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos, ArrayList<Utilisable> inventaire, ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, nom, pos, inventaire, effets);
        this.nbFleches = nbFleches;
        this.setLettre("A");
    }

    /**
     * Constructeur par défaut
     */
    public Archer() {
        this.nbFleches = 8;
        this.setLettre("A");
        String[] listeNoms = {
            "Lorand",
            "Elara",
            "Thalion",
            "Faelan",
            "Niamh",
            "Sylas",
            "Aeliana",
            "Rowan",
            "Caelum",
            "Iliad",
            "Seraphina",
            "Hawthorn",
            "Elysia",
            "Brynden",
            "Aria",
            "Gaelan",
            "Lyria",
            "Draven",
            "Calista",
            "Soren",
            "Evadne",
            "Finnian",
            "Thalassa",
            "Eowyn",
            "Cyrus",
            "Valeria",
            "Riven",
            "Aurora",
            "Raelin",
            "Sylvan",
            "Isolde",
            "Kael",
            "Ysolde",
            "Thorne",
            "Lysandra",
            "Silas",
            "Maelis",
            "Vaelora",
            "Ossian",
            "Elysande",
            "Elian",
            "Elowen",
            "Caelia",
            "Lorian",
            "Elyndra",
            "Faelanor",
            "Vespera",
            "Caelith"
        };
        nomAlea(listeNoms);
        this.setDistAttMax(4);
    }

    /**
     * Constructeur de copie
     *
     * @param a Archer
     */
    public Archer(Archer a) {
        super((Personnage) a);
        this.nbFleches = a.getNbFleches();
        this.setLettre("A");
    }

    /**
     * Donne le nombre de flèches
     *
     * @return Nombre de flèches du personnage
     */
    public int getNbFleches() {
        return nbFleches;
    }

    /**
     * Modifie le nombre de flèches
     *
     * @param nbFleches Nombre de flèches
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

    /**
     * Methode combattre : active le système de combat à distance entre deux
     * créatures
     *
     * @param c Creature
     */
    @Override
    public void combattre(Creature c) {
        boolean jce = (c.isEstJoueur() == true || this.isEstJoueur() == true);
        if (getPos().distance(c.getPos()) <= 1) {
            if (jce) {
                System.out.println(getNom() + " tente une attaque sur " + c.toString());
            }
            Random generateur = new Random();
            int entierAleatoire = generateur.nextInt(100);
            if (entierAleatoire > getPageAtt()) {
                if (jce) {
                    System.out.println(getNom() + " loupe son attaque");
                }
            } else {
                if (jce) {
                    System.out.println(getNom() + "a réussi son attaque");
                    System.out.println(c.toString() + "tente de parer");
                }
                entierAleatoire = generateur.nextInt(100);
                if (entierAleatoire > getPagePar()) {
                    if (jce) {
                        System.out.println(c.toString() + " loupe sa parade et perd " + getDegAtt() + " PV");
                    }
                    c.setPtVie(c.getPtVie() - getDegAtt(), this);
                } else {
                    if (jce) {
                        System.out.println(c.toString() + " réussit sa parade et perd " + (getDegAtt() - c.getPtPar()) + " PV");
                    }
                    c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar(), this);
                }
            }
        } else if (getPos().distance(c.getPos()) <= getDistAttMax()) {
            if(nbFleches > 0){
            if (jce) {
                System.out.println(getNom() + " tente une attaque à distance sur " + c.toString());
            }
            setNbFleches(nbFleches - 1);
            if (jce) {
                System.out.println(getNom() + " a maintenant " + nbFleches + " flèches");
            }
            Random generateur = new Random();
            int entierAleatoire = generateur.nextInt(100);
            if (entierAleatoire > getPageAtt()) {
                if (jce) {
                    System.out.println(getNom() + " loupe son attaque");
                }
            } else {
                if (jce) {
                    System.out.println(getNom() + "a réussi son attaque");
                    System.out.println(c.toString() + " perd " + getDegAtt() + " PV");
                }
                c.setPtVie(c.getPtVie() - getDegAtt(), this);
            }
        }}else if(jce){
            System.out.println("Impossible ! Vous n'avez plus de flèches");
        }
    }

    /**
     * Methode toString : affiche le type Archer
     *
     * @return Chaine de caractère "Archer"
     */
    @Override
    public String toString() {
        return (getNom() + " l'Archer");
    }

}
