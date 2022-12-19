package com.itzamic.aoc.day05;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;

public class CraneOperator {
    private static Map<String, Deque<String>> stackDeque = new HashMap<>();

    static {
        setDeque();
    }

    private static void setDeque() {
        stackDeque.put("1", new ArrayDeque<>(List.of("[Z]", "[P]", "[M]", "[H]", "[R]")));
        stackDeque.put("2", new ArrayDeque<>(List.of("[P]", "[C]", "[J]", "[B]")));
        stackDeque.put("3", new ArrayDeque<>(List.of("[S]", "[N]", "[H]", "[G]", "[L]", "[C]", "[D]")));
        stackDeque.put("4", new ArrayDeque<>(List.of("[F]", "[T]", "[M]", "[D]", "[Q]", "[S]", "[R]", "[L]")));
        stackDeque.put("5", new ArrayDeque<>(List.of("[F]", "[S]", "[P]", "[Q]", "[B]", "[T]", "[Z]", "[M]")));
        stackDeque.put("6", new ArrayDeque<>(List.of("[T]", "[F]", "[S]", "[Z]", "[B]", "[G]")));
        stackDeque.put("7", new ArrayDeque<>(List.of("[N]", "[R]", "[V]")));
        stackDeque.put("8", new ArrayDeque<>(List.of("[P]", "[G]", "[L]", "[T]", "[D]", "[V]", "[C]", "[M]")));
        stackDeque.put("9", new ArrayDeque<>(List.of("[W]", "[Q]", "[N]", "[J]", "[F]", "[M]", "[L]")));
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day05\\input.txt"));
        int stackLines = getStackLines(strings);
        int bound = strings.size();

        for (int i = stackLines; i < bound; i++) {
            Coordinate transform = transform(strings.get(i));
            moveSingle(transform);
        }
        stackDeque.forEach((k, v) -> System.out.println("Key:" + k + " with top value: " + v.getLast()));

        stackDeque = new HashMap<>();
        setDeque();

        for (int i = stackLines; i < bound; i++) {
            Coordinate transform = transform(strings.get(i));
            moveMultiple(transform);
        }
        stackDeque.forEach((k, v) -> System.out.println("Key:" + k + " with top value: " + v.getLast()));
    }

    public static void moveSingle(Coordinate coordinate) {
        Deque<String> fromDeque = stackDeque.get(coordinate.from);
        Deque<String> toDeque = stackDeque.get(coordinate.to);
        for (int i = 1; i <= coordinate.num; i++) {
            toDeque.add(fromDeque.pollLast());
        }
    }
    private static void moveMultiple(Coordinate coordinate) {
        Deque<String> fromDeque = stackDeque.get(coordinate.from);
        Deque<String> toDeque = stackDeque.get(coordinate.to);
        List<String> elements = new ArrayList<>();
        if (coordinate.num > 1) {
            for (int i = 0; i < coordinate.num; i++) {
                elements.add(fromDeque.pollLast());
            }
            Collections.reverse(elements);
            toDeque.addAll(elements);
        } else {
            toDeque.add(fromDeque.pollLast());
        }
    }

    private static Coordinate transform(String s) {
        String[] split = s.replaceAll("[a-z]", "").trim().split("\\s+");
        return Coordinate.of(split[1], split[2], Integer.parseInt(split[0]));
    }

    private static int getStackLines(List<String> strings) {
        int i = 0;
        while (i < strings.size()) {
            if (strings.get(i).equals("")) {
                i++;
                break;
            }
            i++;
        }
        return i;
    }

    private static class Coordinate {
        private String from;
        private String to;
        private int num;

        public static Coordinate of(String from, String to, int num) {
            Coordinate coordinate = new Coordinate();
            coordinate.from = from;
            coordinate.to = to;
            coordinate.num = num;
            return coordinate;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Coordinate.class.getSimpleName() + "[", "]")
                    .add("from='" + from + "'")
                    .add("to='" + to + "'")
                    .add("num=" + num)
                    .toString();
        }
    }
}
