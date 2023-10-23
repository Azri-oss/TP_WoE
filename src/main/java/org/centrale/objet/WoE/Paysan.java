/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
       public Paysan(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos, ArrayList<Utilisable> inventaire,ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, nom, pos, inventaire, effets);
        this.setLettre("P");
    }

    /**
     *Constructeur par defaut
     */
    public Paysan(){
        super();
        this.setLettre("P");
        String[] listeNoms = {
            "Alden",
            "Branwen",
            "Cedric",
            "Daelin",
            "Elinor",
            "Faelan",
            "Gwendolyn",
            "Halvor",
            "Ismene",
            "Jareth",
            "Kaelith",
            "Lorelei",
            "Maelis",
            "Nerys",
            "Orin",
            "Pryderi",
            "Quinlan",
            "Rhoslyn",
            "Sylvan",
            "Talwyn",
            "Urien",
            "Vaelora",
            "Wynne",
            "Xander",
            "Ysolde",
            "Zephyr",
            "Auberon",
            "Brynn",
            "Cassia",
            "Darian",
            "Elowen",
            "Faelan",
            "Galen",
            "Hawthorn",
            "Ilythia",
            "Jorunn",
            "Kaelith",
            "Lirael",
            "Maelis",
            "Nerys",
            "Oberon",
            "Persephone",
            "Quinlan",
            "Ravenna",
            "Sarai",
            "Tavish",
            "Una",
            "Vaelora",
            "Wynne",
            "Xander",
            "Ysolde",
            "Zephyr"
        };
        nomAlea(listeNoms);
    }
    
    /**
     *Constructeur de copie
     * @param p Paysan à copier
     */
    public Paysan(Paysan p){
        super((Personnage)p);
        this.setLettre("P");
    }

    /**
     * Méthode retournant le nom et le type du personnage
     * @return La chaine de caractère : Paysan
     */
    @Override
    public String toString() {
        return (getNom() + " le Paysan");
    }
    
    /**
     *Méthode de sauvegarde de l'élément dans la bdd
     * @param connection Connection à la bdd
     * @param idMonde Identifiant du monde dans la bdd
     */
    @Override
    public void saveToDatabase(Connection connection, int idMonde) {
        super.saveToDatabase(connection, idMonde);
        try {
            String query1 = "SELECT MAX(id_creature) FROM creature";
            PreparedStatement stmt = connection.prepareStatement(query1);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idCreature = rs.getInt(1);
            String query = "INSERT INTO humanoide (nom, type, est_jouable, id_creature)"
                    + "VALUES ('" + this.getNom() + "','Paysan', 0," + idCreature + ")";
            stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Exception " + ex.getMessage());
        }
    }

    
    
}
