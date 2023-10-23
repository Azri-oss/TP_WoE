/**
 *
 * @author 33651
 *
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe du monstre : Loup
 */

public class Loup extends Monstre implements Combattant {

    /**
     * Constructeur principal
     *
     * @param ptVie Points de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param pos position du loup
     * @param distAttMax Distance maximale d'attaque
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
   public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, int distAttMax, ArrayList<Utilisable> inventaire,ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, inventaire,effets);
        this.setLettre("W");
   }

    /**
     * Constructeur de copie
     *
     * @param m Monstre à copier
     */
    public Loup(Monstre m) {
        super(m);
        this.setLettre("W");
    }

    /**
     * Constructeur par défaut
     */
    public Loup() {
        super();
        this.setLettre("W");
    }

    /**
     * Methode qui modélise le système des combats au corps à corps
     *
     * @param c Creature à combattre
     */
    @Override
    public void combattre(Creature c) {
        boolean jce = (c.isEstJoueur() == true || this.isEstJoueur() == true);
        if (jce) {
            System.out.println(toString() + "tente une attaque sur" + c.toString());
        }
        Random generateur = new Random();
        int entierAleatoire = generateur.nextInt(100);
        if (entierAleatoire > getPageAtt()) {
            if (jce) {
                System.out.println(toString() + "loupe son attaque");
            }
        } else {
            if (jce) {
                System.out.println(toString() + "à réussi son attaque");
                System.out.println(c.toString() + "tente de parer");
            }
            entierAleatoire = generateur.nextInt(100);
            if (entierAleatoire > getPagePar()) {
                if (jce) {
                    System.out.println(c.toString() + "loupe sa parade et perd" + getDegAtt() + "PV");
                }
                c.setPtVie(c.getPtVie() - getDegAtt(), this);
            } else {
                if (jce) {
                    System.out.println(c.toString() + "réussit sa parade et perd" + (getDegAtt() - c.getPtPar()) + "PV");
                }
                c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar(), this);
            }
        }
    }

    /**
     *
     * @return La chaine de caractère : Loup
     */
    @Override
    public String toString() {
        return "Loup ";
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
            int idCreature =rs.getInt(1);
            String query = "INSERT INTO monstre (nom, id_creature) VALUES ('loup',"+idCreature+")";
            stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Exception " + ex.getMessage());
        }
    }
}
