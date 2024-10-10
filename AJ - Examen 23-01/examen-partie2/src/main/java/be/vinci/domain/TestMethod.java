package be.vinci.domain;

import java.lang.reflect.Method;

public interface TestMethod {

  Method getMethod();

  void setMethod(Method method);

  String getName();

  void setName(String name);

  void run(Object o);
}
