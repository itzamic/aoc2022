package com.itzamic.aoc.day04;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class FullyContainArea {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day04\\input.txt"));
        List<List<List<Integer>>> collect = strings.stream()
                .map(s -> s.split(","))
                .map(Arrays::asList)
                .map(FullyContainArea::generateNumbers)
                .filter(a -> new HashSet<>(a.get(0)).containsAll(a.get(1)) || new HashSet<>(a.get(1)).containsAll(a.get(0)))
                .collect(Collectors.toList());
        System.out.println(collect.size());

        List<List<List<Integer>>> collect1 = strings.stream()
                .map(s -> s.split(","))
                .map(Arrays::asList)
                .map(FullyContainArea::generateNumbers)
                .filter(FullyContainArea::check)
                .collect(Collectors.toList());
        System.out.println(collect1.size());

    }

    private static boolean check(List<List<Integer>> a) {
        List<Integer> first = a.get(0);
        List<Integer> second = a.get(1);
        boolean b = first.stream().anyMatch(second::contains);
        boolean b1 = second.stream().anyMatch(first::contains);
        return b && b1;

    }

    public static List<List<Integer>> generateNumbers(List<String> pair) {
        List<List<Integer>> ints = new ArrayList<>(2);
        for (String width : pair) {
            int index = width.indexOf("-");
            int start = Integer.parseInt(width.substring(0, index));
            int finish = Integer.parseInt(width.substring(index+1));
            List<Integer> numbers = new ArrayList<>(finish-start);
            for (int i=start; i<=finish; i++) {
                numbers.add(i);
            }
             ints.add(numbers);
        }
        return ints;


    }
}
