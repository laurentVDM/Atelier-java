package services;

import static org.junit.jupiter.api.Assertions.*;

import domaine.City;
import domaine.Firstname;
import domaine.Firstname.Gender;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamStatsServiceTest {

  /*
  Créez pour cela au moins deux FirstName contenant plusieurs
  villes. Vérifiez que la valeur renvoyée par la méthode getBirthsCount est bien la bonne.
  Vous devez faire ce test de deux façons : une fois sans utiliser Mockito (utilisez directement
  les classes fournies) et une fois avec Mockito.
  */

  private Firstname firstname1;
  private Firstname firstname2;
  private City c1;
  private City c2;
  private City c3;
  private City c4;
  private Stream<Firstname> namesSupplier;

  @BeforeEach
  void setUp() {
    firstname1 = new Firstname("Kevin", Gender.M);
    firstname2 = new Firstname("Manon", Gender.F);

    c1 = new City(1, "bxl");
    c2 = new City(2, "paris");
    c3 = new City(3, "amsterdam");
    c4 = new City(4, "wavre");
    c1.addFirstname(firstname1);
    c2.addFirstname(firstname1);
    c2.addFirstname(firstname2);
    c3.addFirstname(firstname2);
    c4.addFirstname(firstname2);
  }

  @Test
  void getFirstnamesCount() {
  }
}