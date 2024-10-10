package infinis;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class TestStreamDeNombres {

    private static double[] doubles;

    public static void main(String[] args) {
        doubles = new Random().doubles(10,0,100).toArray();

        System.out.println("1. Moyenne des racines carrée = "  + moyenneDesRacinesCarrees());
        System.out.println("\n2. Génération de streams infinis de doubles");
        listeDe10PuisTabDe20DoubleStream();
        listeDe10PuisTabDe20StreamDouble();
        System.out.println("\n3. 20 nombres entiers entre 0 et 100");
        System.out.println(
                Stream.generate(() -> (int) (Math.random() * 50) * 2).limit(20).collect(Collectors.toList())
        );

    }

    /**
     * cf. point 1
     */
    private static double moyenneDesRacinesCarrees() {
        return DoubleStream.of(doubles)
                .map(Math::sqrt)
                .average()
                .getAsDouble();
    }

    /**
     * cf. point 2
     */
    private static DoubleStream randomDoubleStream(double maxValue) {
        return DoubleStream.generate(() -> Math.random() * maxValue);
    }

    /**
     * cf. point 2
     */
    private static Stream<Double> randomStreamDouble(double maxValue) {
        return Stream.generate(() -> Math.random() * maxValue);
    }

    /**
     * cf. point 2
     */
    private static void listeDe10PuisTabDe20DoubleStream() {
        List<Double> numsList = randomDoubleStream(100)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(numsList);

        double[] numTab = randomDoubleStream(100)
                .limit(20)
                .toArray();
        System.out.print("{ ");
        for(double d : numTab) System.out.print(d + " ");
        System.out.println("}");
    }

    /**
     * cf. point 2
     */
    private static void listeDe10PuisTabDe20StreamDouble() {
        List<Double> numsList = randomStreamDouble(100)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(numsList);

        double[] numTab = randomStreamDouble(100)
                .limit(20)
                .mapToDouble(x -> x)
                .toArray();
        System.out.print("{ ");
        for(double d : numTab) System.out.print(d + " ");
        System.out.println("}");
    }

}
