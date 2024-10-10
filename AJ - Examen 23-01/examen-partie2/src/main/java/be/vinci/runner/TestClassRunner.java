package be.vinci.runner;

import be.vinci.domain.DomainFactory;
import be.vinci.domain.TestClass;
import be.vinci.domain.TestClassImpl;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class TestClassRunner {

    private DomainFactory domainFactory;

    public TestClassRunner(DomainFactory domainFactory){
        this.domainFactory = domainFactory;
    }

    private class RunningTest implements Runnable{
        private Class aClass;
        public RunningTest(Class aClass){
            this.aClass = aClass;
        }
        @Override
        public  void run(){
            synchronized (RunningTest.class){
                TestClass testClass = domainFactory.testclass();
                testClass.loadFromClass(aClass);
                testClass.runAllTests();
            }
        }
    }

    /**
     * Exécute toutes les classes de tests trouvés dans le package "tests", une par une.
     */
    public void runTests() {
        getResourcesClasses().stream().forEach(c -> this.runTests(c));
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
        TestClass testClass = new TestClassImpl();
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
