/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author 33651
 */

/** Classe du personnage de type Paysan
 * 
 */
public class Paysan extends Personnage {

    /**
     *Constructeur principal
     * @param ptVie Points de vie
     * @param degAtt Dégats d'attaque 
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de réussite d'une attaque
     * @param pagePar Pourcentage de reussite du parade
     * @param distAttMax Distance d'attaque maximale
     * @param nom Nom du paysan
     * @param pos Position du paysan
     */
    public Paysan(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, nom, pos);
    }

    /**
     *Constructeur par defaut
     */
    public Paysan(){
        
    }
    
    /**
     *Constructeur de copie
     * @param p Paysan
     */
    public Paysan(Paysan p){
        super((Personnage)p);
    }

    /**
     * 
     * @return La chaine de caractère : Paysan
     */
    @Override
    public String toString() {
        return "Paysan";
    }
    
    
}
