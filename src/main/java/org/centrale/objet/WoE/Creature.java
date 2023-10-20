package org.centrale.objet.WoE;

import java.util.*;

/**
 *
 * @author 33651
 */
/**
 * Classe Créature
 *
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {

    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private int distAttMax;
    private String lettre;
    private boolean estJoueur;
    private ArrayList<Utilisable> inventaire;
    private ArrayList<Utilisable> effets;
    private boolean vivant;

    /**
     * Constructeur principal
     *
     * @param ptVie Points de vie
     * @param degAtt Dégâts d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param pos Position de la creature
     * @param distAttMax Distance maximale d'attaque
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, int distAttMax, ArrayList<Utilisable> inventaire, ArrayList<Utilisable> effets) {
        super(pos);
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.distAttMax = distAttMax;
        this.inventaire = inventaire;
        this.effets = effets;
        this.vivant = true;
        this.estJoueur = false;
    }

    /**
     * Constructeur par défaut
     */
    public Creature() {
        super();
        Random g = new Random();
        this.ptVie = 30 + g.nextInt(20);
        this.degAtt = 20 + g.nextInt(10);
        this.ptPar = 10 + g.nextInt(10);
        this.pageAtt = 50 + g.nextInt(35);
        this.pagePar = 20 + g.nextInt(15);
        this.distAttMax = 1;
        this.inventaire = new ArrayList<>();
        this.effets = new ArrayList<>();
        this.estJoueur = false;
        this.vivant = true;
    }

    /**
     * Constructeur de copie
     *
     * @param c Creature
     */
    public Creature(Creature c) {
        super((ElementDeJeu) c);
        this.ptVie = c.getPtVie();
        this.degAtt = c.getDegAtt();
        this.ptPar = c.getPtPar();
        this.pageAtt = c.getPageAtt();
        this.pagePar = c.getPagePar();
        this.distAttMax = c.distAttMax;
        this.inventaire = c.getInventaire();
        this.effets = c.effets;
        this.estJoueur = false;
        this.vivant = true;

    }

    /**
     * Donne l'arrayList contenant les effets appliqués sur la créature
     *
     * @return arrayList effets
     */
    public ArrayList<Utilisable> getEffets() {
        return effets;
    }

    /**
     * Donne l'arrayList contenant les objets présents dans l'inventaire de la
     * créature
     *
     * @return ArrayList inventaire
     */
    public ArrayList<Utilisable> getInventaire() {
        return inventaire;
    }

    /**
     * Donne la distance d'attaque maximale de la créature
     *
     * @return int distAttMax
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     * Renvoie la valeur des points de vie
     *
     * @return ptVie le nombre de point de vie
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * Récupère les dégats d'attaque
     *
     * @return degAtt le nombre de dégats d'attaque
     *
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * Récupère les points de parade
     *
     * @return ptPar le nombre de points de parade
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     * Indique si la créature est un joueur
     *
     * @return boolean estJoueur
     */
    public boolean isEstJoueur() {
        return estJoueur;
    }

    /**
     * Fixe si la créature est un joueur ou non
     *
     * @param estJoueur
     */
    public void setEstJoueur(boolean estJoueur) {
        this.estJoueur = estJoueur;
    }

    /**
     * Récupère le pourcentage de reussite d'une attaque
     *
     * @return pageAtt le pourcentage de reussite d'une attaque
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     * Récupère le pourcentage de reussite d'une parade
     *
     * @return pagePar le pourcentage de reusssite d'une parade
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     * Modifie l'inventaire de la créature
     *
     * @param inventaire
     */
    public void setInventaire(ArrayList<Utilisable> inventaire) {
        this.inventaire = inventaire;
    }

    /**
     * Modifie la distance maximale d'attaque de la créature
     *
     * @param distAttMax
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    /**
     * Modifie les effets s'appliquant sur la créature
     *
     * @param effets
     */
    public void setEffets(ArrayList<Utilisable> effets) {
        this.effets = effets;
    }

    /**
     * Modifie le nombre de points de vie d'une creature
     *
     * @param ptVie le nombre de point de vie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
        if (this.ptVie <= 0) {
            System.out.println(this.toString() + " est mort(e)");
            this.getPos().setX(-10);
            vivant = false;
        }
    }

    /**
     * Modifie le nombre de points de vie d'une creature et indique qui l'a tuée
     * le cas échéant
     *
     * @param ptVie
     * @param c
     */
    public void setPtVie(int ptVie, Creature c) {
        this.ptVie = ptVie;
        if (this.ptVie <= 0) {
            if (c instanceof Monstre) {
                System.out.println(this.toString() + " est mort(e), sous les coups d'un " + c.toString());
            } else {
                System.out.println(this.toString() + " est mort(e), sous les coups de " + c.toString());
            }
            c.loot();
            this.getPos().setX(-10);
            vivant = false;
        }
    }

    /**
     * Modifie les dégats d'attaque de la creature
     *
     * @param degAtt le nombre de dégats infligés
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     * Modifie les points de parade de la creature
     *
     * @param ptPar le nmbre de points de parade
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     * Modifie le pourcentage de réussite d'une attaque de la creature
     *
     * @param pageAtt le pourcentage de réussite d'une attaque
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     * Modifie le pourcentage de réussite d'une parade
     *
     * @param pagePar le pourcentage de reussite d'une parade
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     * Donne la lettre (initiale) de la créature (pour l'affichage)
     *
     * @return lettre
     */
    public String getLettre() {
        return lettre;
    }

    /**
     * Fixe la lettre (initiale) de la créature (pour l'affichage)
     *
     * @param lettre
     */
    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    /**
     * Indique si la créature est vivante
     *
     * @return boolean vivant
     */
    public boolean isVivant() {
        return vivant;
    }

    /**
     * Modifie le statut vivant de la créature
     *
     * @param vivant
     */
    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

    /**
     * Méthode indiquant si une créature se trouve dans les alentours du
     * personnage
     *
     * @param w Monde
     * @return int[] alentours : Liste où chaque case vaut 1 si une créature se
     * trouve dans la direction, 0 sinon
     */
    public int[] testPresenceAlentour(World w) {
        int[] alentours = new int[8];
        for (int k = 0; k < alentours.length; k++) {
            alentours[k] = 0;
        }
        int r = 0;
        for (int j = -1; j < 2; j++) {
            for (int i = -1; i < 2; i++) {
                if (i != 0 || j != 0) {

                    this.setPos(new Point2D(this.getPos().getX() + i, this.getPos().getY() + j));

                    if (w.testCaseOccupeePerso(this, w.creatures)) {

                        alentours[r] = 1;

                    }
                    r = r + 1;
                    this.setPos(new Point2D(this.getPos().getX() - i, this.getPos().getY() - j));
                }

            }
        }

        return alentours;
    }

    /**
     * Déplace une créature sur une case adjacente
     *
     * @param w Monde
     */
    @Override
    public void deplace(World w) {
        Random generateur = new Random();
        int[] l = testPresenceAlentour(w);
        int rs = 8;
        int compt = 0;
        /**
         * Compte combien il y a de cases disponibles*
         */
        for (int i = 0; i < 8; i++) {
            if (l[i] == 0) {
                compt = compt + 1;

            }
        }
        if (compt > 0) {
            int entierAleatoire = generateur.nextInt(compt) + 1;
            int compt2 = 0;
            for (int j = 0; j < 8; j++) {
                if (l[j] == 0) {
                    compt2 = compt2 + 1;
                    if (compt2 == entierAleatoire) {
                        rs = j;
                    }
                }
            }

            switch (rs) {
                case 0:
                    getPos().translate(-1, -1);
                    break;
                case 1:
                    getPos().translate(0, -1);
                    break;
                case 2:
                    getPos().translate(1, -1);
                    break;
                case 3:
                    getPos().translate(-1, 0);
                    break;
                case 4:
                    getPos().translate(1, 0);
                    break;
                case 5:
                    getPos().translate(-1, 1);
                    break;
                case 6:
                    getPos().translate(0, 1);
                    break;
                case 7:
                    getPos().translate(1, 1);
                    break;
                default:
                    System.out.println("Erreur");
            }
        } else {
            //System.out.println("La créature ne peut pas bouger");
        }

    }

    /**
     * Methode qui dit si la potion est consommé ou nom par la creature, dans ce
     * cas elle modifie les points de vie de la creature.
     *
     * @param ps Objet : Potion de Soin
     * @return booleen Vrai si il potion est consommée, Faux autrement
     */
    public boolean consommation(PotionSoin ps) {
        if (getPos().distance(ps.getPos()) == 0) {
            setPtVie(ps.getPtSoin() + getPtVie());
            System.out.println(toString() + "a récupéré " + ps.getPtSoin());
            return true;
        } else {
            return false;
        }

    }

    /**
     * Ajoute un objet à l'inventaire de la créature
     *
     * @param o Utilisable à ajouter à l'inventaire
     * @param w Monde
     */
    public void ajoutinventaire(Utilisable o, World w) {
        inventaire.add(o);
        w.objets.remove(o);
    }

    /**
     * Retire un objet de l'inventaire de la créature
     *
     * @param o Utilisable à retirer de l'inventaire
     */
    public void retirerinventaire(Utilisable o) {
        inventaire.remove(o);

    }

    public void loot() {
        Random g = new Random();
        int n = g.nextInt(100);
        Utilisable obj;
        if (n<33){
            obj = new PotionSoin();
        } else if (n>66){
            obj = new Epee();
        } else {
            obj = new Nourriture();
        }
        if (this instanceof Archer) {
            int nf = g.nextInt(2)+1;
            ((Archer)this).setNbFleches(((Archer)this).getNbFleches()+nf);
            if(isEstJoueur()){
               System.out.println("En fouillant son cadavre, vous obtenez " + ((Utilisable) obj).affiche()+ " et "+ nf + " flèches");
            }
        } else if (isEstJoueur()){
               System.out.println("En fouillant son cadavre, vous obtenez " + ((Utilisable) obj).affiche());

        }
        inventaire.add((Utilisable) obj);
    }

    /**
     * Met à jour l'arrayList effets en retirant les utilisables dont la durée
     * est nulle
     */
    public void majEffets() {
        for (int i = 0; i < effets.size(); i++) {
            if (effets.get(i).getDuree() == 0) {
                effets.remove(i);
            }

        }
    }
}
