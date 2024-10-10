package be.vinci.domain;

import java.lang.reflect.Method;

/**
 * Cette classe représente une méthode de test. Elle est assez simple
 * pour l'instant, et ne contient que la Method à exécuter, et le nom
 * du test. Pour faire simple, le nom du test est le nom de la méthode.
 */
class TestMethodImpl implements TestMethod {

    private Method method;

    private String name;

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void run(Object o) {
        // TODO
    }

}
