package be.vinci.domain;

public interface TestClass {
    void runAllTests();

    void loadFromClass(Class aClass);

    void setDomaineFactory(DomaineFactory domaineFactory);
}
