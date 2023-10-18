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
     * Array des créatures présentes sur le monde
     */
    public ArrayList<Creature> creatures;
    /**
     * Array des objets présents sur le monde
     */
    public ArrayList<Utilisable> objets;
    /**
     * Constante de la HAUTEUR du monde
     */
    public static final int HAUTEUR = 20;
    /**
     * Constante de la LARGEUR du monde
     */
    public static final int LARGEUR = 70;

    /**
     * Méthode testPresence : renvoie true si la position de p est déjà
     * enregistrée dans l'array positions
     *
     * @param p
     * @param positions
     * @return boolean
     */
    public boolean testCaseOccupeePerso(ElementDeJeu c, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getPos().distance(c.getPos()) == 0 && (c != creature) || c.getPos().getX() >= LARGEUR || c.getPos().getY() >= HAUTEUR || c.getPos().getX() < 0 || c.getPos().getY() < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean testCaseOccupeePerso(Point2D p, Creature c, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getPos().distance(p) == 0 && (c != creature) || p.getX() >= LARGEUR || p.getY() >= HAUTEUR || p.getX() < 0 || p.getY() < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean testCaseOccupeeObj(Utilisable o, ArrayList<Utilisable> objets) {
        for (Utilisable ob : objets) {
            if ((ob.getPos().distance(o.getPos()) == 0 && !(o.equals(ob))) || (o.getPos().getX() >= LARGEUR )|| (o.getPos().getY() >= HAUTEUR )|| (o.getPos().getX() < 0) || (o.getPos().getY() < 0)) {
                return true;
            }
        }
        return false;
    }
    


    /**
     * Méthode creeMondeAlea : Génère des créatures dans l'array creatures, en
     * évitant que deux entités aient la même position
     *
     * @param joueur
     */
    public void creeMondeAlea(Joueur joueur) {
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
        creatures.add(joueur.getPers());
        joueur.getPers().setPos(new Point2D(generateur.nextInt(LARGEUR), generateur.nextInt(HAUTEUR)));
        for (Creature creature : creatures) {
            while (testCaseOccupeePerso(creature, creatures)) {
                creature.setPos(new Point2D(generateur.nextInt(LARGEUR), generateur.nextInt(HAUTEUR)));

            }
        }
        Random nbobj = new Random();
        Random val = new Random();
        /**
         * int totalobj= nbcases-total-1;*
         */
        int totobj = 20;
        int[] nombresobj = new int[4];
        for (int i = 0; i < 4; i++) {
            nombresobj[i] = nbobj.nextInt(totobj);
            totobj = totobj - nombresobj[i];
        }

        for (int j = 0; j < nombresobj[0]; j++) {
            objets.add((Utilisable) new Epee());
        }
        for (int j = 0; j < nombresobj[1]; j++) {
            objets.add(new PotionSoin());
        }
        for (int j = 0; j < nombresobj[2]; j++) {
            objets.add(new Nourriture(val.nextInt(20) - 10, 3, 1, "Legumes", new Point2D()));
        }
        for (int j = 0; j < nombresobj[3]; j++) {
            objets.add(new Nourriture(val.nextInt(20) - 10, 3, 1, "Gateau", new Point2D()));
        }
        for (Utilisable ob : objets) {
            System.out.println(ob.toString());
            int compteur = 0;
            while (testCaseOccupeeObj(ob, objets)) {
                compteur = compteur + 1;
                System.out.println(compteur);
                ob.setPos(new Point2D(generateur.nextInt(LARGEUR), generateur.nextInt(HAUTEUR)));

            }
            System.out.println("On sort du while");
        }
        String t="re";
    }

    /**
     * Méthode ptVieTotal : Parcours de l'array creatures sur la taille pour
     * calculer la somme des points de vie des creatures
     *
     * @return int Somme des points de vie
     */
    public int ptVieTotal() {
        System.out.println("On a bien " + creatures.size() + " créatures dans notre Array/liste. Début du parcours ...");
        int s = 0;
        long tDebut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            s = s + creatures.get(i).getPtVie();
        }
        long tFin = System.nanoTime();
        System.out.println("Fin ! Le temps est de " + (tFin - tDebut) + " ns");
        return s;
    }

    /**
     * Méthode ptVieIterateurs : Parcours de l'array creatures sur la taille
     * pour calculer la somme des points de vie des creatures
     *
     * @return int Somme des points de vie
     */
    public int ptVieIterateurs() {
        System.out.println("On a bien " + creatures.size() + " créatures dans notre Array/Liste. Début du parcours ...");
        int s = 0;
        Iterator<Creature> listIt = creatures.iterator();
        long tDebut = System.nanoTime();
        while (listIt.hasNext()) {
            s = s + listIt.next().getPtVie();
        }
        long tFin = System.nanoTime();
        System.out.println("Fin ! Le temps est de " + (tFin - tDebut) + " ns");
        return s;
    }

    /**
     * Méthode affichePositions : affiche proprement les positions de chacune
     * des créatures présentes sur le monde
     */
    public void affichePositions() {
        for (Creature creature : creatures) {
            System.out.println("Un " + creature.toString() + " se trouve en " + creature.getPos().toString());
        }
    }

    /**
     * Méthode tourDeJeu : Annonce le début du tour, fait la phase de
     * déplacement et lance les combats
     *
     * @param tour
     * @param jou
     * @return tour+1
     */
    public int tourDeJeu(int tour, Joueur jou) {
        System.out.println("Début du tour " + tour);

        System.out.println("Action du joueur ...");
        jou.choixaction(this);

        System.out.println("Action des autres personnages ...");
        for (Creature c : creatures) {
            choixactionPNJ(c);
        }
        this.affichage(jou);

        for (Utilisable obj : jou.getPers().getEffets()) {
            if (obj instanceof Nourriture) {
                int dur = ((Nourriture) obj).getDuree();
                if (dur < 1) {
                    ((Nourriture) obj).setDuree(dur - 1);
                } else {
                    ((Nourriture) obj).finUtiliser(jou.getPers());
                }
            }
        }
        return tour + 1;
    }

    public void choixactionPNJ(Creature c) {

        if (c.isEstJoueur() == false) {

            Random action = new Random();
            ArrayList<Creature> adv = new ArrayList<>();
            ArrayList<Integer> choix = new ArrayList<>();

            Utilisable u = new Epee();

            if (!c.getInventaire().isEmpty()) {
                choix.add(0);
            }

            /* Choix entre rien faire et se deplacer et utiliser un objet de son inventaire*/
            for (Utilisable o : objets) {
                if (c.getPos().distance(o.getPos()) == 0) {

                    u = o;
                    choix.add(4);
                }
            }
            int posscombat = 0;
            if ((c instanceof Combattant)) {

                for (Creature creature : creatures) {
                    if (c.getPos().distance(creature.getPos()) <= c.getDistAttMax() && c != creature) {
                        posscombat = 1;

                        adv.add(creature);
                    }
                }
                if (posscombat == 1) {
                    choix.add(2);
                }
            }

            int compt = 0;
            int[] liste = c.testPresenceAlentour(this);
            for (int i = 0; i < liste.length; i++) {
                if (liste[i] == 0) {
                    compt++;
                }
            }
            if (compt != 8) {
                choix.add(1);
            }

            /*attaque ou pas*/
            choix.add(3);

            int sol = action.nextInt(choix.size());

            switch (choix.get(sol)) {

                case 0:

                    System.out.println(c.getInventaire());
                    int S = c.getInventaire().size();
                    System.out.println(S);

                    int choixobj = action.nextInt(S);

                    c.getInventaire().get(choixobj).utiliser(c);
                    System.out.println("Le PNJ " + c.toString() + "utilise l'objet" + c.getInventaire().get(choixobj).getNom());
                    c.retirerinventaire(c.getInventaire().get(choixobj));

                    break;

                case 1:
                    c.deplace(this);
                    System.out.println("Le PNJ c'est déplacé en " + c.getPos());
                    break;

                case 2:
                    int vic = action.nextInt(adv.size());
                    ((Combattant) c).combattre(adv.get(vic));
                    break;
                case 3:
                    System.out.println("Le PNJ " + c.toString() + " a décidé de ne rien faire.");
                    break;
                case 4:
                    int choix2 = action.nextInt(2);
                    /* Ranger*/
                    if (choix2 == 0) {
                        c.ajoutinventaire(u, this);
                        System.out.println("Le PNJ " + c.toString() + "a ajouté l'objet " + u.getNom() + "à son inventaire" + c.getInventaire());
                    } /*Utiliser directement*/ else {
                        u.utiliser(c);
                        c.getEffets().add(u);

                        objets.remove(u);
                        System.out.println("Le PNJ " + c.toString() + "a utilisé l'objet " + u.getNom());

                    }
                    break;
                default:
                    break;

            }
        }

    }

    public void affichage(Joueur joueur) {
        String s;
        Boolean r;
        for (int i = 0; i < HAUTEUR; i++) {
            s = "    ";
            for (int j = 0; j < LARGEUR; j++) {
                r = true;
                for (Creature creature : creatures) {
                    if ((creature.getPos().getX()) == j && (creature.getPos().getY() == HAUTEUR - 1 - i)) {
                        s = s + creature.getLettre();
                        r = false;
                    }
                }
                /**if (r){
                    for (Utilisable obj : objets) {
                    if ((obj.getPos().getX()) == j && (obj.getPos().getY() == HAUTEUR - 1 - i)) {
                        s = s + obj.getLettre();
                        r = false;
                    }
                }
                }**/

                if (r) {
                    s = s + "_";
                }
            }
            if (i == 1) {
                s = (s + "     Points de vie : " + joueur.getPers().getPtVie());
            } else if (i == 3) {
                s = (s + "     Points d'attaque : " + joueur.getPers().getDegAtt());
            }
            System.out.println(s);
        }
    }

    /**
     * Constructeur
     */
    public World() {
        creatures = new ArrayList<>();
        objets = new ArrayList<>();
    }

}
