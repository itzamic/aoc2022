package com.itzamic.aoc.day07;

import com.itzamic.aoc.utils.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FileExplorer {
    private static final String ROOT_DIR = "/";
    private static final String COMMAND = "$";
    private static final String DIRECTORY = "dir";

    private static final Directory EXPLORER;
    private static Directory current = new Directory(ROOT_DIR);
    private static int total;

    private static final int AT_MOST = 100000;
    private static final int TOTAL_SPACE = 70000000;

    private static final int NEEDED_SPACE = 30000000;
    private static final List<Directory> CANDIDATES = new ArrayList<>();

    static {
        current.setParent(current);
        EXPLORER = current;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(ResourceUtil.getPath("day07\\input.txt"));
        for (String input : strings) {
            if (input.startsWith(COMMAND)) {
                runCommand(input).ifPresent(value -> current = value);
                continue;
            }
            if (input.startsWith(DIRECTORY)) {
                current.getDirectories().add(new Directory(input.substring(4), current));
            } else {
                int index = input.indexOf(" ");
                File file = new File(input.substring(index + 1), Integer.parseInt(input.substring(0, index)));
                current.getFiles().add(file);
            }
        }
        findDirSize(EXPLORER);
        findDirsAtMost();
        System.out.println(total);

        Collections.sort(CANDIDATES);
        System.out.println(CANDIDATES.get(0).getSize());
    }

    private static void findDirsAtMost() {
        find(EXPLORER);
        int lookUpSpace = TOTAL_SPACE - EXPLORER.getSize();
        lookup(EXPLORER, lookUpSpace);
    }

    private static void lookup(Directory directory, int value) {
        List<Directory> directories = directory.getDirectories();
        if (directories.size() != 0) {
            for (Directory dir : directories) {
                lookup(dir, value);
            }
        }
        if (NEEDED_SPACE < directory.getSize() + value) {
            CANDIDATES.add(directory);
        }
    }

    private static void find(Directory directory) {
        List<Directory> directories = directory.getDirectories();
        if (directories.size() != 0) {
            for (Directory dir : directories) {
                find(dir);
            }
        }
        if (directory.getSize() <= AT_MOST) {
            total += directory.getSize();
        }
    }

    private static void findDirSize(Directory directory) {
        List<Directory> directories = directory.getDirectories();
        int sum = 0;
        if (directories.size() != 0) {
            for (Directory dir : directories) {
                findDirSize(dir);
                sum += dir.getSize();
            }
        }
        directory.setSize(sum + directory.getFilesSize());
    }

    private static Optional<Directory> runCommand(String input) {
        if (input.startsWith(COMMAND + " " + "cd")) {
            if (input.endsWith("..")) {
                return Optional.of(current.getParent());
            }
            return current.getDirectories()
                    .stream()
                    .filter(e -> e.getName().equals(input.substring(5)))
                    .findFirst();
        }
        return Optional.empty();
    }
}
