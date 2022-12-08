package com.itzamic.aoc.day02;

import org.apache.commons.lang3.tuple.Pair;

public enum RPSType {
    ROCK(1),
    PAPER(2),
    SCISSOR(3);

    private final int value;

    RPSType(int value) {
        this.value = value;
    }

    public static RPSType getTypeOf(char c) {
        switch (c) {
            case 'A':
            case 'X':
                return RPSType.ROCK;
            case 'B':
            case 'Y':
                return RPSType.PAPER;
            default:
                return RPSType.SCISSOR;
        }
    }

    public static int getPoints(Pair<RPSType, RPSType> pair) {
        RPSType elf = pair.getLeft();
        RPSType you = pair.getRight();
        if (elf.equals(you)) {
            return you.getValue() + 3;
        } else if (you.equals(ROCK)) {
            if (elf.equals(SCISSOR)) {
                return you.getValue() + 6;
            } else {
                return you.getValue();
            }
        } else if (you.equals(PAPER)) {
            if (elf.equals(ROCK)) {
                return you.getValue() + 6;
            } else {
                return you.getValue();
            }
        } else if (you.equals(SCISSOR)) {
            if (elf.equals(PAPER)) {
                return you.getValue() + 6;
            } else {
                return you.getValue();
            }
        }
        return 0;
    }

    public static Pair<RPSType, RPSType> getResultTypeOf(String line) {
        RPSType elf = getTypeOf(line.charAt(0));
        switch (line.charAt(2)) {
            case 'X':
                switch (elf) {
                    case ROCK:
                        return Pair.of(elf, SCISSOR);
                    case PAPER:
                        return Pair.of(elf, ROCK);
                    case SCISSOR:
                        return Pair.of(elf, PAPER);
                }
            case 'Z':
                switch (elf) {
                    case ROCK:
                        return Pair.of(elf, PAPER);
                    case PAPER:
                        return Pair.of(elf, SCISSOR);
                    case SCISSOR:
                        return Pair.of(elf, ROCK);
                }
            default:
                return Pair.of(elf, elf);
        }
    }

    public int getValue() {
        return value;
    }
}
