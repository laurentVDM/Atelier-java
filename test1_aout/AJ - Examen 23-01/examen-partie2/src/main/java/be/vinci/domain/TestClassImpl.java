package be.vinci.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe est une modélisation d'une classe de test.
 *
 * Pour l'instant, elle est assez simple, car elle ne retient que la classe à tester
 * et la liste des méthodes à exécuter.
 * Mais à terme, elle pourrait contenir une liste des méthodes "beforeEach"...
 *
 * Elle contient également une méthode pour charger les données d'un objet Class dans
 * l'objet TestClass. Et une méthode pour exécuter tous les tests.
 */
class TestClassImpl implements TestClass {

    private Class classObject;
    private List<TestMethod> tests = new ArrayList<TestMethod>();

    @Override
    public Class getClassObject() {
        return this.classObject;
    }

    @Override
    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }

    @Override
    public void addTest(TestMethod test) {
        this.tests.add(test);
    }

    @Override
    public List<TestMethod> getTests() {
        return Collections.unmodifiableList(this.tests);
    }


    @Override
    public void loadFromClass(Class classToLoad) {

        // TODO
    }


    @Override
    public void runAllTests() {
        // TODO
    }

}
