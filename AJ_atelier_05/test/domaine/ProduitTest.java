package domaine;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
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
    prixAucune  = new Prix();
    prixPub = new Prix(TypePromo.PUB,1);
    prixSolde = new Prix(TypePromo.SOLDE, 1);

    prixAucune.definirPrix(1,20);
    prixAucune.definirPrix(10,10);

    prixPub.definirPrix(3,10);

    produit1 = new Produit("iphone", "apple", "telephone");
    produit2 = new Produit("pc rapide", "asus","ordinateur");

    produit1.ajouterPrix(LocalDate.parse("2007-12-03"), prixAucune);
    produit1.ajouterPrix(LocalDate.parse("2007-12-05"), prixPub);
    produit1.ajouterPrix(LocalDate.parse("2007-12-07"), prixSolde);
  }

//  -----------------

  @DisplayName("test constructeur lance IllegalArgumentException si param nom invalide")
  @Test
  void testParamConstructeurProduit1 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit("", "apple", "telephone"));
  }

  @DisplayName("test constructeur lance IllegalArgumentException si param marque invalide")
  @Test
  void testParamConstructeurProduit2 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit("iphone", "", "telephone"));
  }

  @DisplayName("test constructeur lance IllegalArgumentException si param rayon invalide")
  @Test
  void testParamConstructeurProduit3 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit("iphone", "apple", ""));
  }

  @DisplayName("test constructeur lance IllegalArgumentException si param nom null")
  @Test
  void testParamConstructeurProduitNull1 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit(null, "apple", "telephone"));
  }

  @DisplayName("test constructeur lance IllegalArgumentException si param marque null")
  @Test
  void testParamConstructeurProduitNull2 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit("iphone", null, "telephone"));
  }

  @DisplayName("test constructeur lance IllegalArgumentException si param rayon null")
  @Test
  void testParamConstructeurProduitNull3 () {
    assertThrows(IllegalArgumentException.class, ()-> new Produit("iphone", "apple", null));
  }

//  -----------------

  @DisplayName("test marque lors de l’instanciation d’un produit\n"
      + "correspondent bien à celles renvoyées par les getters.")
  @Test
  void testGetMarque() {
    assertEquals("apple",  produit1.getMarque());
  }

  @DisplayName("test nom lors de l’instanciation d’un produit\n"
      + "correspondent bien à celles renvoyées par les getters.")
  @Test
  void testGetNom() {
    assertEquals("iphone",  produit1.getNom());
  }

  @DisplayName("test rayon lors de l’instanciation d’un produit\n"
      + "correspondent bien à celles renvoyées par les getters.")
  @Test
  void testGetRayon() {
    assertEquals("telephone",  produit1.getRayon());
  }

//  -----------------

  @DisplayName("test ajouterPrix lance une IllegalArgumentException si date est null.")
  @Test
  void testAjouterPrixDateNull() {
    assertThrows(IllegalArgumentException.class, ()-> produit2.ajouterPrix(null,prixAucune));
  }

  @DisplayName("test ajouterPrix lance une IllegalArgumentException si prix est null.")
  @Test
  void testAjouterPrixPrixNull() {
    assertThrows(IllegalArgumentException.class, ()-> produit2.ajouterPrix(LocalDate.parse("2007-12-03"),null));
  }

  @DisplayName("test ajouterPrix lance une DateDejaPresenteException si date est déjà présente.")
  @Test
  void testAjouterPrixDatePresente() {
    assertThrows(DateDejaPresenteException.class, ()-> produit1.ajouterPrix(LocalDate.parse("2007-12-03"),prixAucune));
  }

  @DisplayName("test ajouterPrix ajoute un prix pour une date donnee en verifiant avec getPrix")
  @Test
  void testAjouterPrix() {
    produit2.ajouterPrix(LocalDate.parse("2007-12-03"),prixAucune);
    assertEquals(prixAucune, produit2.getPrix(LocalDate.parse("2007-12-03")));
  }

  @DisplayName("test que lorsqu’on demande un prix pour une date antérieure à la définition d’un prix\n"
      + "  l’exception PrixNonDisponibleException est lancée.")
  @Test
  void testGetPrixDateAnteriere() {
    assertThrows(PrixNonDisponibleException.class, ()-> produit1.getPrix(LocalDate.parse("2007-12-02")));
  }

  @DisplayName("test que lorsqu’on demande un prix pour un produit qui n’en n’a pas, l’exception\n"
      + "  PrixNonDisponibleException est lancée.")
  @Test
  void testGetPrixSansPrix() {
    assertThrows(PrixNonDisponibleException.class, ()-> produit2.getPrix(LocalDate.parse("2007-12-02")));
  }

  @DisplayName("  test que lorsqu’on demande un prix pour une date comprise entre deux dates pour\n"
      + "  lesquelles le prix a été défini, c’est bien celui de la date antérieure qui a été renvoyé.")
  @Test
  void testPrixEntreDeuxDates() {
    assertEquals(prixAucune, produit1.getPrix(LocalDate.parse("2007-12-04")));
  }

//  -----------------

  @DisplayName("test que la méthode equals fonctionne pour 2 instances de Produit différentes\n"
      + "  mais qui ont les même marque, nom et rayon.")
  @Test
  void testEquals2Instances(){
    Produit testProduit1 = new Produit("nom", "marque", "rayon");
    Produit testProduit2 = new Produit("nom", "marque", "rayon");
    assertTrue(testProduit1.equals(testProduit2));
  }

  //
  @DisplayName("test que la méthode equals renvoie faux pour deux produits ayant la même marque\n"
      + "  et le même rayon mais ayant des noms différents.")
  @Test
  void testEquals2InstancesAutresNoms(){
    Produit testProduit1 = new Produit("nom1", "marque", "rayon");
    Produit testProduit2 = new Produit("nom2", "marque", "rayon");
    assertFalse(testProduit1.equals(testProduit2));
  }

  //
  @DisplayName(". Vérifier que la méthode equals renvoie faux pour deux produits ayant le même nom et\n"
      + "  le même rayon mais ayant des marques différentes.")
  @Test
  void testEquals2InstancesAutresMarques(){
    Produit testProduit1 = new Produit("nom", "marque1", "rayon");
    Produit testProduit2 = new Produit("nom", "marque2", "rayon");
    assertFalse(testProduit1.equals(testProduit2));
  }

  @DisplayName("Vérifier que la méthode equals renvoie faux pour deux produits ayant le même nom et\n"
      + "  la même marque mais ayant des rayons différents")
  @Test
  void testEquals2InstancesAutresRayons(){
    Produit testProduit1 = new Produit("nom", "marque", "rayon1");
    Produit testProduit2 = new Produit("nom", "marque", "rayon2");
    assertFalse(testProduit1.equals(testProduit2));
  }

  @DisplayName("test que la méthode hashCode renvoie bien la même valeur pour 2 instances de\n"
      + "  Produit différentes mais qui ont les même marque, nom et rayon.")
  @Test
  void testHashCode() {
    Produit testProduit1 = new Produit("nom", "marque", "rayon");
    Produit testProduit2 = new Produit("nom", "marque", "rayon");
    assertEquals(testProduit1, testProduit2);
  }


}