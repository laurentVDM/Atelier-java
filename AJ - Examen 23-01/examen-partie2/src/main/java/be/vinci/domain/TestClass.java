package be.vinci.domain;

import java.util.List;

public interface TestClass {

  Class getClassObject();

  void setClassObject(Class classObject);

  void addTest(TestMethod test);

  List<TestMethod> getTests();

  void loadFromClass(Class classToLoad);

  void runAllTests();
}
