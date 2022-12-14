package com.itzamic.aoc.day3;

import com.itzamic.aoc.utils.ResourceUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

public class Rucksack {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day03\\input.txt"));
        int sum = strings.stream()
                .map(Rucksack::getOf)
                .map(Rucksack::findChar)
                .mapToInt(Rucksack::replaceWithNumber)
                .sum();
        System.out.println(sum);
        int total = 0;
        for (int i = 0; i < strings.size(); i += 3) {
            total += replaceWithNumber(findCommonChar(strings, i));
        }
        System.out.println(total);
    }

    private static char findCommonChar(List<String> strings, int i) {
        char[] first = strings.get(i).toCharArray();
        char[] second= strings.get(i+1).toCharArray();
        char[] third= strings.get(i+2).toCharArray();
        for (char a : first) {
            for (char b: second) {
                if (a == b) {
                    for (char c: third) {
                        if (b == c) {
                            return b;
                        }
                    }
                }
            }
        }
        return 0;
    }

    private static Pair<String, String> getOf(String a) {
        return Pair.of(a.substring(0, a.length() / 2), a.substring(a.length() / 2));
    }

    private static int replaceWithNumber(Character character) {
        switch (character) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            case 'i':
                return 9;
            case 'j':
                return 10;
            case 'k':
                return 11;
            case 'l':
                return 12;
            case 'm':
                return 13;
            case 'n':
                return 14;
            case 'o':
                return 15;
            case 'p':
                return 16;
            case 'q':
                return 17;
            case 'r':
                return 18;
            case 's':
                return 19;
            case 't':
                return 20;
            case 'u':
                return 21;
            case 'v':
                return 22;
            case 'w':
                return 23;
            case 'x':
                return 24;
            case 'y':
                return 25;
            case 'z':
                return 26;
            default:
                return replaceWithNumber(String.valueOf(character).toLowerCase().charAt(0)) + 26;
        }

    }

    private static char findChar(Pair<String, String> p) {
        char[] first = p.getLeft().toCharArray();
        char[] second = p.getRight().toCharArray();
        for (char value : first) {
            for (char c : second) {
                if (value == c) {
                    return value;
                }
            }
        }
        return 0;
    }
}
