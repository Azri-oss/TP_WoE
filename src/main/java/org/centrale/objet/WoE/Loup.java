/**
 *
 * @author 33651
 * 
 */

package org.centrale.objet.WoE;
import java.util.Random;
/**
 * Classe du monstre : Loup
 */

public class Loup extends Monstre{

    /**
     *Constructeur principal
     * @param ptVie Points de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param pos position du loup
     */
    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    /**
     *Constructeur de copie
     * @param m Monstre
     */
    public Loup(Monstre m) {
        super(m);
    }

    /**
     *Constructeur par défaut
     */
    public Loup() {
        super();
    }

    /**
     *Methode qui modélise le système des combats au corps à corps
     * @param c Creature
     */
    public void combattre(Creature c){
    System.out.println(toString()+"tente une attaque sur"+c.toString());
    Random generateur= new Random();
    int entierAleatoire= generateur.nextInt(100);
    if(entierAleatoire>getPageAtt()){
        System.out.println(toString()+"loupe son attaque");
    }
    else{
        System.out.println(toString() +"à réussi son attaque");
        System.out.println(c.toString()+"tente de parer");
        entierAleatoire=generateur.nextInt(100);
        if(entierAleatoire>getPagePar()){
            System.out.println(c.toString()+"loupe sa parade et perd"+ getDegAtt()+"PV");
            c.setPtVie(c.getPtVie()-getDegAtt());
        }
        else{
            System.out.println(c.toString()+"réussit sa parade et perd"+(getDegAtt()- c.getPtPar())+"PV");
            c.setPtVie(c.getPtVie()-getDegAtt()+c.getPtPar());
        }
    }
}    

    /**
     *
     * @return La chaine de caractère : Loup
     */
    @Override
    public String toString() {
        return "Loup ";
    }
}
