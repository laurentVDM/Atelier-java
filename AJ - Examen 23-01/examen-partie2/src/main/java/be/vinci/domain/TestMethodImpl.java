package be.vinci.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Cette classe représente une méthode de test. Elle est assez simple
 * pour l'instant, et ne contient que la Method à exécuter, et le nom
 * du test. Pour faire simple, le nom du test est le nom de la méthode.
 */
public class TestMethodImpl implements TestMethod {

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

    /**
     * Cette méthode permet d'exécuter la méthode de test.
     * Elle reçoit en paramètre l'objet sur lequel exécuter la méthode.
     *
     * Vous pouvez ignorer les exceptions, en lançant simplement une
     * RuntimeException() qui emballe l'exception récupérée.
     *
     * @param o l'objet sur lequel invoquer la méthode
     */
    @Override
    public void run(Object o) {
        try{
            method.invoke(o);
        }catch (InvocationTargetException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
        // TODO

    }

}