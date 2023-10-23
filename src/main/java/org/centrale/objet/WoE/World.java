/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    public static int HAUTEUR = 20;
    /**
     * Constante de la LARGEUR du monde
     */
    public static int LARGEUR = 70;

    /**
     * Liste des nuages (deprecié)
     */
    public ArrayList<NuageToxique> nuages;
    
    /**
     *Entier décrivant le numéro du tour de jeu
     */
    public int tour;

    /**
     * Méthode testPresence : renvoie true si la position de p est déjà
     * enregistrée dans l'array creatures
     *
     * @param c Element de jeu dont la position est testée
     * @param creatures Liste des créatures présentes dans le monde, contenant les positions à comparer
     * @return boolean : true si et seulement si la position de p est déjà
     * enregistrée dans l'array creatures
     */
    public boolean testCaseOccupeePerso(ElementDeJeu c, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getPos().distance(c.getPos()) == 0 && (c != creature) || c.getPos().getX() >= LARGEUR || c.getPos().getY() >= HAUTEUR || c.getPos().getX() < 0 || c.getPos().getY() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si la case où la créature souhaite se déplacer est occupée ou
     * hors de la zone de jeu
     *
     * @param p Point2D de la position souhaitée
     * @param c Creature se déplaçant
     * @param creatures ArrayList comprenant toutes les créatures
     * @return boolean : false si la case où la créature souhaite se déplacer est accessible
     */
    public boolean testCaseOccupeePerso(Point2D p, Creature c, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getPos().distance(p) == 0 && (c != creature) || p.getX() >= LARGEUR || p.getY() >= HAUTEUR || p.getX() < 0 || p.getY() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indique si la case est occupée par un objet (pour pas que deux objets ne
     * se situent sur la même case)
     *
     * @param o Objet à tester
     * @param objets Liste des objets sur le monde
     * @return boolean : true si la case est occupée par un objet
     */
    public boolean testCaseOccupeeObj(Utilisable o, ArrayList<Utilisable> objets) {
        for (Utilisable ob : objets) {
            if ((ob.getPos().distance(o.getPos()) == 0 && !(o.equals(ob))) || (o.getPos().getX() >= LARGEUR) || (o.getPos().getY() >= HAUTEUR) || (o.getPos().getX() < 0) || (o.getPos().getY() < 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode creeMondeAlea : Génère des créatures dans l'array creatures, en
     * évitant que deux entités aient la même position
     *
     * @param joueur instance de la classe Joueur
     */
    public void creeMondeAlea(Joueur joueur) {
        Random generateur = new Random();
        int[] nombres = new int[5];
        int total = 50;
        for (int i = 0; i < 5; i++) {
            nombres[i] = generateur.nextInt(total);
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
        int totobj = 100;
        int[] nombresobj = new int[4];
        int nombreNuage = nbobj.nextInt(10);
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
            while (testCaseOccupeeObj(ob, objets)) {
                Random g = new Random();
                ob.setPos(new Point2D(g.nextInt(LARGEUR), g.nextInt(HAUTEUR)));

            }
        }
        for(int j = 0; j<nombreNuage;j++){
            nuages.add(new NuageToxique());
        }
        for (NuageToxique nuage : nuages){
            Random g = new Random();
            nuage.setPos(new Point2D(g.nextInt(LARGEUR), g.nextInt(HAUTEUR)));
        }
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
     * @param tour entier décrivant le numéro de tour de jeu
     * @param jou Joueur
     * @return tour+1
     */
    public int tourDeJeu(int tour, Joueur jou) {
        this.affichage(jou);
        System.out.println("Début du tour " + tour);

        System.out.println("Action du joueur ...");
        jou.choixaction(this);

        System.out.println("Action des autres personnages ...");
        for (Creature c : creatures) {
            choixactionPNJ(c);
        }

        for (Creature c : creatures) {
            for (Utilisable obj : c.getEffets()) {
                int dur = obj.getDuree();
                if (dur > 0) {
                    obj.setDuree(dur - 1);
                } else {
                    obj.finUtiliser(c);
                }
            }
            if (!c.getEffets().isEmpty()) {
                c.majEffets();
            }
        }
        return tour + 1;
    }

    /**
     * Séquence de l'action d'une créature non joueuse. Celle-ci chosit
     * aléatoirement une action en fonction des possibilités que lui offre sa
     * position
     *
     * @param c Creature non joueuse qui s'apprête à jouer
     */
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

            if (posscombat == 1 && (action.nextInt(100) < 40)) {
                int vic = action.nextInt(adv.size());
                ((Combattant) c).combattre(adv.get(vic));
            } else {
                int sol = action.nextInt(choix.size());
                switch (choix.get(sol)) {

                    case 0:

                        //System.out.println(c.getInventaire());
                        int S = c.getInventaire().size();
                        //System.out.println(S);

                        int choixobj = action.nextInt(S);

                        c.getInventaire().get(choixobj).utiliser(c);
                        //System.out.println("Le PNJ " + c.toString() + "utilise l'objet" + c.getInventaire().get(choixobj).getNom());
                        c.retirerinventaire(c.getInventaire().get(choixobj));

                        break;

                    case 1:
                        c.deplace(this);
                        //System.out.println("Le PNJ c'est déplacé en " + c.getPos());
                        break;
                    case 3:
                        //System.out.println("Le PNJ " + c.toString() + " a décidé de ne rien faire.");
                        break;
                    case 4:
                        int choix2 = action.nextInt(2);
                        /* Ranger*/
                        if (choix2 == 0) {
                            c.ajoutinventaire(u, this);
                            //System.out.println("Le PNJ " + c.toString() + "a ajouté l'objet " + u.getNom() + "à son inventaire" + c.getInventaire());
                        } /*Utiliser directement*/ else {
                            u.utiliser(c);
                            c.getEffets().add(u);

                            objets.remove(u);
                            //System.out.println("Le PNJ " + c.toString() + "a utilisé l'objet " + u.getNom());

                        }
                        break;
                    default:
                        break;

                }
            }
        }

    }

    /**
     * Affiche le monde dans le terminal en décrivant chaque créature par sa
     * lettre initiale, et chaque objet par la lettre "o". Affiche également les
     * caractéristiques du joueur, son inventaire et ses effets.
     *
     * @param joueur Joueur 
     */
    public void affichage(Joueur joueur) {
        String s;
        Boolean r;
        String space = "  ";
        ArrayList<Utilisable> inventaire = joueur.getPers().getInventaire();
        Iterator<Utilisable> listIt = inventaire.iterator();
        ArrayList<Utilisable> effets = joueur.getPers().getEffets();
        Iterator<Utilisable> listE = effets.iterator();
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

                if (r) {
                    for (Utilisable obj : objets) {
                        if ((obj.getPos().getX()) == j && (obj.getPos().getY() == HAUTEUR - 1 - i)) {
                            s = s + obj.getLettre();
                            r = false;
                        }
                    }
                }
                if (r) {
                    for (NuageToxique nuage : nuages) {
                        if ((nuage.getPos().getX()) == j && (nuage.getPos().getY() == HAUTEUR - 1 - i)) {
                            s = s + nuage.getLettre();
                            r = false;
                        }
                    }
                }

                if (r) {
                    s = s + "_";
                }
            }

            switch (i) {
                case 1:
                    s = (s + space + space + "Points de vie : " + joueur.getPers().getPtVie());
                    break;
                case 2:
                    s = (s + space + space + "Points d'attaque : " + joueur.getPers().getDegAtt());
                    break;
                case 3:
                    s = (s + space + space + "Pourcentage de réussite d'attaque : " + joueur.getPers().getPageAtt() + "%");
                    break;
                case 4:
                    s = (s + space + space + "Pourcentage de réussite de contre : " + joueur.getPers().getPagePar() + "%");
                    break;
                case 5:
                    if (joueur.getType().equals("Archer")) {
                        s = (s + space + space + "Nombre de flèches : " + ((Archer) joueur.getPers()).getNbFleches());
                    }
                    break;
                case 6:
                    s = (s + space + space + "INVENTAIRE");
                    break;
                case 13:
                    s = (s + space + space + "EFFETS");
                    break;
            }
            if (i > 6 && listIt.hasNext() && i <= 12) {
                s = (s + space + space + space + "- " + listIt.next().affiche());
            } else if (i == 7) {
                s = (s + space + space + space + "(vide)");
            }
            if (i > 13 && listE.hasNext() && i <= 22) {
                Utilisable ut = listE.next();
                s = (s + space + space + space + "- " + ut.affiche() + " (" + ut.getDuree() + " tours restants)");
            } else if (i == 14) {
                s = (s + space + space + space + "(vide)");
            }

            System.out.println(s);
        }
    }

    /**
     *Pas encore implantée
     * @param tour tour de jeu
     */
    public void progressionNuage(int tour) {

    }

    /**
     * Vérifie si l'ensemble des créatures sont mortes : si c'est le cas,
     * renvoie true
     *
     * @return boolean : true si toutes les créatures non joueuses sont mortes
     */
    public boolean testVictoire() {
        for (Creature c : creatures) {
            if (!c.isEstJoueur()) {
                if (c.isVivant()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Constructeur
     */
    public World() {
        creatures = new ArrayList<>();
        objets = new ArrayList<>();
        nuages = new ArrayList<>();
    }

    /**
     * Save world to database
     *
     * @param connection Connection à la bdd
     * @param gameName String nom de la partie
     * @param saveName String nom de la sauvegarde (devient "Sauvegarde rapide" si null)
     * @param date Timestamp contenant la date temporelle de la sauvegarde
     */
    public void saveToDatabase(Connection connection, String gameName, String saveName, Timestamp date) {
        if (connection != null) {
            try {
                String query = "INSERT INTO monde (param) VALUES ("+tour+")";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.executeUpdate();
                String query1 = "SELECT MAX(id_monde) FROM monde";
                stmt = connection.prepareStatement(query1);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int idMonde = rs.getInt(1);
                for (Utilisable element : objets) {
                    element.saveToDatabase(connection, idMonde);
                }
                for (Creature element : creatures) {
                    element.saveToDatabase(connection, idMonde);
                }
                if (saveName == null) {
                    query = "INSERT INTO sauvegarde (date, nom_partie, est_rapide, id_monde) "
                            + "VALUES (?,?,1,?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setTimestamp(1, date);
                    stmt.setString(2, gameName);
                    stmt.setInt(3, idMonde);
                    stmt.executeUpdate();
                } else {
                    query = "INSERT INTO sauvegarde (date, nom_partie, est_rapide, id_monde, nom) "
                            + "VALUES (?,?,0,?,?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setTimestamp(1, date);
                    stmt.setString(2, gameName);
                    stmt.setInt(3, idMonde);
                    stmt.setString(4, saveName);
                    stmt.executeUpdate();
                }
            } catch (SQLException ex) {
                System.err.println("SQL Exception " + ex.getMessage());
            }
        }
    }

    /**
     * Get world from database
     *
     * @param connection Connection à la bdd
     * @param idSauvegarde Identifiant de la sauvegarde dans la bdd
     * @param joueur instance de Joueur
     */
    public void getFromDatabase(Connection connection, int idSauvegarde, Joueur joueur) {
        if (connection != null) {
            // Remove old data
            try {
                String query1 = "SELECT m.id_monde, nom_partie, param FROM sauvegarde s JOIN monde m ON m.id_monde = s.id_monde WHERE id_sauvegarde=?";
                PreparedStatement stmt = connection.prepareStatement(query1);
                stmt.setInt(1, idSauvegarde);
                ResultSet res = stmt.executeQuery();
                res.next();
                int idMonde = res.getInt(1)+1;
                tour = res.getInt(3);
                String query2 = "SELECT longueur, largeur FROM partie WHERE nom_partie=?";
                stmt = connection.prepareStatement(query2);
                stmt.setString(1, res.getString(2));
                ResultSet rs = stmt.executeQuery();
                rs.next();
                this.setHeightWidth(rs.getInt(1), rs.getInt(2));

                String queryHuma = "SELECT type, pos_x, pos_y, creature.id_creature, est_jouable, nb_fleches, pv, deg_att, page_att, pt_par, page_par, dist_att_max, nom FROM creature JOIN humanoide ON creature.id_creature = humanoide.id_creature WHERE id_monde=?";
                stmt = connection.prepareStatement(queryHuma);
                stmt.setInt(1, idMonde);
                ResultSet humas = stmt.executeQuery();
                System.out.println(idMonde);
                while (humas.next()) {
                    String queryObjets = "SELECT nom, actif,duree, b_deg_att,b_page_par,b_page_att,b_pv FROM objet o JOIN possede p ON o.id_objet = p.id_objet WHERE id_creature = " + humas.getInt(4) + "";
                    ArrayList<Utilisable> Inventaire = new ArrayList<>();
                    ArrayList<Utilisable> Effets = new ArrayList<>();
                    PreparedStatement stmt2 = connection.prepareStatement(queryObjets);
                    ResultSet rsObj = stmt2.executeQuery();
                    while (rsObj.next()) {
                        int actif = rsObj.getInt(2);
                        //System.out.println(rsObj.getInt(7));
                        if(rsObj.getInt(7) != 0){
                                System.out.println("ps activé");
                                PotionSoin ps = new PotionSoin(rsObj.getInt(7), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(ps);
                                } else {
                                    Inventaire.add(ps);
                                }
                        } else if (rsObj.getInt(6)!=0){
                                Nourriture nour = new Nourriture(rsObj.getInt(6), rsObj.getInt(3), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(nour);
                                } else {
                                    Inventaire.add(nour);
                                }
                        } else if (rsObj.getInt(5)!=0){
                                Nourriture n2 = new Nourriture(rsObj.getInt(5), rsObj.getInt(3), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(n2);
                                } else {
                                    Inventaire.add(n2);
                                }
                        }else{
                                Epee ep = new Epee(rsObj.getInt(4), 1, rsObj.getString(1), new Point2D(-1, -1));
                                ep.setDuree(rsObj.getInt(3));
                                if (actif == 1) {
                                    Effets.add(ep);
                                } else {
                                    Inventaire.add(ep);
                                }
                        }
                    }
                    for(int i =0; i<Inventaire.size(); i++){
                        System.out.println(Inventaire.get(i).getNom());
                    }
                    System.out.println("Fin for pour"+humas.getString(13));
                    for(int i =0; i<Effets.size(); i++){
                        System.out.println(Effets.get(i).getNom());
                    }
                    System.out.println("Fin for pour"+humas.getString(13));
                    
                    
                    Personnage Human;
                    switch (humas.getString(1)) {
                        case "Archer":
                            Human = new Archer(humas.getInt(6), humas.getInt(7), humas.getInt(8), humas.getInt(10), humas.getInt(9), humas.getInt(11), humas.getInt(12), humas.getString(13), new Point2D(humas.getInt(2), humas.getInt(3)), Inventaire, Effets);
                            break;
                        case "Guerrier":
                            Epee ep = new Epee();
                            Human = new Guerrier(ep, humas.getInt(7), humas.getInt(8), humas.getInt(10), humas.getInt(9), humas.getInt(11), humas.getInt(12), humas.getString(13), new Point2D(humas.getInt(2), humas.getInt(3)), Inventaire, Effets);
                            break;
                        default:
                            Human = new Paysan(humas.getInt(7), humas.getInt(8), humas.getInt(10), humas.getInt(9), humas.getInt(11), humas.getInt(12), humas.getString(13), new Point2D(humas.getInt(2), humas.getInt(3)), Inventaire, Effets);
                            break;
                    }
                    if (humas.getInt(5) == 1) {
                        joueur.setPers(Human);
                        joueur.setType(humas.getString(1));
                        Human.setEstJoueur(true);
                        Human.setLettre("J");
                        System.out.println("idCreature = "+humas.getInt(4));
                    }
                    this.creatures.add(Human);
                    

                }System.out.println(((Personnage)creatures.get(creatures.size()-1)).getNom());

//System.out.println(creatures.get(creatures.size()-1).getInventaire().get(creatures.get(creatures.size()-1).getInventaire().size()-2));
                                       //System.out.println(creatures.get(creatures.size()-1).getInventaire().get(creatures.get(creatures.size()-1).getInventaire().size()-1));

                String queryMons = "SELECT nom, pos_x, pos_y, creature.id_creature, pv, deg_att, page_att, pt_par, page_par, dist_att_max FROM creature JOIN monstre ON creature.id_creature = monstre.id_creature WHERE id_monde=?";
                stmt = connection.prepareStatement(queryMons);
                stmt.setInt(1, idMonde);
                ResultSet mons = stmt.executeQuery();
                while (mons.next()) {
                    String nom = mons.getString(1);
                    Point2D pos = new Point2D(mons.getInt(2), mons.getInt(3));
                    int idCreature = mons.getInt(4);
                    int pv = mons.getInt(5);
                    int degAtt = mons.getInt(6);
                    int pageAtt = mons.getInt(7);
                    int ptPar = mons.getInt(8);
                    int pagePar = mons.getInt(9);
                    int distAttMax = mons.getInt(10);

                    String queryObjets = "SELECT nom, actif,duree, b_deg_att,b_page_par,b_page_att,b_pv FROM objet o JOIN possede p ON o.id_objet = p.id_objet WHERE id_creature = " + idCreature + "";
                    ArrayList<Utilisable> Inventaire = new ArrayList<>();
                    ArrayList<Utilisable> Effets = new ArrayList<>();
                    PreparedStatement stmt2 = connection.prepareStatement(queryObjets);
                    ResultSet rsObj = stmt2.executeQuery();
                    while (rsObj.next()) {
                        int actif = rsObj.getInt(2);
                        switch (rsObj.getString(1)) {
                            case "Potion de Soin":
                                PotionSoin ps = new PotionSoin(rsObj.getInt(7), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(ps);
                                } else {
                                    Inventaire.add(ps);
                                }
                                break;
                            case "Gateau":
                                Nourriture nour = new Nourriture(rsObj.getInt(6), rsObj.getInt(3), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(nour);
                                } else {
                                    Inventaire.add(nour);
                                }
                                break;
                            case "Legumes":
                                Nourriture n2 = new Nourriture(rsObj.getInt(5), rsObj.getInt(3), 1, rsObj.getString(1), new Point2D(-1, -1));
                                if (actif == 1) {
                                    Effets.add(n2);
                                } else {
                                    Inventaire.add(n2);
                                }
                                break;
                            default:
                                Epee ep = new Epee(rsObj.getInt(4), 1, rsObj.getString(1), new Point2D(-1, -1));
                                ep.setDuree(rsObj.getInt(3));
                                if (actif == 1) {
                                    Effets.add(ep);
                                } else {
                                    Inventaire.add(ep);
                                }
                                break;
                        }}
                        Monstre Monster;
                        switch (nom) {
                            case "loup":
                                Monster = new Loup(pv, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, Inventaire, Effets);
                                Monster.setLettre("W");
                                break;
                            default:
                                Monster = new Lapin(pv, degAtt, ptPar, pageAtt, pagePar, pos, distAttMax, Inventaire, Effets);
                                Monster.setLettre("L");
                                break;
                        }
                        this.creatures.add(Monster);
                    }
                    String queryObj = "SELECT nom, pos_x, pos_y, b_deg_att, b_page_par, b_page_att, b_pv, duree FROM comporte_obj c JOIN objet ON objet.id_objet = c.id_objet WHERE id_monde="+idMonde;
                    PreparedStatement stmtObjs = connection.prepareStatement(queryObj);
                    ResultSet resObjs = stmtObjs.executeQuery();
                    while (resObjs.next()) {
                        String nomO = resObjs.getString(1);
                        Point2D posO = new Point2D(resObjs.getInt(2), resObjs.getInt(3));
                        int bDegAtt = resObjs.getInt(4);
                        int bPagePar = resObjs.getInt(5);
                        int bPageAtt = resObjs.getInt(6);
                        int bPV = resObjs.getInt(7);
                        int dureeO = resObjs.getInt(8);

                        Utilisable obj;
                        switch (resObjs.getString(1)) {
                            case "Potion de soin":
                                obj = new PotionSoin(bPV, 1, nomO, posO);
                                break;
                            case "Legumes":
                                obj = new Nourriture(bPagePar, dureeO, 1, nomO, posO);
                                break;
                            case "Gateau":
                                obj = new Nourriture(bPageAtt, dureeO, 1, nomO, posO);
                                break;
                            default:
                                obj = new Epee(bDegAtt, 1, nomO, posO);
                                ((Epee) obj).setDuree(dureeO);
                        }
                        this.objets.add(obj);
                    }
                

            } catch (SQLException ex) {
                System.err.println("SQL Exception " + ex.getMessage());
            }
        
    }}

    private void setHeightWidth(int aInt, int aInt0) {
        HAUTEUR = aInt;
        LARGEUR = aInt0;
    }

}
