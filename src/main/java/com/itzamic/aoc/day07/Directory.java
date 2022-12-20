package com.itzamic.aoc.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Directory extends File {
    private List<Directory> directories;
    private List<File> files;

    private Directory parent;

    public Directory(String name, Directory parent) {
        super(name);
        this.parent = parent;
    }

    public Directory(String name) {
        super(name);
    }

    public List<Directory> getDirectories() {
        if (directories == null) {
            directories = new ArrayList<>();
        }
        return directories;
    }

    public List<File> getFiles() {
        if (files == null) {
            files = new ArrayList<>();
        }
        return files;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Directory.class.getSimpleName() + "[", "]")
                .add("directories=" + directories)
                .add("files=" + files)
                .toString();
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public int getFilesSize() {
        return getFiles()
                .stream()
                .mapToInt(File::getSize)
                .reduce(0, Integer::sum);
    }
}
