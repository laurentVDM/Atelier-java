package be.vinci.domain;

import java.util.List;

public interface TestClass {

  Class getClassObject();

  void setClassObject(Class classObject);

  void addTest(TestMethod test);

  List<TestMethod> getTests();

  /**
   * Cette méthode permet de charger dans l'objet TestClass courant toutes les données d'une classe
   * de test passée en paramètre.
   * <p>
   * On retient la classe, mais également les méthodes de test qu'on va trouver dedans. Pour savoir
   * si une méthode est une méthode de test, elle doit respecter les critères suivants : - Avoir
   * l'annotation @Test - Être public - Ne pas être static - Avoir comme type de retour void
   * (comparez le type de retour avec void.class)
   * <p>
   * Les méthodes de test doivent être ajoutées dans la liste des méthodes de test. Donnez
   * simplement comme nom à la méthode de test, le nom de la méthode.
   *
   * @param classToLoad
   */
  void loadFromClass(Class classToLoad);

  /**
   * Exécute tous les tests de la classe de test précédemment chargée.
   * <p>
   * Pour cela, il faut d'abord instancier la classe dont il faut exécuter les méthodes de test.
   * Tous les tests doivent être exécutés sur le même objet instancié. Considérons qu'il y a
   * toujours un constructeur sans paramètres dans chaque classe de test.
   * <p>
   * Vous pouvez ignorer les exceptions, en lançant simplement une RuntimeException() qui emballe
   * l'exception récupérée.
   */
  void runAllTests();
}
