package com.itzamic.aoc.day02;

import com.itzamic.aoc.utils.ResourceUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

public class RPS {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day02\\input.txt"));
        int sum = strings.stream()
                .map(line ->
                        Pair.of(RPSType.getTypeOf(line.charAt(0)), RPSType.getTypeOf(line.charAt(2)))
                )
                .mapToInt(RPSType::getPoints)
                .sum();
        System.out.println(sum);
        int sum1 = strings.stream()
                .map(RPSType::getResultTypeOf)
                .mapToInt(RPSType::getPoints)
                .sum();
        System.out.println(sum1);
    }
}
