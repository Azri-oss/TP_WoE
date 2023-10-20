package org.centrale.objet.WoE;

/**
 *
 * @author 33651
 */
/**
 *
 * Classe de l'objet : Epee
 */
public class Epee extends Objet implements Utilisable {

    private int BDegAtt;
    private String lettre = "o";
    private int duree = 4;

    /**
     * Constructeur par défaut
     */
    public Epee() {
        super();
        this.BDegAtt = 6;
        this.setNom("une épée");

    }

    /**
     * Constructeur principal
     *
     * @param BDegAtt Bonus de dégats d'attaque donnée par l'épée
     *
     * @param nombre Nombre d'épée
     * @param nom Nom de l'épée
     * @param pos Position de l'épée
     */
    public Epee(int BDegAtt, int nombre, String nom, Point2D pos) {
        super(nombre, nom, pos);
        this.BDegAtt = BDegAtt;
        this.setNom("une épée");

    }

    /**
     * Constructeur de copie
     *
     * @param e Epee
     */
    public Epee(Epee e) {
        super((Objet) e);
        this.BDegAtt = e.getBDegAtt();
        this.setNom("une épée");
    }

    /**
     * Donne le bonus d'attause et la portée d'une epée
     *
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        return "Epee{" + "BDegAtt=" + BDegAtt + '}';
    }

    /**
     * Récupère la valeur du bonus d'attaque de l'épée
     *
     * @return BDegAtt le bonus d'attaque
     */
    public int getBDegAtt() {
        return BDegAtt;
    }

    /**
     * Modifie le bonus d'attaque d'une épée
     *
     * @param BDegAtt Bonus de dégats ingligés
     */
    public void setBDegAtt(int BDegAtt) {
        this.BDegAtt = BDegAtt;
    }

    /**
     * Methode qui modifie les PVs
     *
     * @param p1 Creature
     */
    public void degats(Creature p1) {
        p1.setPtVie(p1.getPtVie() - BDegAtt);
    }

    /**
     *Modifie l'attaque du personnage et ajoute l'utilisable à l'array effets
     * @param c
     */
    @Override
    public void utiliser(Creature c) {
        c.setDegAtt(c.getDegAtt() + BDegAtt);
        c.getEffets().add(this);
    }

    /**
     *Donne la lettre de l'objet
     * @return String lettre
     */
    @Override
    public String getLettre() {
        return lettre;
    }

    /**
     *Fixe la lettre de l'objet
     * @param lettre
     */
    @Override
    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    /**
     *Donne proprement le nom et les caractéristiques principales de l'objet
     * @return String affiche
     */
    @Override
    public String affiche() {
        return ("Une épée augmentant de " + this.getBDegAtt() + " l'attaque");
    }

    /**
     *Donne la durée de vie restante de l'objet
     * @return int duree
     */
    @Override
    public int getDuree() {
        return duree;
    }

    /**
     *Modifie la durée de vie de l'objet
     * @param duree
     */
    @Override
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     *Retire le bonus donné par l'objet et fixe sa durée à 0
     * @param c
     */
    @Override
    public void finUtiliser(Creature c) {
        duree = 0;
        c.setDegAtt(c.getDegAtt() - BDegAtt);
    }

    }

