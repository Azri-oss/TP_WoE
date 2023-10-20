/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.objet.WoE;

/**
 *
 * @author noesa
 */
public class ProjetTP {

    public static void main(String[] args) {
        ElemInterface.afficheTitre();
        World w = new World();
        int tours = 200;
        int t =0;
        
        Joueur j= new Joueur();
        
        Joueur jou= j.choixperso();
        w.creeMondeAlea(jou);
        
        while((t<tours) &&(jou.getPers().isVivant()) && (w.testVictoire() == false)){
            t= w.tourDeJeu(t,jou);
        }
        if(!jou.getPers().isVivant()){
            ElemInterface.afficheMort();
        } else if (w.testVictoire()){
            ElemInterface.afficheVictoire();
        }
        
}}
