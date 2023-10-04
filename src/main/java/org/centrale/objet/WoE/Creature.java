
package org.centrale.objet.WoE;
import java.util.*;
/**
 *
 * @author 33651
 */

/** Classe Créature
 * 
 */
public abstract class  Creature {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos; 

    /**
     *Constructeur principal
     * @param ptVie Points de vie
     * @param degAtt Dégâts d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param pos Position de la creature
     */
    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = pos;
    }

    /**
     *Constructeur par défaut
     */
    public Creature() {
        this.ptVie = 10;
        this.degAtt = 2;
        this.ptPar = 2;
        this.pageAtt = 2;
        this.pagePar = 2;
        Point2D pdep=new Point2D();
        this.pos = pdep;
    }

    /**
     *Constructeur de copie
     * @param c Creature
     */
    public Creature(Creature c){
        this.ptVie = c.getPtVie() ;
        this.degAtt = c.getDegAtt();
        this.ptPar = c.getPtPar();
        this.pageAtt = c.getPageAtt();
        this.pagePar = c.getPagePar();
        this.pos = new Point2D(c.getPos());  
        
        
    }
    
    


    
     /**Renvoie la valeur des points de vie
     * @return ptVie le nombre de point de vie 
     */
   public int getPtVie() {
        return ptVie;
    }
    /** Récupère les dégats d'attaque
     *
     * @return degAtt le nombre de dégats d'attaque
     * 
     */
    public int getDegAtt() {
        return degAtt;
    }
    /**Récupère les points de parade
     *
     * @return ptPar le nombre de points de parade
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     *Récupère le pourcentage de reussite d'une attaque
     * @return pageAtt le pourcentage de reussite d'une attaque
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     *Récupère le pourcentage de reussite d'une parade
     * @return pagePar le pourcentage de reusssite d'une parade
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     *Récupère la position de la creature
     * @return pos la position de la creature
     */
    public Point2D getPos() {
        return pos;
    }

    
    /**
     *Modifie le nombre de points de vie d'une creature
     * @param ptVie le nombre de point de vie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }
    /**
     *Modifie les dégats d'attaque de la creature
     * @param degAtt le nombre de dégats infligés
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }
    /**
     *Modifie les points de parade de la creature
     * @param ptPar le nmbre de points de parade
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     *Modifie le pourcentage de réussite d'une attaque de la creature
     * @param pageAtt le pourcentage de réussite d'une attaque
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     *Modifie le pourcentage de réussite d'une parade
     * @param pagePar le pourcentage de reussite d'une parade
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     *Modifie la position de la creature
     * @param pos Position de la Creature
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
     /**
     *Methode qui affiche la position en 2D du personnage
     */
    public void affiche(){
        System.out.println("[" + pos.getX() + ";" + pos.getY() + "]");
    }
    
/**
     *Déplace une créature sur une case adjacente
     * 
     */
    public void deplace() {
        Random generateur = new Random();
        int entierAleatoire = generateur.nextInt(7);
        switch (entierAleatoire) {
            case 0:
                pos.translate(-1, -1);
                break;
            case 1:
                pos.translate(-1, 0);
                break;
            case 2:
                pos.translate(-1, 1);
                break;
            case 3:
                pos.translate(0, 1);
                break;
            case 4:
                pos.translate(1, 1);
                break;
            case 5:
                pos.translate(1, 0);
                break;
            case 6:
                pos.translate(1, -1);
                break;
            case 7:
                pos.translate(0, -1);
                break;
        }
    }

    /**
     *Methode qui dit si la potion est consommé ou nom par la creature, dans ce cas elle modifie les points de vie de la creature.
     * @param ps Objet : Potion de Soin
     * @return booleen Vrai si il potion est consommée, Faux autrement
     */
    public boolean consommation(PotionSoin ps){
        if (getPos().distance(ps.getPos())==0){
            setPtVie(ps.getPtSoin()+getPtVie());
            System.out.println(toString()+ "a récupéré " + ps.getPtSoin());
            ps=null;
            return true;}
            else{ return false;}
    
     }
}

     
