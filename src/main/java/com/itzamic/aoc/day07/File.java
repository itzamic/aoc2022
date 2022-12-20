package com.itzamic.aoc.day07;

import java.util.StringJoiner;

public class File implements Comparable<File> {
    private final String name;
    private int size;

    public File(String name) {
        this.name = name;
    }

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", File.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("size=" + size)
                .toString();
    }

    @Override
    public int compareTo(File o) {
        return this.size - o.size;
    }
}
