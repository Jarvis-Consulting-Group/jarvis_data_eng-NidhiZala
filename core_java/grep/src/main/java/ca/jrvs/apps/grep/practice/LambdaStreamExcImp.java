package ca.jrvs.apps.grep.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Stream.of(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(s -> !s.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.asDoubleStream().map(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(n -> n % 2 != 0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return message -> System.out.println(prefix + message + suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        Stream.of(messages).forEach(printer);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        getOdd(intStream).mapToObj(Integer::toString).forEach(printer);
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        // Solution using flatMap
        return ints.flatMap(List::stream).map(n -> n * n);
    }

    public static void main(String[] args) {
        LambdaStreamExc lse = new LambdaStreamExcImp();

        // Testing createStrStream
        lse.createStrStream("one", "two", "three").forEach(System.out::println);

        // Testing toUpperCase
        lse.toUpperCase("one", "two", "three").forEach(System.out::println);

        // Testing filter
        lse.filter(lse.createStrStream("one", "two", "three"), "o").forEach(System.out::println);

        // Testing createIntStream
        lse.createIntStream(new int[]{1, 2, 3}).forEach(System.out::println);

        // Testing toList
        List<String> strList = lse.toList(lse.createStrStream("one", "two", "three"));
        List<Integer> intList = lse.toList(lse.createIntStream(1, 3));
        System.out.println(strList);
        System.out.println(intList);

        // Testing createIntStream
        lse.createIntStream(1, 3).forEach(System.out::println);

        // Testing squareRootIntStream
        lse.squareRootIntStream(lse.createIntStream(1, 3)).forEach(System.out::println);

        // Testing getOdd
        lse.getOdd(lse.createIntStream(new int[]{1, 2, 3})).forEach(System.out::println);

        // Testing getLambdaPrinter
        Consumer<String> printer = lse.getLambdaPrinter("prefix-", "-suffix");
        printer.accept("message");

        // Testing printMessages
        String[] messages = {"one", "two", "three"};
        lse.printMessages(messages, System.out::println);

        // Testing printOdd
        lse.printOdd(lse.createIntStream(new int[]{1, 2, 3, 4, 5}), System.out::println);

        // Testing flatNestedInt
        Stream<List<Integer>> nestedInts = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(6));
        lse.flatNestedInt(nestedInts).forEach(System.out::println);
    }
}