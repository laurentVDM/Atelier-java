package domaine;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Camion  {
    private String immatriculation;
    private int nbMaxCaisses;
    private int chargeMaximale;

    private SortedMap<LocalDate,Trajet> trajets = new TreeMap<>();

    public Camion(String immatriculation, int nbMaxCaisses, int chargeMaximale) {
        this.immatriculation = immatriculation;
        this.nbMaxCaisses = nbMaxCaisses;
        this.chargeMaximale = chargeMaximale;
    }

    /**
     * ajoute un trajet pour le camion
     * @param trajet le trajet à ajouter
     * @return false
     * la date du jour n'est pas antérieure à la date du trajet
     * - s'il y a déjà un trajet prévu ce jour-là pour le camion
     * - s'il y a déjà un trajet prévu la veille et que la ville d'arrivée de ce trajet ne correspond
     * pas à la ville de départ du trajet à ajouter
     * - s'il y a déjà un trajet prévu le lendemain et que la ville de départ de ce trajet ne correspond
     * pas à la ville d'arrivée du trajet à ajouter
     * - s'il y a trop de palettes à transporter par rapport à la capacité du camion
     * - si le poids total à transporter est supérieur à la charge maximale du camion
     */
    public boolean ajouterTrajet(Trajet trajet){
        LocalDate dateActuelle = LocalDate.now();
        if (!dateActuelle.isBefore(trajet.getDate())) return false;
        if (this.chargeMaximale < trajet.calculerPoidsTotal()) return false;
        if (this.nbMaxCaisses < trajet.nbCaisses()) return false;

        if(trajets.containsKey(trajet.getDate()))return false;
        if(trajets.get(trajet.getDate().minusDays(1))!=null){
            if(!trajets.get(trajet.getDate().minusDays(1)).getVilleArrivee().equals(trajet.getVilleDepart())){
                return false;
            }
        }
        if(trajets.get(trajet.getDate().plusDays(1))!=null){
            if(!trajets.get(trajet.getDate().plusDays(1)).getVilleDepart().equals(trajet.getVilleArrivee())){
                return false;
            }
        }
        if(trajets.put(trajet.getDate(),trajet)==null)return true;
        return false;
    }

    /**
     * renvoie, s'il existe, le trajet prévu à la date passée en paramètre
     * @param dateTrajet la date du trajet recherché
     * @return le trajet prévu à cette date s'il existe et null sinon
     */
    public Trajet trouverTrajet(LocalDate dateTrajet){
        return trajets.get(dateTrajet);
    }


    /**
     * recherche le premier trajet, dont la date est ultérieure à la date du jour, auquel la caisse peut être
     * ajoutée et, s'il en a un, lui ajoute la caisse.
     * @date date du trajet recherché pour ajouter la caisse
     * @param caisse caisse à ajouter
     * @return false s'il n'y a pas de trajet auquel la caisse peut être ajoutée
     */
    public boolean ajouterCaisse(Caisse caisse){
        Trajet trajet = rechercherTrajet(caisse);
        if (trajet == null) return false;
        return trajet.ajouter(caisse);
    }

    /**
     * renvoie, s'il existe, le premier trajet, dont la date est ultérieure à la date du jour, auquella caisse
     * peut lui être ajoutée
     * @param caisse
     * @return null s'il n'y a pas de trajet auquel la caisse peut être ajoutée
     */
    private Trajet rechercherTrajet(Caisse caisse) {
        Trajet trajet = null;

        for (Entry<LocalDate,Trajet> trajetPrevu : trajets.entrySet()){
            if (trajetPrevu.getValue().peutAjouter(caisse)
                && trajetPrevu.getValue().nbCaisses() < nbMaxCaisses
                && trajetPrevu.getValue().calculerPoidsTotal() + caisse.getPoids() <= chargeMaximale) {
                if (trajet == null ||trajetPrevu.getKey().isBefore(trajet.getDate())) {
                    trajet = trajetPrevu.getValue();
                }
            }
        }
        return trajet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camion camion = (Camion) o;
        return Objects.equals(immatriculation, camion.immatriculation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(immatriculation);
    }

}
