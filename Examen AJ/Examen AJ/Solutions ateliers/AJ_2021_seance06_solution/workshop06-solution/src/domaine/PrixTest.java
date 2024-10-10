package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
class PrixTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(Promo.PUB,10);
        prixSolde = new Prix(Promo.SOLDE,30);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);
    }

    //Tests du constructeur

    @Test
    @DisplayName("Test du constructeur avec paramètre Promo null")
    void testPrix1(){
        assertThrows(IllegalArgumentException.class,() -> new Prix(null,15));
    }

    @Test
    @DisplayName("Test du constructeur avec valeur <0")
    void testPrix2(){
        assertThrows(IllegalArgumentException.class,() -> new Prix(Promo.SOLDE,-1));
    }

    // Tests des getters

    @Test
    @DisplayName("Vérification de la valeur renvoyée par getValeurPromo")
    void testGetValeurPromo() {
        assertAll(()->assertEquals(0,prixAucune.getValeurPromo()),
                ()-> assertEquals(10,prixPub.getValeurPromo()),
                () ->assertEquals(30,prixSolde.getValeurPromo()));
    }

    @Test
    @DisplayName("Vérification de la valeur renvoyée par getTypePromo")
    void testGetTypePromo() {
        assertAll(()->assertNull(prixAucune.getTypePromo()),
                  ()-> assertSame(Promo.PUB,prixPub.getTypePromo()),
                  () ->assertSame(Promo.SOLDE,prixSolde.getTypePromo()));
    }

    //Tests de definirPrix

    @ParameterizedTest
    @ValueSource(ints = {-1,0})
    @DisplayName("Test de la méthode definirPrix avec une quantité < ou = à 0")
    void testDefinirPrix1(int quantite) {
        assertThrows(IllegalArgumentException.class,()->prixPub.definirPrix(quantite,15));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.5,0})
    @DisplayName("Test de la méthode definirPrix avec une valeur < ou = à 0")
    void testDefinirPrix2(double valeur) {
        assertThrows(IllegalArgumentException.class,()->prixPub.definirPrix(2,valeur));
    }

    @Test
    @DisplayName("Test du remplacement de la valeur pour une quantité dèjà existante")
    void testDefinirPrix3() throws QuantiteNonAutoriseeException {
       prixAucune.definirPrix(10,6);
       assertEquals(6,prixAucune.getPrix(10));
    }

    //tests de getPrix

    @ParameterizedTest
    @ValueSource(ints = {-1,0})
    @DisplayName("Test de la méthode getPrix avec une quantité < ou = à 0")
    void testGetPrix1(int quantite) {
        assertThrows(IllegalArgumentException.class,()->prixPub.getPrix(quantite));
    }

    //Pour prixAucune, le prix est de 20 euros pour une quantité comprise entre 1 et 9 (bornes incluses)
    @ParameterizedTest
    @ValueSource(ints = {1,5,9})
    @DisplayName("Test de la méthode getPrix pour prixAucune avec les quantités 1,5,9")
    void testGetPrix2a(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(20,prixAucune.getPrix(quantite));
    }

    //Pour prixAucune, le prix est de 10 euros pour une quantité supérieure ou égale à 10
    @ParameterizedTest
    @ValueSource(ints = {10,15,20,25})
    @DisplayName("Test de la méthode getPrix pour prixAucune avec des quantités >=10")
    void testGetPrix2b(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(10,prixAucune.getPrix(quantite));
    }

    @Test
    @DisplayName("Test de getPrix avec une quantite < à la plus petite quantité minimale")
    void testGetPrix3() {
        assertThrows(QuantiteNonAutoriseeException.class,()->prixPub.getPrix(2));
    }

    @Test
    @DisplayName("Test de getPrix s'il n'y a pas de prix défini")
    void testGetPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class,()->prixSolde.getPrix(1));
    }

    //Tests de la méthode clone

    @Test
    @DisplayName("Test que clone renvoie bien un nouvel objet")
    void testClone1() {
        assertNotSame(prixAucune,prixAucune.clone());
    }

    @Test
    @DisplayName("Test que le type de promo et la valeur du clone sont bien les mêmes que ceux de l'objet cloné")
    void testClone2() {
        Prix prixAucuneClone = prixAucune.clone();
        Prix prixPubClone = prixPub.clone();
        assertAll(()-> assertEquals(prixAucune.getTypePromo(), prixAucuneClone.getTypePromo(),"Le clone n'a pas le même type de promo que l'objet cloné"),
                ()->	 assertEquals(prixAucune.getValeurPromo(), prixAucuneClone.getValeurPromo(),"Le clone n'a pas la même valeur de promo que l'objet cloné" ),
                ()->	 assertEquals(prixPub.getValeurPromo(), prixPubClone.getValeurPromo(),"Le clone n'a pas la même valeur de promo que l'objet cloné" ),
                ()-> assertEquals(prixPub.getTypePromo(), prixPubClone.getTypePromo(),"Le clone n'a pas le même type de promo que l'objet cloné")
        );
    }

    @ParameterizedTest
    @MethodSource("range")
    @DisplayName("Test que les quantités et valeurs du clone correspondent bien à celles de l'objet cloné")
    void testClone3(int quantite) throws QuantiteNonAutoriseeException {
        Prix prixAucuneClone = prixAucune.clone();
        assertEquals(prixAucune.getPrix(quantite),prixAucuneClone.getPrix(quantite));
    }

    static IntStream range(){
        return IntStream.range(1,20);
    }

    @Test
    @DisplayName("Test qu'un ajout d'un nouveau prix au clone n'affecte pas l'objet cloné")
    void testClone4() throws QuantiteNonAutoriseeException {
        Prix prixAucuneClone = prixAucune.clone();
        prixAucuneClone.definirPrix(15,6);
        assertAll(
                ()-> assertEquals(10,prixAucune.getPrix(15)),
                ()-> assertEquals(6,prixAucuneClone.getPrix(15))
        );
    }

}