package domaine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoniteurImplTest {

  private Moniteur moniteur1;
  private StageStub stage1;
  private SportStub sport1;

  private Moniteur moniteur2;
  private StageStub stage2;
  private SportStub sport2;


  @BeforeEach
  void setUp() {
    //moniteur avec un stage
    moniteur1 = new MoniteurImpl("didier");
    sport1 = new SportStub(true);
    stage1 = new StageStub(sport1,1, moniteur1);
    //stage1.enregistrerMoniteur(moniteur1);
    moniteur1.ajouterStage(stage1);

    //moniteur sans stage
    moniteur2 = new MoniteurImpl("kevin");
    sport2 = new SportStub(true);
    stage2 = new StageStub(sport2,2,null);

  }

  @DisplayName("test ajouterStage avec null en parametre")
  @Test
  void ajouterStageNull() {
    assertThrows(IllegalArgumentException.class, ()-> moniteur1.ajouterStage(null));
  }

  @DisplayName("test ajouterStage ou le moniteur a deja le stage")
  @Test
  void ajouterStageMoniteurDejaPresent() {
    assertEquals(false, moniteur1.ajouterStage(stage1));
  }

  @DisplayName("test ajouterStage quand il est deja occupe cette semaine")
  @Test
  void ajouterStageMoniteurDejaOccupe() {
    StageStub stageTemp = new StageStub(sport2,1,null);
    assertEquals(false, moniteur1.ajouterStage(stageTemp));
  }

  @DisplayName("test ajouterStage avec bons parametres")
  @Test
  void ajouterStageMoniteur() {
    assertEquals(true, moniteur2.ajouterStage(stage2));
  }



  @Test
  void supprimerStage() {
  }


}