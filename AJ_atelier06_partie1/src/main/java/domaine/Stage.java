package domaine;

import java.util.Set;

public interface Stage {

  String getIntitule();

  String getLieu();

  int getNumeroDeSemaine();

  Sport getSport();

  boolean enregistrerMoniteur(Moniteur moniteur);

  boolean supprimerMoniteur();

  Moniteur getMoniteur();

  boolean ajouterEnfant(Enfant enfant);

  boolean supprimerEnfant(Enfant enfant);

  boolean contientEnfant(Enfant enfant);

  int nombreDEnfants();

  Set<Enfant> enfants();
}
