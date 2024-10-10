package be.vinci.domain;

import java.lang.reflect.Method;

public interface TestMethod {
    public Method getMethod();
    public void run(Object o);
    public String getName();
    public void setMethod(Method method);
    public void setName(String name);

}
