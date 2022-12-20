package com.itzamic.aoc.day06;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommunicationSystem {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day06\\input.txt"));
        String data = strings.get(0);
        print(data, 4);
        print(data, 14);
    }

    private static void print(String data, int messageLength) {
        outer:
        for (int i = 0; i < data.length() - messageLength; i++) {
            for (int j = i + 1; j < i + messageLength + 1; j++) {
                String substring = data.substring(i, j);
                if (substring.length() == messageLength) {
                    if (hasUniqueChars(substring)) {
                        System.out.println(i + messageLength);
                        System.out.println(substring);
                        break outer;
                    }
                    break;
                }
            }
        }
    }

    private static boolean hasUniqueChars(String substring) {
        char[] chars = substring.toCharArray();
        Set<Character> unique = new HashSet<>();
        for (char c : chars) {
            if (!unique.add(c)) {
                return false;
            }
        }
        return true;
    }
}
