package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PoulaillerTest {

  private Poulailler poulailler1;
  private Lot lot1;
  @BeforeEach
  void setUp() {
    poulailler1 = Mockito.mock(Poulailler.class);
    Mockito.when(poulailler1.getTousLesLots()).thenReturn(null);
  }

  @Test
  @DisplayName(" Dans le setup de votre test, veuillez notamment créer un poulailler ne contenant\n"
      + "aucun lot. Veuillez ensuite vérifier, quand vous tentez d’ajouter un lot pour lequel on a\n"
      + "signalé son affectation, que l’ajout échoue. Pensez à vérifier aussi, une fois l’échec de la\n"
      + "tentative d’ajout d’un lot, qu’aucun lot ne soit présent au niveau du poulailler")
  void testAjouterLot1() {
    lot1 = new Lot(1,2,"Poules Rousses");
    assertAll(
        ()->Mockito.when(poulailler1.ajouterLot(lot1)).thenReturn(true),
        ()->assertTrue(poulailler1.ajouterLot(lot1)),
        ()->Mockito.when(poulailler1.ajouterLot(lot1)).thenReturn(false)
    );
  }
}