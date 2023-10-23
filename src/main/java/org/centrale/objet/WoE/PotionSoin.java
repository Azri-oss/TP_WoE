/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Classe de l'objet : Potion de soin
 * @author 33651
 */
public class PotionSoin extends Objet implements Utilisable {

    private int ptSoin;
    private String lettre = "o";

    /**
     * Constructeur principal
     *
     * @param ptSoin Nombre de points de vie soignés
     * @param nombre Nombre de potions
     * @param nom Nom de la potion
     * @param pos Position de la potion dans le monde
     */
    public PotionSoin(int ptSoin, int nombre,String nom, Point2D pos) {
        super(nombre, nom, pos);
        this.ptSoin = ptSoin;
        this.setNom("Potion de soin");
    }

    /**
     * Constructeur par défaut
     */
    public PotionSoin() {
        Random g = new Random();
        this.ptSoin = 15 + g.nextInt(30);
        this.setNom("Potion de soin");
    }

    /**
     * Constructeur de copie
     *
     * @param ps Objet : Potion de Soin
     */
    public PotionSoin(PotionSoin ps) {
        super((Objet) ps);
        this.ptSoin = ps.getPtSoin();
        this.setNom("Potion de soin");
    }

    /**
     * Récupère le nombre de PV soigné
     *
     * @return ptSoin Nombre de PV soigné
     */
    public int getPtSoin() {
        return ptSoin;
    }

    /**
     * Modifie le nombre de PV soigné
     *
     * @param ptSoin Nombre de points de vie soignés
     */
    public void setPtSoin(int ptSoin) {
        this.ptSoin = ptSoin;
    }

    /**
     * Methode qui augmente les PV de la créature qui prend la potion
     *
     * @param pers Creature qui se soigne avec la potion
     */
    @Override
    public void utiliser(Creature pers) {
        pers.setPtVie(getPtSoin() + pers.getPtVie());
    }

    /**
     * Indique les caractéristiques de la potion
     *
     * @return String affichage
     */
    @Override
    public String toString() {
        return "PotionSoin{" + "ptSoin=" + ptSoin + '}';
    }

    /**
     * Donne la lettre (initiale) de l'objet
     *
     * @return String lettre
     */
    @Override
    public String getLettre() {
        return lettre;
    }

    /**
     * Fixe la lettre de l'objet (pour l'affichage)
     *
     * @param lettre
     */
    @Override
    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    /**
     * Donne proprement le nom et les caractéristiques de l'objet (pour
     * l'affichage)
     *
     * @return String affiche
     */
    @Override
    public String affiche() {
        return ("Une potion de soin octroyant " + this.getPtSoin() + " PV");
    }

    /**
     * Donne la durée de vie de l'objet (retourne -1 car une potion de vie a
     * pour l'instant une consommation instantanée)
     *
     * @return -1
     */
    @Override
    public int getDuree() {
        return -1;
    }

    /**
     * Fixe la durée de vie de l'objet (inutile pour l'instant, peut l'être par
     * la suite du projet)
     *
     * @param i
     */
    @Override
    public void setDuree(int i) {
    }

    /**
     * Dans le cas où l'on fixerait une durée aux potions de vie, alors cette
     * méthode permettrait de supprimer l'effet de la potion après que sa durée
     * de vie se soit écoulée
     *
     * @param c
     */
    @Override
    public void finUtiliser(Creature c) {
    }

    /**
     *Méthode de sauvegarde de l'élément dans la bdd
     * @param connection Connection à la bdd
     * @param idMonde Identifiant du monde dans la bdd
     */
    @Override
    public void saveToDatabase(Connection connection, int idMonde) {
        try {
            String query = "INSERT INTO objet (nom, b_pv)"
                    + "VALUES ('Potion de soin'," + ptSoin + ")";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            String query1 = "SELECT MAX(id_objet) FROM objet";
            stmt = connection.prepareStatement(query1);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idObjet = rs.getInt(1);
            query = "INSERT INTO comporte_obj (id_objet, id_monde, pos_x, pos_y)"
                    + "VALUES ('" + idObjet + "'," + idMonde + ",'" + getPos().getX() + "','" + getPos().getY() + "')";
            stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Exception " + ex.getMessage());
        }
    }

}
