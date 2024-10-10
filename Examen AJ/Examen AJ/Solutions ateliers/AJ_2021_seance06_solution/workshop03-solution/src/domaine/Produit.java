package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Produit implements Cloneable {

    private final String nom;
    private final String rayon;
    private final String marque;

    private SortedMap<LocalDate, Prix> historiquePrix = new TreeMap<LocalDate, Prix>(Comparator.reverseOrder());

    public Produit(String nom, String marque, String rayon) {
        Util.checkString(nom);
        Util.checkString(marque);
        Util.checkString(rayon);
        this.nom = nom;
        this.marque = marque;
        this.rayon = rayon;
    }

    public String getMarque() {
        return marque;
    }

    public String getNom() {
        return nom;
    }

    public String getRayon() {
        return rayon;
    }

    /**
     * ajoute un prix dans l'historique si la date n'est pas déjà présente
     *
     * @param date la date à partir duquel les prix s'appliquent
     * @param prix
     * @throws DateDejaPresenteException si la date est déjà présente dans l'historique
     * @throws IllegalArgumentException  en cas de paramètre invalide
     */
    public void ajouterPrix(LocalDate date, Prix prix) throws DateDejaPresenteException {
        Util.checkObject(date);
        Util.checkObject(prix);
        if (historiquePrix.containsKey(date)) throw new DateDejaPresenteException();
        //Il faut cloner le prix stocké pour éviter que la modification du prix qui était passée en
        // paramètre modifie le prix du produit
        historiquePrix.put(date, prix.clone());
    }

    /**
     * Renvoie le prix appliqué à une date donnée
     *
     * @param date la date pour laquelle il faut retrouver le prix
     * @return le prix
     * @throws PrixNonDisponibleException s'il n'y a pas de prix disponible pour cette date
     * @throws IllegalArgumentException   en cas de paramètre invalide
     */
    public Prix getPrix(LocalDate date) throws PrixNonDisponibleException {
        Util.checkObject(date);
        for (Entry<LocalDate, Prix> entry : historiquePrix.entrySet()) {
            //Il faut cloner le prix renvoyé afin que, si le prix renvoyé est modifié,
            // il n'est pas modifié au niveau du produit
            if (!entry.getKey().isAfter(date)) return entry.getValue().clone();
        }
        throw new PrixNonDisponibleException();
    }

    @Override
    public String toString() {
        String detail = "Nom : " + nom + "\nMarque : " + marque + "\nRayon : " + rayon + "\nHistorique des prix :";
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        for (Entry<LocalDate, Prix> entry : historiquePrix.entrySet()) {
            detail += "\n" + formater.format(entry.getKey()) + " :\n" + entry.getValue();
        }

        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit produit = (Produit) o;

        if (!nom.equals(produit.nom)) return false;
        if (!rayon.equals(produit.rayon)) return false;
        return marque.equals(produit.marque);
    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + rayon.hashCode();
        result = 31 * result + marque.hashCode();
        return result;
    }

    @Override
    public Produit clone() {

        try {
            Produit clone = (Produit) super.clone();
            //Comme le clone d'Object est superficiel, il faut aussi cloner la map
            // Comme la classe Produit ne possède aucune méthode permettant de modifier les prix qu'elle
            // stocke et que les prix qu'elle renvoie sont des clones,
            // il est inutile de cloner les prix contenus dans la map.
            clone.historiquePrix = (SortedMap<LocalDate, Prix>) ((TreeMap<LocalDate, Prix>) historiquePrix).clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
