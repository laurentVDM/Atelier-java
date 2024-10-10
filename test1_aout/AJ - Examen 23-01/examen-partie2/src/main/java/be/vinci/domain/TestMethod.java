package be.vinci.domain;

import java.lang.reflect.Method;

public interface TestMethod {

  Method getMethod();

  void setMethod(Method method);

  String getName();

  void setName(String name);
  /**
   * Cette méthode permet d'exécuter la méthode de test.
   * Elle reçoit en paramètre l'objet sur lequel exécuter la méthode.
   *
   * Vous pouvez ignorer les exceptions, en lançant simplement une
   * RuntimeException() qui emballe l'exception récupérée.
   *
   * @param o l'objet sur lequel invoquer la méthode
   */
  void run(Object o);
}
