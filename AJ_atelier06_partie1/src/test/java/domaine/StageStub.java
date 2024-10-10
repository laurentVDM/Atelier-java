package domaine;

import java.util.Set;

public class StageStub implements Stage {

  private Sport sport;
  private int numeroSemaine;
  private Moniteur moniteur;

  public StageStub(Sport sport, int numeroSemaine, Moniteur moniteur) {
    this.sport = sport;
    this.numeroSemaine = numeroSemaine;
    this.moniteur = moniteur;
  }
/*private void amenerALEtat(int etat, Moniteur moniteur ){
    if(etat <= 0 ){
      return;
    }
    int nbrSages = moniteur.nombreDeStages();
    if(nbrSages > etat){
      return;
    }
    for(int i=nbrSages; i<etat; i++){
      StageStub stage = new StageStub();
      moniteur.ajouterStage(stage);
    }
  }
  */

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
    return this.numeroSemaine;
  }

  @Override
  public Sport getSport() {
    return this.sport;
  }

  @Override
  public boolean enregistrerMoniteur(Moniteur moniteur) {
    return false;
  }

  @Override
  public boolean supprimerMoniteur() {
    return false;
  }

  @Override
  public Moniteur getMoniteur() {
    return this.moniteur;
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
