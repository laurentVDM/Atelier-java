package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Produit produitSansPrix;
    private Produit produitAvecPrix;
    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;
    private static final LocalDate DATE_ANNEE_PASSEE = LocalDate.now().minusYears(1);
    private static final LocalDate DATE_MOIS_PASSEE = LocalDate.now().minusMonths(1);
    private static final LocalDate DATE_AUJOURDHUI = LocalDate.now();

    @BeforeEach
    void setUp() throws DateDejaPresenteException {
        produitSansPrix = new Produit("nom1","marque1","rayon1");
        produitAvecPrix = new Produit("nom2","marque2","rayon2");
        prixAucune = new Prix();
        prixPub = new Prix(Promo.PUB,10);
        prixSolde = new Prix(Promo.SOLDE,30);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);
        produitAvecPrix.ajouterPrix(DATE_ANNEE_PASSEE,prixAucune);
        produitAvecPrix.ajouterPrix(DATE_MOIS_PASSEE,prixPub);
        produitAvecPrix.ajouterPrix(DATE_AUJOURDHUI,prixSolde);
    }

    //Tests des prix (ajouterPrix et getPrix)

    @Test
    @DisplayName("Test d'ajouterPrix avec une date null")
    void testAjouterPrix1a() {
        assertThrows(IllegalArgumentException.class,()->produitSansPrix.ajouterPrix(null,prixAucune));
    }

    @Test
    @DisplayName("Test d'ajouterPrix avec un prix null")
    void testAjouterPrix1b() {
        assertThrows(IllegalArgumentException.class,()->produitSansPrix.ajouterPrix(DATE_AUJOURDHUI,null));
    }
    @Test
    @DisplayName("Test d'ajouterPrix à une date déjà présente")
    void testAjouterPrix2(){
       assertThrows(DateDejaPresenteException.class,()->produitAvecPrix.ajouterPrix(DATE_MOIS_PASSEE,new Prix()));
    }

    @Test
    @DisplayName("Vérification de l'ajout des prix avec le bon type de promo et la bonne valeur de promo ")
    void testAjouterPrix3a() throws PrixNonDisponibleException {
        Prix prixMoisPasse = produitAvecPrix.getPrix(DATE_MOIS_PASSEE);
        Prix prixAujourdhui = produitAvecPrix.getPrix(DATE_AUJOURDHUI);
        assertAll(
                () ->assertSame(Promo.PUB,prixMoisPasse.getTypePromo()),
                () -> assertEquals(10,prixMoisPasse.getValeurPromo()),
                () ->assertSame(Promo.SOLDE,prixAujourdhui.getTypePromo()),
                () -> assertEquals(30,prixAujourdhui.getValeurPromo())
        );
    }


    @ParameterizedTest
    @MethodSource("range")
    @DisplayName("Test que le prix est bien ajouté avec les bons prix par quantité")
    void testAjouterPrix3b(int quantite) throws DateDejaPresenteException, PrixNonDisponibleException, QuantiteNonAutoriseeException {
        Prix prixAnneePassee = produitAvecPrix.getPrix(DATE_ANNEE_PASSEE);
        assertEquals(prixAucune.getPrix(quantite),prixAnneePassee.getPrix(quantite));
    }

    //génère un IntStreanm "contenant" les valeurs de 1 à 19
    static IntStream range() {
        return IntStream.range(1, 20);
    }

    @Test
    @DisplayName("Vérification que getPrix renvoie bien un clone")
    void testGetPrix4() throws PrixNonDisponibleException {
        assertNotSame(produitAvecPrix.getPrix(DATE_MOIS_PASSEE),produitAvecPrix.getPrix(DATE_MOIS_PASSEE));
    }

    @Test
    @DisplayName("Test que la méthode ajouterPrix clone le prix ajouté")
    void testAjouterPrix5() throws QuantiteNonAutoriseeException, PrixNonDisponibleException {
        prixAucune.definirPrix(15,6);
        Prix prixAnneePassee = produitAvecPrix.getPrix(DATE_ANNEE_PASSEE);
        assertEquals(10,prixAnneePassee.getPrix(15));
    }

    @Test
    @DisplayName("Test que la méthode getPrix avec une date antérieure aux dates où le prix est défini")
    void testGetPrix6() {
        assertThrows(PrixNonDisponibleException.class,()->produitAvecPrix.getPrix(DATE_ANNEE_PASSEE.minusDays(1)));
    }

    @Test
    @DisplayName("Test que la méthode getPrix sur un produit sans prix défini")
    void testGetPrix7() {
        assertThrows(PrixNonDisponibleException.class,()->produitSansPrix.getPrix(DATE_AUJOURDHUI));
    }

    @Test
    @DisplayName("Test que la méthode getPrix avec une date située entre deux dates de définition de prix")
    public void testGetPrix8() throws PrixNonDisponibleException {
        Prix p = produitAvecPrix.getPrix(DATE_AUJOURDHUI.minusDays(1));
        assertAll(
                () -> assertEquals(prixPub.getValeurPromo(), p.getValeurPromo()),
                () -> assertSame(prixPub.getTypePromo(), p.getTypePromo())
        );
    }

    //test des méthodes equals et hashCode

    @Test
    @DisplayName("Test que deux produits ayant même nom, marque et rayon sont égaux")
    void testEquals1() {
        Produit produit = new Produit("nom2","marque2","rayon2");
        assertEquals(produitAvecPrix,produit);
    }

    @Test
    @DisplayName("Test que deux produits ayant deux noms différents ne sont pas égaux")
    void testEquals2() {
        Produit produit = new Produit("nom","marque2","rayon2");
        assertNotEquals(produitAvecPrix,produit);
    }

    @Test
    @DisplayName("Test que deux produits ayant deux marques différentes ne sont pas égaux")
    void testEquals3() {
        Produit produit = new Produit("nom2","marque","rayon2");
        assertNotEquals(produitAvecPrix,produit);
    }

    @Test
    @DisplayName("Test que deux produits ayant deux rayons différents ne sont pas égaux")
    void testEquals4() {
        Produit produit = new Produit("nom2","marque2","rayon");
        assertNotEquals(produitAvecPrix,produit);
    }

    @Test
    @DisplayName("Test que deux produits ayant même nom, marque et rayon ont le même hashCode")
    void testHashCode5() {
        Produit produit = new Produit("nom2","marque2","rayon2");
        assertEquals(produitAvecPrix.hashCode(),produit.hashCode());
    }

    //tests de la méthode clone

    @Test
    @DisplayName("Test de l'égalité entre l'objet cloné et le clone")
    void testClone1() {
        assertEquals(produitAvecPrix,produitAvecPrix.clone());
    }

    @Test
    @DisplayName("Test que l'objet cloné est bien un nouvel objet")
    void testClone2() {
        assertNotSame(produitAvecPrix,produitAvecPrix.clone());
    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    @DisplayName("Test que les prix du clone ont les mêmes valeurs et type de promo que l'objet cloné ")
    void testClone3a(LocalDate date) throws PrixNonDisponibleException {
        Produit clone = produitAvecPrix.clone();
        Prix prixClone = clone.getPrix(date);
        Prix prixOriginal = produitAvecPrix.getPrix(date);
        assertAll(
                () -> assertSame(prixOriginal.getTypePromo(),prixClone.getTypePromo()),
                () -> assertEquals(prixOriginal.getValeurPromo(),prixClone.getValeurPromo())
        );
    }

    static Stream<LocalDate> dateProvider(){
        return Stream.of(DATE_ANNEE_PASSEE,DATE_MOIS_PASSEE,DATE_AUJOURDHUI);
    }

    @ParameterizedTest
    @MethodSource("range")
    @DisplayName("Test que les prix du clone ont les mêmes prix par quantité minimale que l'objet cloné")
    void testClone3b(int quantite) throws PrixNonDisponibleException, QuantiteNonAutoriseeException {
        Produit clone = produitAvecPrix.clone();
        Prix prixClone = clone.getPrix(DATE_ANNEE_PASSEE);
        Prix prixOriginal = produitAvecPrix.getPrix(DATE_ANNEE_PASSEE);
        assertEquals(prixOriginal.getPrix(quantite),prixClone.getPrix(quantite));
    }

    @Test
    @DisplayName("Test que l'ajout d'un prix au clone n'affecte pas l'objet cloné")
    void testClone4() throws DateDejaPresenteException, PrixNonDisponibleException {
        Produit clone = produitAvecPrix.clone();
        Prix prixAjoute = new Prix(Promo.DESTOCKAGE,50);
        LocalDate dateFuture = DATE_AUJOURDHUI.plusWeeks(2);
        clone.ajouterPrix(dateFuture,prixAjoute);
        Prix prixOriginal = produitAvecPrix.getPrix(dateFuture);
        assertAll(
                () ->assertSame(Promo.SOLDE,prixOriginal.getTypePromo()),
                () -> assertEquals(30,prixOriginal.getValeurPromo())
        );
    }

    //Tests du constructeur en cas de paramètre invalide

    @Test
    @DisplayName("Test du constructeur avec un nom null")
    void testProduit1() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit(null, "marque", "rayon"));
    }

    @Test
    @DisplayName("Test du constructeur avec une marque null")
    void testProduit2() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit("nom", null, "rayon"));
    }

    @Test
    @DisplayName("Test du constructeur avec un rayon null")
    void testProduit3() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit("nom", "marque", null));
    }

    @Test
    @DisplayName("Test du constructeur avec un nom constitué uniquement de blancs")
    void testProduit4() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit("   ", "marque", "rayon"));
    }

    @Test
    @DisplayName("Test du constructeur avec une marque constituée uniquement de blancs")
    void testProduit5() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit("nom", "\t\t", "rayon"));
    }

    @Test
    @DisplayName("Test du constructeur avec un rayon constitué uniquement de blancs")
    void testProduit6() {
        assertThrows(IllegalArgumentException.class, ()-> new Produit("nom", "marque", "\n"));
    }

	/* Solution alternative pour le test du constructeur avec des paramètres invalides

	@DisplayName("Test du constructeur avec un paramètre null")
	@ParameterizedTest
	@MethodSource("invalidParameterProvider")
	void testProduit(String nom, String marque, String rayon) {
		assertThrows(IllegalArgumentException.class,()-> new Produit(nom, marque, rayon));
	}

	static Stream<Arguments> invalidParameterProvider(){
		return Stream.of(
					Arguments.of(null,"marque","rayon"),
					Arguments.of("nom",null,"rayon"),
					Arguments.of("nom","marque",null),
                    Arguments.of("    ","marque","rayon"),
                    Arguments.of("nom","\t\t","rayon"),
                    Arguments.of("nom","marque","\n")
				);

	}

	*/

    //Test des getters

    @DisplayName("Test de la valeur renvoyée par getNom")
    @Test
    void testGetNom() {
        assertEquals("nom1", produitSansPrix.getNom());
    }

    @DisplayName("Test de la valeur renvoyée par getMarque")
    @Test
    void testGetMarque() {
        assertEquals("marque1", produitSansPrix.getMarque());
    }

    @DisplayName("Test de la valeur renvoyée par getRayon")
    @Test
    void testGetRayon() {
        assertEquals("rayon1", produitSansPrix.getRayon());
    }




}