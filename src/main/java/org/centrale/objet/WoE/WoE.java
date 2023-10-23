/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.objet.WoE;

import java.util.Scanner;

/**
 * Classe contenant le main
 *
 * @author noesa
 */
public class WoE {

    public static void main(String[] args) {
        ElemInterface.afficheTitre();
        ElemInterface.afficheIntro();
        int tours;
        World w;
        Joueur j;
        String choixLocal = null;
        int idJoueur = -1;
        DatabaseTools database = new DatabaseTools();
        while(!("LOCAL".equals(choixLocal) || "REMOTE".equals(choixLocal))){
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez 'LOCAL' pour jouer en local ou 'REMOTE' pour se connecter à la base de données");
            System.out.println("La base de données n'étant pas installée, merci de taper 'LOCAL");
            choixLocal = sc.nextLine();
        }
        boolean local = ("LOCAL".equals(choixLocal));
        if (local) {
            w = new World();
            Joueur jou = new Joueur();
            j = jou.choixperso();
            w.creeMondeAlea(j);
            tours = 200;
            w.tour = 1;
        } else {
            database.connect();
            idJoueur = database.connexion();
            j = new Joueur();
            w = database.choixCharger(idJoueur, j);
            tours = 200;
        }

        while ((w.tour < tours) && (j.getPers().isVivant()) && (w.testVictoire() == false)) {
            w.tour = w.tourDeJeu(w.tour, j);
            if(!local){
                database.saveWorld(idJoueur, database.getNomPartie(), null, w);
            }
        }
        if (!j.getPers().isVivant()) {
            ElemInterface.afficheMort();
        } else if (w.testVictoire()) {
            ElemInterface.afficheVictoire();
        }
        //database.disconnect();

    }
}
