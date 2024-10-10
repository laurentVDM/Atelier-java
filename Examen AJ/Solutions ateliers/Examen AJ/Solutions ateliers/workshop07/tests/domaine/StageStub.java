package domaine;

import java.util.Set;

public class StageStub implements Stage {
    private Sport sport;
    private int numeroDeSemaine;
    private Moniteur moniteur;


    public StageStub(Sport sport, int numeroDeSemaine, Moniteur moniteur) {
        this.sport = sport;
        this.numeroDeSemaine = numeroDeSemaine;
        this.moniteur = moniteur;
    }

    @Override
    public String getIntitule() {
        return null;
    }

    @Override
    public String getLieu() {
        return null;
    }

    @Override
    public int getNumeroDeSemaine() {
        return numeroDeSemaine;
    }

    @Override
    public Sport getSport() {
        return sport;
    }

    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return true;
    }

    @Override
    public boolean supprimerMoniteur() {
        return true;
    }

    @Override
    public Moniteur getMoniteur() {
        return moniteur;
    }

    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public int nombreDEnfants() {
        return 0;
    }

    @Override
    public Set<Enfant> enfants() {
        return null;
    }
}
