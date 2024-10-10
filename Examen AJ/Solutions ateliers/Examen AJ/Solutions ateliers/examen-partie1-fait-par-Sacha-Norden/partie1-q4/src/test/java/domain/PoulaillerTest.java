package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PoulaillerTest {

  private Lot lot;

  private Poulailler poulailler;

  @BeforeEach
  void setUp() {
    poulailler = new Poulailler("1", 12);
    lot = Mockito.mock(Lot.class);
    Mockito.when(lot.signalerAffectation()).thenReturn(false);
  }

  @Test
  @DisplayName("TestAjouterLotFalse")
  void ajouterLot() {
    assertAll(
        ()-> assertFalse(poulailler.ajouterLot(lot)),
        ()-> assertFalse(poulailler.getTousLesLots().contains(lot))
    );
  }
}