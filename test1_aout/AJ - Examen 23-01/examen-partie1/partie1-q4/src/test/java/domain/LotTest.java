package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotTest {

  private Lot lot1;
  private Lot lot2;
  @BeforeEach
  void setUp() {
    lot1 = new Lot(1,2,"Poules rousses");

    lot2 = new Lot(2,2,"Poules rousses");
    lot2.signalerAffectation();
  }

  @Test
  @DisplayName("vérifiez que si vous appelez le constructeur en passant une quantité de volailles plus\n"
      + "petite que 1, une IllegalArgumentException est lancée.")
  void testLot1() {
    assertThrows(IllegalArgumentException.class, ()->new Lot(0,2,"Poules rousses"));
  }
  @Test
  @DisplayName("Vérifiez que la méthode signalerAffectation renvoie true quand on l’appelle sur\n"
      + "un lot dont on n’a pas encore signalé d’affectation.")
  void testSignalerAffectation1() {
    assertTrue(lot1.signalerAffectation());
  }

  @Test
  @DisplayName("Vérifiez que la méthode signalerAffectation renvoie false quand on l’appelle\n"
      + "sur un lot pour lequel on a déjà signalé une affectation.")
  void testSignalerAffectation2() {
    assertFalse(lot2.signalerAffectation());
  }
}