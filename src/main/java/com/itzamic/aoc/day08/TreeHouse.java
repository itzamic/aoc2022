package com.itzamic.aoc.day08;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TreeHouse {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day08\\input.txt"));
        System.out.println(countNumOfVisible(strings));
        System.out.println(countScenicScore(strings));
    }

    private static int countScenicScore(List<String> strings) {
        int listSize = strings.size();
        int columnSize = strings.get(0).length();
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                trees.add(getTreeWithScore(strings, i, j));
            }
        }
        List<Tree> collect = trees.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return collect.get(0).getScore();
    }

    private static Tree getTreeWithScore(List<String> strings, int i, int j) {
        int i1 = countTop(strings, i, j);
        int i2 = countBottom(strings, i, j);
        int i3 = countLeft(strings, i, j);
        int i4 = countRight(strings, i, j);
        return new Tree(i, j, i1 * i2 * i3 * i4);
    }

    private static int countRight(List<String> strings, int i, int j) {
        if (j == strings.get(i).length() - 1) {
            return 0;
        }
        int count = 0;
        char thisLineColumnChar = strings.get(i).toCharArray()[j];
        for (int k = j + 1; k < strings.get(i).length(); k++) {
            char previousLineColumnChar = strings.get(i).toCharArray()[k];
            if (previousLineColumnChar < thisLineColumnChar) {
                count++;
            } else if (previousLineColumnChar == thisLineColumnChar) {
                return ++count;
            } else {
                return count;
            }
        }
        return count;
    }

    private static int countLeft(List<String> strings, int i, int j) {
        if (j == 0) {
            return 0;
        }
        int count = 0;
        char thisLineColumnChar = strings.get(i).toCharArray()[j];
        for (int k = j - 1; k >= 0; k--) {
            char previousLineColumnChar = strings.get(i).toCharArray()[k];
            if (previousLineColumnChar < thisLineColumnChar) {
                count++;
            } else if (previousLineColumnChar == thisLineColumnChar) {
                return ++count;
            } else {
                return count;
            }
        }
        return count;
    }

    private static int countBottom(List<String> strings, int i, int j) {
        if (i == strings.size() - 1) {
            return 0;
        }
        int count = 0;
        char thisLineChar = strings.get(i).toCharArray()[j];
        for (int k = i + 1; k < strings.size(); k++) {
            char previousLineChar = strings.get(k).toCharArray()[j];
            if (previousLineChar < thisLineChar) {
                count++;
            } else if (previousLineChar == thisLineChar) {
                return ++count;
            } else {
                return count;
            }
        }
        return count;
    }

    private static int countTop(List<String> strings, int i, int j) {
        if (i == 0) {
            return 0;
        }
        int count = 0;
        char thisLineChar = strings.get(i).toCharArray()[j];
        for (int k = i - 1; k >= 0; k--) {
            char previousLineChar = strings.get(k).toCharArray()[j];
            if (previousLineChar < thisLineChar) {
                count++;
            } else if (previousLineChar == thisLineChar) {
                return ++count;
            } else {
                return count;
            }
        }
        return count;
    }

    private static int countNumOfVisible(List<String> strings) {
        int count = 0;
        int listSize = strings.size();
        int columnSize = strings.get(0).length();
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (i == 0 || i == listSize - 1 || j == 0 || j == columnSize - 1) {
                    System.out.print(ANSI_GREEN + strings.get(i).toCharArray()[j] + ANSI_RESET);
                    count++;
                    continue;
                }
                if (isVisible(strings, i, j)) {
                    count++;
                } else {
                    System.out.print(ANSI_RED + strings.get(i).toCharArray()[j] + ANSI_RESET);
                }
            }
            System.out.println();
        }
        return count;
    }

    private static boolean isVisible(List<String> strings, int i, int j) {
        return visibleFromTop(strings, i, j)
                || visibleFromBottom(strings, i, j)
                || visibleFromRight(strings, i, j)
                || visibleFromLeft(strings, i, j);
    }

    private static boolean visibleFromLeft(List<String> strings, int i, int j) {
        for (int k = j - 1; k >= 0; k--) {
            if (strings.get(i).toCharArray()[j] <= strings.get(i).toCharArray()[k]) {
                return false;
            }
        }
        System.out.print(ANSI_YELLOW + strings.get(i).toCharArray()[j] + ANSI_RESET);
        return true;
    }

    private static boolean visibleFromRight(List<String> strings, int i, int j) {
        for (int k = j + 1; k < strings.get(i).length(); k++) {
            if (strings.get(i).toCharArray()[j] <= strings.get(i).toCharArray()[k]) {
                return false;
            }
        }
        System.out.print(ANSI_PURPLE + strings.get(i).toCharArray()[j] + ANSI_RESET);
        return true;
    }

    private static boolean visibleFromBottom(List<String> strings, int i, int j) {
        for (int k = i + 1; k < strings.size(); k++) {
            if (strings.get(i).toCharArray()[j] <= strings.get(k).toCharArray()[j]) {
                return false;
            }
        }
        System.out.print(ANSI_BLUE + strings.get(i).toCharArray()[j] + ANSI_RESET);
        return true;
    }

    private static boolean visibleFromTop(List<String> strings, int i, int j) {
        for (int k = i - 1; k >= 0; k--) {
            if (strings.get(i).toCharArray()[j] <= strings.get(k).toCharArray()[j]) {
                return false;
            }
        }
        System.out.print(ANSI_CYAN + strings.get(i).toCharArray()[j] + ANSI_RESET);
        return true;
    }
}
