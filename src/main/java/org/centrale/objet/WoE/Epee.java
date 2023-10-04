
package org.centrale.objet.WoE;

/**
 *
 * @author 33651
 */
/**
 * 
 * Classe de l'objet : Epee
 */
public class Epee extends Objet {
   private int BDegAtt;
   private int DistMax;
   
    /**
     *Constructeur par défaut
     */
    public Epee() {
        super();
        this.BDegAtt = 6;
        this.DistMax = 2;
    }

    /**
     *Constructeur principal
     * @param BDegAtt Bonus de dégats d'attaque donnée par l'épée
     * @param DistMax Distance maximale d'attque avec l'épée
     * @param nombre Nombre d'épée
     * @param nom Nom de l'épée
     * @param pos Position de l'épée
     */
    public Epee(int BDegAtt, int DistMax, int nombre, String nom, Point2D pos) {
        super(nombre, nom, pos);
        this.BDegAtt = BDegAtt;
        this.DistMax = DistMax;    
    }

    /**
     *Constructeur de copie
     * @param e Epee
     */
    public Epee(Epee e){
        super((Objet) e);
        this.BDegAtt=e.getBDegAtt();
        this.DistMax=e.getDistMax();
    }
    
    /**
     *Donne le bonus d'attause et la portée d'une epée
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        return "Epee{" + "BDegAtt=" + BDegAtt + ", DistMax=" + DistMax + '}';
    }

    /**
     *Récupère la valeur du bonus d'attaque de l'épée
     * @return BDegAtt le bonus d'attaque
     */
    public int getBDegAtt() {
        return BDegAtt;
    }

    /**
     *Récupère la valeur de la portée maximale
     * @return DistMax Distance maximale
     */
    public int getDistMax() {
        return DistMax;
    }

    /**
     *Modifie le bonus d'attaque d'une épée
     * @param BDegAtt Bonus de dégats ingligés
     */
    public void setBDegAtt(int BDegAtt) {
        this.BDegAtt = BDegAtt;
    }

    /**
     *Modifie la portée de l'épée
     * @param DistMax Portée maximale
     */
    public void setDistMax(int DistMax) {
        this.DistMax = DistMax;
    }

    /**
     *Methode qui modifie les PVs 
     * @param p1 Creature
     */
    public void degats(Creature p1){
       p1.setPtVie(p1.getPtVie()-BDegAtt);
   }
}
