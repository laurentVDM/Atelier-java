package domaine;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.DateDejaPresenteException;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProduitTest {
  private Prix prixAucune;
  private Prix prixPub;
  private Prix prixSolde;

  private Produit produit1;
  private Produit produit2;
  @BeforeEach
  void setUp() {
    prixAucune = new Prix();
    prixPub = new Prix(TypePromo.PUB, 10);
    prixSolde = new Prix(TypePromo.SOLDE, 30);
    prixAucune.definirPrix(1,20);
    prixAucune.definirPrix(10,10);
    prixPub.definirPrix(3,15);

    produit1 = new Produit("paire", "nike","rayon chaussures");
    produit2 = new Produit("veste", "adidas","rayon veste");
    produit2.ajouterPrix(LocalDate.ofEpochDay(2000/01/01), prixAucune);
    produit2.ajouterPrix(LocalDate.ofEpochDay(2001/01/01), prixPub);
    produit2.ajouterPrix(LocalDate.ofEpochDay(2002/01/01), prixSolde);
  }

  @Test
  @DisplayName("Vérifier que la méthode ajouterPrix lance une IllegalArgumentException si un\n"
      + "des paramètres est null.")
  void testAjouterPrix1() {
    assertAll(
        ()->assertThrows(IllegalArgumentException.class, ()-> produit1.ajouterPrix(null, prixAucune)),
        ()->assertThrows(IllegalArgumentException.class, ()-> produit1.ajouterPrix(LocalDate.ofEpochDay(2000/01/01), null))
    );
  }

  @Test
  @DisplayName("Vérifier que la méthode ajouterPrix lance une DateDejaPresenteException si la\n"
      + "date est déjà présente")
  void testAjouterPrix2() {
        assertThrows(
            DateDejaPresenteException.class, ()-> produit2.ajouterPrix(LocalDate.ofEpochDay(2000/01/01), prixAucune));
  }

  @Test
  void getMarque() {
  }

  @Test
  void getNom() {
  }

  @Test
  void getRayon() {
  }

  @Test
  void ajouterPrix() {
  }

  @Test
  void getPrix() {
  }

  @Test
  void testEquals() {
  }

  @Test
  void testHashCode() {
  }
}