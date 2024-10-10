package domaine;

import java.util.List;

public interface Moniteur {

  String getNom();

  boolean estLibre(int numeroDeSemaine);

  boolean ajouterStage(Stage stage);

  boolean supprimerStage(Stage stage);

  boolean contientStage(Stage stage);

  int nombreDeStages();

  List<Stage> stages();
}
