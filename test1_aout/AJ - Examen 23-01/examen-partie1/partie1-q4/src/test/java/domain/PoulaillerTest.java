package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PoulaillerTest {
  private Lot lot;
  private Poulailler poulailler;
  @BeforeEach
  void setUp() {
    poulailler = new Poulailler("1",12);
    lot = Mockito.mock(Lot.class);
    Mockito.when(poulailler.ajouterLot(lot)).thenReturn(false);
  }

  @Test
  @DisplayName("Dans le setup de votre test, veuillez notamment créer un poulailler ne contenant\n"
      + "aucun lot. Veuillez ensuite vérifier, quand vous tentez d’ajouter un lot pour lequel on a\n"
      + "signalé son affectation, que l’ajout échoue. Pensez à vérifier aussi, une fois l’échec de la\n"
      + "tentative d’ajout d’un lot, qu’aucun lot ne soit présent au niveau du poulailler")
  void testAjouterLot1() {
    assertAll(
        ()-> assertFalse(poulailler.ajouterLot(lot)),
        ()-> assertFalse(poulailler.getTousLesLots().contains(lot))
    );
  }
}
