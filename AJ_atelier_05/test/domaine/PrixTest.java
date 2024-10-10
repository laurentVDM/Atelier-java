package domaine;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrixTest {

  private Prix prixAucune;
  private Prix prixPub;
  private Prix prixSolde;

  @BeforeEach
  void setUp() {
    prixAucune  = new Prix();
    prixPub = new Prix(TypePromo.PUB,1);
    prixSolde = new Prix(TypePromo.SOLDE, 1);

    prixAucune.definirPrix(1,20);
    prixAucune.definirPrix(10,10);

    prixPub.definirPrix(3,10);
  }

//  -----------------

  @DisplayName("Test du constructeur avec en paramètre une promo null")
  @Test
  void testConstructeurPromoNull() {
    assertThrows(IllegalArgumentException.class, () -> new Prix(null, 10));
  }

  @DisplayName("Test du constructeur avec en paramètre une promo < 0")
  @Test
  void testConstructeurPromoNegatif() {
    assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, -1));
  }

//  -----------------

  @DisplayName("Test si la valeur de la promo est initialisée à 0 lors de l’instanciation d’un prix au\n"
      + "  moyen du constructeur sans paramètre")
  @Test
  void testPromoSansParam() {
    assertEquals(0, prixAucune.getValeurPromo());
  }

  @DisplayName("Test la valeur de promo correspond a celle passée en param")
  @Test
  void testPromoCorrespond() {
    assertEquals(1, prixPub.getValeurPromo());
  }

  @DisplayName("Test type de promo est null lors de l’instanciation d'un prix sans parametres")
  @Test
  void testPromoNullPourPrixSansParam() {
    assertEquals(null, prixAucune.getTypePromo());
  }

  @DisplayName("Test typePromo correspond a celle passée en param")
  @Test
  void testConstructeurTypePromoCorrespond() {
    assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo());
  }

//  -----------------

  @DisplayName("Test methode definirPrix lance IllegalArgumentException si quantite <= 0")
  @Test
  void testDefiniPrixQuantitePasPositif () {
    assertThrows(java.lang.IllegalArgumentException.class, ()-> prixPub.definirPrix(0,10));
  }

  @DisplayName("Test methode definirPrix lance IllegalArgumentException si quantite <= 0")
  @Test
  void testDefiniPrixValeurPasPositif () {
    assertThrows(java.lang.IllegalArgumentException.class, ()-> prixPub.definirPrix(1,0));
  }

  @DisplayName("Test methode definirPrix remplace l'ancien prix de prixAucun")
  @Test
  void testDefiniPrixNouvelleValeurPrixAucun () {
    prixAucune.definirPrix(10,6);
    assertEquals(  6 , prixAucune.getPrix(10));
  }

//  -----------------

  @DisplayName("Test getPrix lance une IllegalArgumentException si quantité est négatif ou nul.")
  @Test
  void testGetPrixQuantiteNegatif() {
    assertThrows(IllegalArgumentException.class, ()-> prixPub.getPrix(0));
  }

  @DisplayName("Test les prix renvoyés par la méthode getPrix pour l’attribut prixAucune : faire le\n"
      + "test pour 1 unité, 5 unités, 9 unités, 10 unités, 15 unités, 20 unités et 25 unités.")
  @Test
  void testGetPrixAvecPlusieursUnites() {
    assertEquals(20,prixAucune.getPrix(1));
    assertEquals(20,prixAucune.getPrix(5));
    assertEquals(20,prixAucune.getPrix(9));
    assertEquals(10,prixAucune.getPrix(10));
    assertEquals(10,prixAucune.getPrix(15));
    assertEquals(10,prixAucune.getPrix(20));
    assertEquals(10,prixAucune.getPrix(25));
  }
  @DisplayName("Testez prixpub.getPrix lance QuantiteNonAutoriseeException pour le prix de 2 unités ")
  @Test
  void testGetPrixPrixPub2Unites () {
    assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
  }


  @DisplayName("Testez prixSolde.getPrix lance QuantiteNonAutoriseeException pour le prix de 1 unitée")
  @Test
  void testGetPrixPrixSolde1Unitee() {
    assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
  }

//  -----------------
  @Test
  void getTypePromo() {
  }

  @Test
  void getValeurPromo() {
  }

  @Test
  void definirPrix() {
  }

  @Test
  void getPrix() {
  }
}