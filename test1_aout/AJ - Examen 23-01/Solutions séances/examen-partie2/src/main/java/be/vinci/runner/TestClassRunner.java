package be.vinci.runner;

import be.vinci.domain.DomaineFactory;
import be.vinci.domain.TestClass;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class TestClassRunner {

    DomaineFactory domaineFactory;

    public void setDomaineFactory(DomaineFactory domaineFactory) {
        this.domaineFactory = domaineFactory;
    }

    /**
     * Exécute toutes les classes de tests trouvés dans le package "tests", une par une.
     */
    public void runTests() {
        getResourcesClasses().stream().forEach(c -> (new Thread(() -> this.runTests(c))).start());
    }


    // plus utiles avec le thread en ano lambda
     private static class MonThread extends Thread {
        Class c;
        TestClassRunner testClassRunner;
        public MonThread(Class c, TestClassRunner testClassRunner) {
            this.c = c;
            this.testClassRunner = testClassRunner;
        }

        @Override
        public void run() {
            testClassRunner.runTests(c);
        }
    }

    /**
     * Méthode privée.
     * Cette méthode permet d'exécuter qu'une seule classe de test, en passant
     * l'objet Class en paramètre. On va charger la classe dans un objet TestClass,
     * et ensuite exécuter tous les tests dessus en utilisant la méthode runAllTests().
     *
     * @param aClass la classe de test à exécuter
     */



    private void runTests(Class aClass) {
        TestClass testClass = domaineFactory.getTestClass();
        testClass.setDomaineFactory(domaineFactory);
        testClass.loadFromClass(aClass);
        testClass.runAllTests();
    }

    /**
     * Méthode privée.
     * Cette méthode parcourt le package "tests" et renvoit un ensemble (Set)
     * avec toutes les classes dedans.
     *
     * @return L'ensemble des classes dans le package "tests"
     */
    private Set<Class> getResourcesClasses() {
        try {
            return ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(c -> c.getPackageName().contains("be.vinci.tests"))
                    .map(c -> c.load())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
