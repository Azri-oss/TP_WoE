/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.*;

/**
 * Classe World : définit le monde dans lequel se passe le jeu
 *
 * @author noesa
 */
public class World {

    /**
     * Archer présent sur le monde
     */
    public Archer robin;
    /**
     * Paysan présent sur le monde
     */
    public Paysan peon;
    /**
     * Lapins présents sur le monde
     */
    public Lapin[] bugs;
    /**
     * Archer présents sur le monde
     */
    public Archer guillaumeT;
    /**
     * Guerrier présent sur le monde
     */
    public Guerrier grosBill;
    /**
     * Loup présent sur le monde
     */
    public Loup wolfie;
    /**
     * Array des créatures présentes sur le monde
     */
    public ArrayList<Creature> creatures;
    /**
     * Constante de la taille du monde
     */
    public static final int TAILLE = 50;

    /**
     * Donne l'archer présent sur le monde
     *
     * @return robin
     */
    public Archer getRobin() {
        return robin;
    }

    /**
     * Fixe l'archer présent sur le monde
     *
     * @param robin
     */
    public void setRobin(Archer robin) {
        this.robin = robin;
    }

    /**
     * Donne le paysan présent sur le monde
     *
     * @return
     */
    public Paysan getPeon() {
        return peon;
    }

    /**
     * Fixe le paysan présent sur le monde
     *
     * @param peon
     */
    public void setPeon(Paysan peon) {
        this.peon = peon;
    }

    /**
     * Donne les lapins sur le monde sous forme de vecteur
     *
     * @return Lapin[]
     */
    public Lapin[] getBugs() {
        return bugs;
    }

    /**
     * Fixe les lapins sur le monde sous forme de vecteur
     *
     * @param bugs
     */
    public void setBugs(Lapin[] bugs) {
        this.bugs = bugs;
    }

    /**
     * Méthode testPresence : renvoie true si la position de p est déjà
     * enregistrée dans l'array positions
     *
     * @param p
     * @param positions
     * @return boolean
     */
    private boolean testPresence(Creature c, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if ((creature.getPos().distance(c.getPos()) == 0) && (c != creature)){
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode creeMondeAlea : Génère 2 lapins, un archer et un paysan de
     * manière aléatoire dans le monde, en évitant que deux entités aient la
     * même position
     */
    public void creeMondeAlea() {
        /**
         * Random generateur = new Random(); Point2D alea = new
         * Point2D(generateur.nextInt(20), generateur.nextInt(20)); Point2D[]
         * positions = new Point2D[7];
         *
         * for (int i = 0; i < 2; i++) { positions[i] = new Point2D(alea);
         *
         * bugs[i].setPos(alea); do { alea.setPosition(generateur.nextInt(20),
         * generateur.nextInt(20)); } while (this.testPresence(alea,
         * positions)); }
         *
         * positions[2] = new Point2D(alea);
         *
         * robin.setNom("Robin"); robin.setPos(alea); do {
         * alea.setPosition(generateur.nextInt(20), generateur.nextInt(20)); }
         * while (this.testPresence(alea, positions)); positions[3] = new
         * Point2D(alea);
         *
         * peon.setNom("Paysan"); peon.setPos(alea); do {
         * alea.setPosition(generateur.nextInt(20), generateur.nextInt(20)); }
         * while (this.testPresence(alea, positions)); positions[4] = new
         * Point2D(alea);
         *
         * grosBill.setNom("Gros Bill"); grosBill.setPos(alea); do {
         * alea.setPosition(generateur.nextInt(20), generateur.nextInt(20)); }
         * while (this.testPresence(alea, positions)); positions[5] = new
         * Point2D(alea); wolfie.setPos(alea); do {
         * alea.setPosition(generateur.nextInt(20), generateur.nextInt(20)); }
         * while (this.testPresence(alea, positions)); positions[6] = new
         * Point2D(alea);
         *
         * guillaumeT.setNom("Guillaume Truand"); guillaumeT.setPos(alea);
        affichePositions();*
         */

        Random generateur = new Random();
        int[] nombres = new int[5];
        int total = 100;
        for (int i = 0; i < 5; i++) {
            nombres[i] = generateur.nextInt(total);
            total = total - nombres[i];
        }
        for (int j = 0; j < nombres[0]; j++) {
            creatures.add(new Archer());
        }
        for (int j = 0; j < nombres[1]; j++) {
            creatures.add(new Guerrier());
        }
        for (int j = 0; j < nombres[2]; j++) {
            creatures.add(new Paysan());
        }
        for (int j = 0; j < nombres[3]; j++) {
            creatures.add(new Loup());
        }
        for (int j = 0; j < nombres[4]; j++) {
            creatures.add(new Lapin());
        }
        for (Creature creature : creatures) {
            while (testPresence(creature, creatures)){
                creature.setPos(new Point2D(generateur.nextInt(50), generateur.nextInt(50)));
                
            }
        }
    }
    
    /**
     *Méthode ptVieTotal : Parcours de l'array creatures sur la taille pour calculer la somme des points de vie des creatures
     * @return int Somme des points de vie
     */
    public int ptVieTotal(){
        System.out.println("On a bien "+creatures.size()+ " créatures dans notre Array/liste. Début du parcours ...");
        int s = 0;
        long tDebut = System.nanoTime();
        for (int i=0; i<creatures.size();i++) {
            s = s + creatures.get(i).getPtVie();
        }
        long tFin = System.nanoTime();
        System.out.println("Fin ! Le temps est de "+(tFin-tDebut)+" ns");
        return s;
    }
    
    /**
     *Méthode ptVieIterateurs : Parcours de l'array creatures sur la taille pour calculer la somme des points de vie des creatures
     * @return int Somme des points de vie
     */
    public int ptVieIterateurs(){
        System.out.println("On a bien "+creatures.size()+ " créatures dans notre Array/Liste. Début du parcours ...");
        int s = 0;
        Iterator<Creature> listIt = creatures.iterator();
        long tDebut = System.nanoTime();
        while (listIt.hasNext()) {
            s = s + listIt.next().getPtVie();
        }
        long tFin = System.nanoTime();
        System.out.println("Fin ! Le temps est de "+(tFin-tDebut)+" ns");
        return s;
    }
    
    /**
     *Méthode affichePositions : affiche proprement les positions de chacune des créatures présentes sur le monde
     */
    public void affichePositions() {
        for (Creature creature : creatures){
            System.out.println("Un " + creature.toString()+ " se trouve en " + creature.getPos().toString());
        }
    }

    /**
     *Méthode tourDeJeu : Annonce le début du tour, fait la phase de déplacement et lance les combats
     * @param tour
     * @return tour+1
     */
    public int tourDeJeu(int tour) {
        System.out.println("Début du tour " + tour);
        System.out.println("Déplacement des personnages ...");
        grosBill.setPos(new Point2D(5, 4));
        robin.setPos(new Point2D(5, 6));
        affichePositions();
        System.out.println("Phase de combat :");
        robin.combattre(grosBill);

        return tour + 1;
    }

    /**
     * Constructeur
     */
    public World() {
        bugs = new Lapin[2];
        bugs[0] = new Lapin();
        bugs[1] = new Lapin();
        robin = new Archer();
        this.peon = new Paysan();
        grosBill = new Guerrier();
        wolfie = new Loup();
        guillaumeT = new Archer();
        creatures = new ArrayList<>();
    }

}
