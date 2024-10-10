package domaine;

import java.util.Set;

public interface Sport {

  String getIntitule();

  boolean ajouterMoniteur(Moniteur moniteur);

  boolean supprimerMoniteur(Moniteur moniteur);

  boolean contientMoniteur(Moniteur moniteur);

  Set<Moniteur> moniteurs();
}
