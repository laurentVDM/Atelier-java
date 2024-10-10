package domaine;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrajetTest {

  private Trajet trajet;
  @BeforeEach
  void setUp() {
    trajet= new Trajet("fgf", LocalDate.now().plusDays(1),"BIBI","LOLO");
  }

  @Test
  void peutAjouter() {
    assertThrows(IllegalArgumentException.class,()->trajet.peutAjouter(null));
  }

  @Test
  void peutAjouterFalseVilleIncorecte() {
    Caisse caisse= new Caisse("ff",LocalDate.now().plusDays(5),"TOTO","LOLO",5);
    assertFalse(trajet.peutAjouter(caisse));
  }
  @Test
  void peutAjoutertrue() {
    Caisse caisse= new Caisse("ff",LocalDate.now().plusDays(5),"BIBI","LOLO",5);
    assertTrue(trajet.peutAjouter(caisse));
  }
}