package domaine;


import exceptions.QuantiteNonAutoriseeException;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static util.Util.*;
import static util.Util.checkPositive;

public class Prix implements Cloneable {

    private final Promo typePromo;
    private final double valeurPromo;

    //TODO Ajouter l'attribut pour garder les différents prix correspondant à une quantité minimale et l'initialiser
    private SortedMap<Integer, Double> mapDePrix = new TreeMap<Integer, Double>();

    public Prix() {
        typePromo = null;
        valeurPromo = 0;
    }

    public Prix(Promo promo, double valeurPromo) {
        checkObject(promo);
        checkPositiveOrZero(valeurPromo);
        this.typePromo = promo;
        this.valeurPromo = valeurPromo;
    }


    public Promo getTypePromo() {
        return typePromo;
    }

    public double getValeurPromo() {
        return valeurPromo;
    }

    /**
     * Cette méthode permet de définir le prix unitaire correspondant à une quantité minimale.
     * S'il existe déjà un prix correspondant à la quantité, il sera remplacé.
     *
     * @param quantiteMin
     * @param valeur      le prix unitaire à partir de cette quantité
     * @throws IllegalArgumentException en cas de quantité négative ou nulle ou en cas de valeur négative ou nulle
     */
    public void definirPrix(int quantiteMin, double valeur) {
        checkPositive(quantiteMin);
        checkPositive(valeur);
        mapDePrix.put(quantiteMin, valeur);
    }


    /**
     * Cette méthode renvoie le prix à appliquer selon la quantité achetée
     *
     * @param quantite la quantité achetée
     * @return le prix unitaire pour cette quantité
     * @throws QuantiteNonAutoriseeException si la quantité est inférieure à la quantité minimale autorisée
     * @throws IllegalArgumentException      en cas de quantité négative ou nulle
     */
    public double getPrix(int quantite) throws QuantiteNonAutoriseeException {
        checkPositive(quantite);
        double prix = 0;
        for (Map.Entry<Integer, Double> entry : mapDePrix.entrySet()
        ) {
            if (quantite >= entry.getKey())
                prix = entry.getValue();
        }

        if (prix == 0) throw new QuantiteNonAutoriseeException();
        return prix;
    }


    @Override
    public String toString() {
        String detail = "";
        if (typePromo != null) detail += "Promo : " + typePromo + " - " + valeurPromo + "\n";
        for (Map.Entry<Integer, Double> entry : mapDePrix.entrySet()) {
            detail += entry.getValue() + " euros à partir de " + entry.getKey() + " unités\n";
        }
        return detail;
    }

    @Override
    public Prix clone() {

        try {
            Prix clone = (Prix) super.clone();
            //Comme le clone d'object est superficiel, il faut aussi cloner la map afin d'éviter que
            // le prix cloné et le clone modifient la même map.
            clone.mapDePrix = (SortedMap<Integer, Double>) ((TreeMap<Integer, Double>) this.mapDePrix).clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
