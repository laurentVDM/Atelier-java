package domaine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrixTest {

  private Prix prixAucune;
  private Prix prixPub;
  private Prix prixSolde;
  @BeforeEach
  void setUp() {
    prixAucune = new Prix();
    prixPub = new Prix(TypePromo.PUB, 10);
    prixSolde = new Prix(TypePromo.SOLDE, 30);
    prixAucune.definirPrix(1,20);
    prixAucune.definirPrix(10,10);
    prixPub.definirPrix(3,15);
  }

  @Test
  @DisplayName("Vérifier que le constructeur lance une IllegalArgumentException si la promo\n"
      + "passée en paramètre est null. ")
  void testPrix1() {
    assertThrows(
        IllegalArgumentException.class, () -> new Prix(null, 15)
    );
  }

  @Test
  @DisplayName("Vérifier que le constructeur lance une IllegalArgumentException si la valeur passée\n"
      + "en paramètre est < 0.")
  void testPrix2() {
    assertThrows(
        IllegalArgumentException.class, () -> new Prix(TypePromo.SOLDE, -1)
    );
  }
//------------------------------------
  @Test
  @DisplayName("Vérifier que la valeur de la promo est initialisée à 0 lors de l’instanciation d’un prix au\n"
      + "moyen du constructeur sans paramètre.")
  void testGetValeurPromo1() {
    assertEquals(0, prixAucune.getValeurPromo());
  }

  @Test
  @DisplayName("Vérifier que la valeur de la promo correspond bien à celle passée en paramètre du\n"
      + "constructeur")
  void testGetValeurPromo2() {
    assertEquals(10, prixPub.getValeurPromo());
  }
//--------------------------------------
  @Test
  @DisplayName("Vérifier que la méthode definirPrix lance une IllegalArgumentException si le\n"
      + "paramètre quantité est 0 ou négatif.")
  void testDefinirPrix1() {
    assertThrows(IllegalArgumentException.class, ()-> prixPub.definirPrix(0, 10));
  }
//---------------------------------
@Test
@DisplayName("Vérifier que la méthode lance une IllegalArgumentException si le paramètre\n"
    + "quantité est négatif ou nul.")
void testGetPrix1() {
  assertThrows(IllegalArgumentException.class, ()-> prixPub.getPrix(0));
}
@Test
@DisplayName("Testez les prix renvoyés par la méthode getPrix pour l’attribut prixAucune : faire le\n"
    + "test pour 1 unité, 5 unités, 9 unités, 10 unités, 15 unités, 20 unités et 25 unités.")
void testGetPrix2() {
    assertAll(
        ()->assertEquals(20, prixAucune.getPrix(1)),
        ()->assertEquals(20, prixAucune.getPrix(5)),
        ()->assertEquals(20, prixAucune.getPrix(9)),
        ()->assertEquals(10, prixAucune.getPrix(10)),
        ()->assertEquals(10, prixAucune.getPrix(15)),
        ()->assertEquals(10, prixAucune.getPrix(20)),
        ()->assertEquals(10, prixAucune.getPrix(25))
    );
}
  //---------------------------------
  @Test
  void getTypePromo() {
  }





}