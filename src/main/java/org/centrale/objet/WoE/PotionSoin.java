/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author 33651
 */
/**
 * 
 * Classe de l'objet : Potion de soin
 */
public class PotionSoin extends Objet {
    private int ptSoin;

    /**
     *Constructeur principal
     * @param ptSoin Nombre de points de vie soignés
     * @param nombre Nombre de potions
     * @param nom Nom de la potion
     * @param pos Position de la potion dans le monde
     */
    public PotionSoin(int ptSoin, int nombre, String nom, Point2D pos) {
        super(nombre, nom, pos);
        this.ptSoin = ptSoin;
    }

    /**
     *Constructeur par défaut
     */
    public PotionSoin() {
        this.ptSoin = 8;
    }

    /**
     *Constructeur de copie
     * @param ps Objet : Potion de Soin
     */
    public PotionSoin(PotionSoin ps){
    super((Objet) ps);
    this.ptSoin= ps.getPtSoin();
}
    
    /**
     * Récupère le nombre de PV soigné
     * @return ptSoin Nombre de PV soigné
     */
    public int getPtSoin() {
        return ptSoin;
    }

    /**
     * Modifie le nombre de PV soigné
     * @param ptSoin Nombre de points de vie soignés
     */
    public void setPtSoin(int ptSoin) {
        this.ptSoin = ptSoin;
    }

    /**
     *Methode qui augmente les PV de la créature qui prend la potion
     * @param pers Creature qui se soigne avec la potion
     */
    public void soigner(Creature pers){
        pers.setPtVie(getPtSoin()+pers.getPtVie());
    }
}
