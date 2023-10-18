
package org.centrale.objet.WoE;

/**
 *
 * @author 33651
 */
/**
 * 
 * Classe de l'objet : Epee
 */
public class Epee extends Objet implements Utilisable{
   private int BDegAtt;
   private String lettre="o";

   
    /**
     *Constructeur par défaut
     */
    public Epee() {
        super();
        this.BDegAtt = 6;

    }

    /**
     *Constructeur principal
     * @param BDegAtt Bonus de dégats d'attaque donnée par l'épée
    
     * @param nombre Nombre d'épée
     * @param nom Nom de l'épée
     * @param pos Position de l'épée
     */
    public Epee(int BDegAtt, int nombre, String nom, Point2D pos) {
        super(nombre, nom, pos);
        this.BDegAtt = BDegAtt;
         
    }

    /**
     *Constructeur de copie
     * @param e Epee
     */
    public Epee(Epee e){
        super((Objet) e);
        this.BDegAtt=e.getBDegAtt();
;
    }
    
    /**
     *Donne le bonus d'attause et la portée d'une epée
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        return "Epee{" + "BDegAtt=" + BDegAtt +  '}';
    }

    /**
     *Récupère la valeur du bonus d'attaque de l'épée
     * @return BDegAtt le bonus d'attaque
     */
    public int getBDegAtt() {
        return BDegAtt;
    }

  

    /**
     *Modifie le bonus d'attaque d'une épée
     * @param BDegAtt Bonus de dégats ingligés
     */
    public void setBDegAtt(int BDegAtt) {
        this.BDegAtt = BDegAtt;
    }

    /**
     *Methode qui modifie les PVs 
     * @param p1 Creature
     */
    public void degats(Creature p1){
       p1.setPtVie(p1.getPtVie()-BDegAtt);
   }
    
   @Override
    public void utiliser(Creature c){
        degats(c);
        
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }


}
