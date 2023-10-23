/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.objet.WoE;

/**
 *Classe contenant le main
 * @author noesa
 */
public class WoE {

    public static void main(String[] args) {
        ElemInterface.afficheTitre();
        DatabaseTools database = new DatabaseTools();
        database.connect();
        int idJoueur = database.connexion();
        Joueur j = new Joueur();
        World w = database.choixCharger(idJoueur, j);
        
        int tours = 200;

        
        while((w.tour<tours) &&(j.getPers().isVivant()) && (w.testVictoire() == false)){
            w.tour= w.tourDeJeu(w.tour,j);
            database.saveWorld(idJoueur, database.getNomPartie(), null, w);
        }
        if(!j.getPers().isVivant()){
            ElemInterface.afficheMort();
        } else if (w.testVictoire()){
            ElemInterface.afficheVictoire();
        }
        database.disconnect();
        
}}
