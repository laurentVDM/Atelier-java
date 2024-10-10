package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LotTest {

  Lot lot;
  @BeforeEach
  void setUp() {
    lot=new Lot(4,2,"bibi");
    assertThrows(IllegalArgumentException.class,()->new Lot(0,5,"vovo"));
  }

  @Test
  void signalerAffectation() {
    Lot newlot= new Lot(7,5,"toto");
    assertTrue(newlot.signalerAffectation());
  }

  @Test
  void signalerAffectationFalse() {
    Lot newlot= new Lot(7,5,"toto");
    newlot.signalerAffectation();
    assertFalse(newlot.signalerAffectation());
  }
}