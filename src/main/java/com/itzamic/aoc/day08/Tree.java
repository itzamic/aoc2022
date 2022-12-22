package com.itzamic.aoc.day08;

public class Tree implements Comparable<Tree>{
    private int x;
    private int y;
    private int scenicScore;

    public Tree(int x, int y, int score) {
        this.x = x;
        this.y = y;
        this.scenicScore = score;
    }

    public int getScore() {
        return scenicScore;
    }

    public void setScenicScore(int value) {
        this.scenicScore = value;
    }

    @Override
    public int compareTo(Tree o) {
        return scenicScore - o.scenicScore;
    }
}
