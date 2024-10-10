package be.vinci.domain;

import be.vinci.runner.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe est une modélisation d'une classe de test.
 * <p>
 * Pour l'instant, elle est assez simple, car elle ne retient que la classe à tester
 * et la liste des méthodes à exécuter.
 * Mais à terme, elle pourrait contenir une liste des méthodes "beforeEach"...
 * <p>
 * Elle contient également une méthode pour charger les données d'un objet Class dans
 * l'objet TestClass. Et une méthode pour exécuter tous les tests.
 */
class TestClassImpl implements TestClass {

    private DomaineFactory domaineFactory;

    public void setDomaineFactory(DomaineFactory domaineFactory) {
        this.domaineFactory = domaineFactory;
    }

    private Class classObject;
    private List<TestMethod> tests = new ArrayList<TestMethod>();

    public Class getClassObject() {
        return this.classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }

    public void addTest(TestMethod test) {
        this.tests.add(test);
    }

    public List<TestMethod> getTests() {
        return Collections.unmodifiableList(this.tests);
    }

    /**
     * Cette méthode permet de charger dans l'objet TestClass courant toutes les
     * données d'une classe de test passée en paramètre.
     * <p>
     * On retient la classe, mais également les méthodes de test qu'on va trouver
     * dedans. Pour savoir si une méthode est une méthode de test, elle doit respecter
     * les critères suivants :
     * - Avoir l'annotation @Test
     * - Être public
     * - Ne pas être static
     * - Avoir comme type de retour void (comparez le type de retour avec void.class)
     * <p>
     * Les méthodes de test doivent être ajoutées dans la liste des méthodes de test.
     * Donnez simplement comme nom à la méthode de test, le nom de la méthode.
     *
     * @param classToLoad
     */
    public void loadFromClass(Class classToLoad) {
        // TODO
        setClassObject(classToLoad);
        for (Method m : classToLoad.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                if (Modifier.isPublic(m.getModifiers())) {
                    if (!Modifier.isStatic(m.getModifiers())) {
                        if (m.getReturnType().equals(void.class)) {

                            TestMethod testMethod = domaineFactory.getTestMethod();
                            testMethod.setName(m.getName());
                            testMethod.setMethod(m);
                            addTest(testMethod);
                        }
                    }
                }
            }
        }
    }

    /**
     * Exécute tous les tests de la classe de test précédemment chargée.
     * <p>
     * Pour cela, il faut d'abord instancier la classe dont il faut exécuter
     * les méthodes de test. Tous les tests doivent être exécutés sur le même
     * objet instancié. Considérons qu'il y a toujours un constructeur sans
     * paramètres dans chaque classe de test.
     * <p>
     * Vous pouvez ignorer les exceptions, en lançant simplement une
     * RuntimeException() qui emballe l'exception récupérée.
     */
    @Override
    public void runAllTests() {
        // TODO

        try {
            Constructor c = classObject.getDeclaredConstructor();
            c.setAccessible(true);
            Object o = c.newInstance();

            for (TestMethod test : tests) {
                test.run(o);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}
















