package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import services.StreamStatsService;

import java.io.FileInputStream;
import java.util.Properties;

public class InjectionService {

    private static Properties props = new Properties();

    static {
        try (FileInputStream file = new FileInputStream("dependencies.properties")) {
            props.load(file);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an instance (of the implementation) of the given Service Object Interface.
     * Looks into the "dependencies.properties" file to know the implementation of the Interface.
     * @param c The interface of the Service Object
     * @param <T> Generic type of the implementation
     * @return The Service Object Implementation Instance
     */
    public static <T> T getDependency(Class<?> c) {
        // TODO (question 4)
        String dependencies = props.getProperty(c.getSimpleName());

        try {
            Constructor constructor = Class.forName(dependencies).getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = (T) constructor.newInstance();
            constructor.setAccessible(false);
            return (T) obj;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
            InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}