package com.itzamic.aoc.day01;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Calories {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day01\\input.txt"));
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
}

