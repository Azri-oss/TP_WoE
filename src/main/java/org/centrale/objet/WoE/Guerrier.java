package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author 33651
 */
/**
 *
 * Classe du personnage : Guerrier
 */
public class Guerrier extends Personnage implements Combattant {

    private Epee arme;

    /**
     * Constructeur principal
     *
     * @param arme Epée du guerrier
     * @param ptVie Points de vie
     * @param degAtt Dégats d'attaque
     * @param ptPar Points de parade
     * @param pageAtt Pourcentage de reussite d'une attaque
     * @param pagePar Pourcentage de reussite d'une parade
     * @param distAttMax Distance maximale d'attaque
     * @param nom Nom du guerrier
     * @param pos Position du guerrier
     * @param inventaire ArrayList comprenant les utilisables de l'inventaire
     * @param effets ArrayList comprenant les utilisables en fonctionnement
     */
    public Guerrier(Epee arme, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, String nom, Point2D pos, ArrayList<Utilisable> inventaire, ArrayList<Utilisable> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, nom, pos, inventaire, effets);
        this.arme = arme;
        this.setLettre("G");
    }

    /**
     * Constructeur par défaut
     */
    public Guerrier() {
        super();
        this.arme = new Epee();
        this.setLettre("G");
        String[] listeNoms = {
            "Aragorn", "Lysandra", "Thorgrim", "Elysia", "Draven",
            "Seraphina", "Gorin", "Valka", "Thrain", "Azura",
            "Hrothgar", "Elara", "Kael", "Rowan", "Sylas",
            "Isolde", "Grimm", "Eilin", "Ragnor", "Selene",
            "Fenrir", "Brynhild", "Kaldor", "Sylvari", "Gwynn",
            "Caelan", "Brienne", "Rurik", "Daelia", "Korin",
            "Elowen", "Valthrun", "Sigrid", "Lorelei", "Krom",
            "Eirik", "Elysande", "Ulfgar", "Thalassa", "Branwen",
            "Thorne", "Vespera", "Magnar", "Eowyn", "Haldor",
            "Astrid", "Ragnarok", "Astraea", "Soren", "Morgana",
            "Galdar", "Elowyn", "Sigrun", "Artemis", "Varian",
            "Faelan", "Kaelith", "Grimbold", "Isabeau", "Draegon",
            "Thyra", "Caelum", "Frost", "Aurelia", "Wolfram",
            "Eowulf", "Thyri", "Vaelen", "Faelore", "Thorne",
            "Elowen", "Ailin", "Kaelith", "Sylas", "Elysia",
            "Thrain", "Valka", "Sigrun", "Brynhild", "Draegon",
            "Thyra", "Seraphina", "Gorin", "Artemis", "Faelan",
            "Magnar", "Astraea", "Isabeau", "Frost", "Vespera",
            "Thorgrim", "Lorelei", "Kaldor", "Grimbold", "Thalassa",
            "Eirik", "Astrid", "Rurik", "Eowyn", "Morgana",
            "Caelan", "Ragnarok", "Ailin", "Galdar", "Sylvari",
            "Varian", "Wolfram", "Eowulf", "Selene", "Gwynn"
        };
        nomAlea(listeNoms);
    }

    /**
     * Constructeur de copie
     *
     * @param arme Epee
     * @param pers Personnage
     */
    public Guerrier(Epee arme, Personnage pers) {
        super(pers);
        this.arme = arme;
        this.setLettre("G");
    }

    /**
     * Récupère les caractéristiques de l'arme
     *
     * @return arme Epee
     */
    public Epee getArme() {
        return arme;
    }

    /**
     * Modifie les caractéristiques de l'arme
     *
     * @param arme Epee
     */
    public void setArme(Epee arme) {
        this.arme = arme;
    }

    /**
     * Methode qui modélise le système de combat corps à corps
     *
     * @param c Creature à combattre
     */
    @Override
    public void combattre(Creature c) {
        boolean jce = (c.isEstJoueur() == true || this.isEstJoueur() == true);
        if (jce) {
            System.out.println(getNom() + "tente une attaque sur" + c.toString());
        }
        Random generateur = new Random();
        int entierAleatoire = generateur.nextInt(100);
        if (entierAleatoire > getPageAtt()) {
            if (jce) {
                System.out.println(getNom() + "loupe son attaque");
            }
        } else {
            if (jce) {
                System.out.println(getNom() + "à réussi son attaque");
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
     * @return La chaine de caractère : nom + guerrier
     */
    @Override
    public String toString() {
        return (getNom() + " le Guerrier");
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
            String query = "INSERT INTO humanoide (nom, type, est_jouable, id_creature)"
                    + "VALUES ('"+ this.getNom()+"','Guerrier', ?,"+idCreature+")";
            stmt = connection.prepareStatement(query);
            if (isEstJoueur()){
                stmt.setInt(1, 1);
            } else {
                stmt.setInt(1, 0);
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Exception " + ex.getMessage());
        }
    }

}
