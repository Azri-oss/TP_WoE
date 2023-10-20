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
         World w = new World();
         /**
        
        
        System.out.println("Le nombre de pv total est de : "+ w.ptVieIterateurs());
        int tour = w.tourDeJeu(1); **/
          
        int tours = 20;
        int t =0;
        
        Joueur j= new Joueur();
        
        Joueur jou= j.choixperso();
        w.creeMondeAlea(jou);
        
        w.affichage(jou);
        
        while(t<tours){
            t= w.tourDeJeu(t,jou);
        }
              
        //System.out.println(tour);
        /**
         * System.out.println("Déplacement ..."); w.getBugs()[0].deplace();
         * w.getBugs()[1].deplace(); w.getRobin().deplace();
         * w.getPeon().deplace(); System.out.println("Le premier lapin se situe
         * maintenant en " + w.getBugs()[0].getPos()); System.out.println("Le
         * deuxieme lapin se situe maintenant en " + w.getBugs()[1].getPos());
         * System.out.println("L'archer se situe maintenant en " +
         * w.getRobin().getPos()); System.out.println("Le paysan se situe
         * maintenant en " + w.getPeon().getPos());*
         */

        /**
         * w.guillaumeT = new Archer(w.robin); w.robin.deplace();
         * System.out.println("Robin se situe en " + w.robin.getPos());
         * System.out.println("GuillaumeT se situe en " + w.guillaumeT.getPos());*
         */
    }
}
