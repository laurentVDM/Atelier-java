package usecase;

import domaine.Prix;
import domaine.Produit;
import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.ProduitNonPresentException;
import util.Util;

import java.time.LocalDate;
import java.util.*;

import static util.Util.checkObject;

public class ListeProduits {


    //La classe produit n'implémentant pas Comparable, il faut passer un Comparator lors de la création du TreeSet
    // Ici, exemple via une classe anonyme;
    private SortedSet<Produit> produits = new TreeSet<Produit>(new Comparator<Produit>() {
        @Override
        public int compare(Produit o1, Produit o2) {
            int compare = o1.getNom().compareTo(o2.getNom());
            if (compare != 0) return compare;
            compare = o1.getMarque().compareTo(o2.getMarque());
            if (compare != 0) return compare;
            return o1.getRayon().compareTo(o2.getRayon());
        }
    });

    //Création du comparator afin de définir l'ordre sur les produits : ici un exemple avec une expression lambda
    /*private static Comparator<Produit> comparateurProduits = (p1, p2) -> {
        int compare = p1.getNom().compareTo(p2.getNom());
        if (compare != 0) return compare;
        compare = p1.getMarque().compareTo(p2.getMarque());
        if (compare != 0) return compare;
        return p1.getRayon().compareTo(p2.getRayon());
    };*/

    private ListeProduits.ProduitIterator produitIterator;

    /**
     * Cette méthode renvoie vraie si le produit passé en paramètre est présent dans la liste
     *
     * @param p le produit recherché
     * @return true si le produit est dans la liste, false sinon
     * @throws IllegalArgumentException en cas de paramètre invalide
     */
    public boolean contient(Produit p) {
        Util.checkObject(p);
        return produits.contains(p);
    }

    /**
     * Cette méthode ajoute le produit si celui-ci ne s'y trouve pas encore
     *
     * @param p le produit à ajouter
     * @return true si le produit a pu être ajouté, false sinon
     * @throws IllegalArgumentException en cas de paramètre invalide
     */
    public boolean ajouterProduit(Produit p) {
        checkObject(p);
        //Inutile de tester si le produit est présent avant de l'ajouter car c'est déjà fait au niveau du SortedSet
        //On ajoute une copie du produit pour pas qu'on puisse le modifier de l'extérieur une fois ajouté.
        return produits.add(p.clone());
    }

    /**
     * Cette méthode supprime le produit si celui-ci est présent dans la liste
     *
     * @param p le produit à supprimer
     * @return true si le produit a pu être supprimé, false sinon
     * @throws IllegalArgumentException en cas de paramètre invalide
     */
    public boolean supprimerProduit(Produit p) {
        checkObject(p);
        return produits.remove(p);
    }


    /**
     * Cette méthode trouve le produit correspondant aux paramètres s'il existe et le renvoie
     *
     * @param nom    le nom du produit recherché
     * @param marque la marque du produit recherché
     * @param rayon  le rayon du produit recherché
     * @return le produit s'il existe, null sinon
     */
    public Produit trouverProduit(String nom, String marque, String rayon) {
        Produit produit = new Produit(nom, marque, rayon);
        for (Produit p : produits) {
            //On renvoie une copie du produit pour pas qu'on puisse le modifier de l'extérieur.
            if (p.equals(produit)) return p.clone();
        }
        return null;
    }

    /**
     * Cette méthode renvoie un itérateur permettant de parcourir les produits
     * Si l'on tente d'appeler la méthode remove() sur l'itérateur renvoyé, cela lance une
     * UnsupportedOperationException (comportement par défaut de la méthode remove() d'un Iterator).
     * TIPS ? voir p.18 de la théorie "collections" (wrappers méthodes)
     *
     * @return un itérateur sur les produits
     */

    public Iterator<Produit> produits() {
        //L'itérateur est défini en classe interne ain de renvoyer des clones des produits.

        return new ProduitIterator();//Collections.unmodifiableCollection( produits).iterator();
    }

    public class ProduitIterator implements Iterator<Produit> {
        private Iterator<Produit> it = produits.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Produit next() {
            return it.next().clone();
        }

    }


    /**
     * Cette méthode ajoute un prix à appliquer à partir d'une certaine date au produit
     *
     * @param prod le produit auquel s'applique le prix
     * @param date la date à partir de laquelle s'applique le prix
     * @param prix le prix à appliquer
     * @throws ProduitNonPresentException si le produit n'est pas dans la liste
     * @throws DateDejaPresenteException  s'il y a déjà un prix pour cette date pour ce produit
     * @throws IllegalArgumentException   en cas de paramètre invalide ou si le produit n'est pas présent
     */
    public void ajouterPrix(Produit prod, LocalDate date, Prix prix) throws DateDejaPresenteException, ProduitNonPresentException {
        checkObject(prix);
        checkObject(date);
        //Il faut récupérer le produit dans le SortedSet afin d'ajouter le prix au bon produit
        Produit produit = trouverProduit(prod);
        //Inutile de cloner le prix car c'est déjà fait dans produit
        produit.ajouterPrix(date, prix);
    }

    /**
     * Cette méthode retrouve et renvoie le prix appliqué à un certain produit à une date donnée.
     *
     * @param prod le produit dont on cherche le prix
     * @param date la date pour laquelle on veut connaître le prix
     * @throws ProduitNonPresentException si le produit n'est pas dans la liste
     * @throws PrixNonDisponibleException s'il n'y a de prix pour cette date pour ce produit
     * @throws IllegalArgumentException   en cas de paramètre invalide ou si le produit n'est pas présent
     */
    public Prix trouverPrix(Produit prod, LocalDate date) throws ProduitNonPresentException, PrixNonDisponibleException {
        Produit produit = trouverProduit(prod);
        //intutile de cloner car c'est déjé fait dans Produit
        return produit.getPrix(date);
    }

    //Permet de retrouver le produit dans la liste correspondant au produit passé en paramètre
    // Cette méthode permet d'éviter de la duplication de code
    private Produit trouverProduit(Produit prod) throws ProduitNonPresentException {
        checkObject(prod);
        Produit produit = null;
        for (Produit p : produits) {
            if (p.equals(prod))
                return p;
        }

        if (produit == null)
            throw new ProduitNonPresentException("le produit ne se trouve pas dans la liste des produits.");
        return null; // jamais appelé normalement
    }


    public String toString() {
        String detail = "";
        for (Produit p : produits) {
            detail += p + "\n----------------------------------------------------------------------\n";
        }
        return detail;
    }

}
