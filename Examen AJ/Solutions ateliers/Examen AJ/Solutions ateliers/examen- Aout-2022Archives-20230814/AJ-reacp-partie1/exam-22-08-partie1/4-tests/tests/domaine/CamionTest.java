package domaine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
class CamionTest {

  private Trajet trajetMock;
  private Camion camion;

  @BeforeEach
  void setUp() {
    camion = new Camion("a",10,100);
    trajetMock = mock(Trajet.class);
  }

  @Test
  @DisplayName("Ajouter un trajet avec trop de caisse : False")
  void ajouterTrajetTropCaisse() {
    when(trajetMock.nbCaisses()).thenReturn(20);
    when(trajetMock.getDate()).thenReturn(LocalDate.now().plusDays(1));
    when(trajetMock.calculerPoidsTotal()).thenReturn(50.0);


    assertFalse(camion.ajouterTrajet(trajetMock));
  }
}