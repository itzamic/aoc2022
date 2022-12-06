package com.itzamic.aoc.day01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Calories {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> strings = Files.readAllLines(getPath());
        List<Integer> calories = new ArrayList<>();
        int i = 0;
        int sum = 0;
        for (String line : strings) {
            if (line.equals("")) {
                calories.add(i, sum);
                i++;
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(line);
        }
        System.out.println(getMax(calories));

    }

    private static int getMax(List<Integer> calories) {
        return calories
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    private static Path getPath() throws URISyntaxException {
        return Paths.get(Objects.requireNonNull(getResource()).toURI());
    }

    private static URL getResource() {
        return Thread.currentThread().getContextClassLoader().getResource("day01\\input.txt");
    }
}

