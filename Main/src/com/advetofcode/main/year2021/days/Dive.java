package com.advetofcode.main.year2021.days;

import java.io.IOException;

import com.advetofcode.main.CustomFileReader;

public class Dive {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path1 = "/Users/nsj7529/repository/Advent-of-Code/Main/src/com/advetofcode/main/year2021/resources/Day2_Part1_test.txt";
    private static String path2 = "/Users/nsj7529/repository/Advent-of-Code/Main/src/com/advetofcode/main/year2021/resources/Day2_Part1.txt";

    private static String FORWARD = "forward ";
    private static String UP = "down ";
    private static String DOWN = "up ";

    public static void main(String[] args) throws IOException {
        //part1(path2);
        part2(path2);
    }

    private static void part1(String path) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var x = 0L;
        var depth = 0L;

        for (var line : rawDataList) {
            if (line.contains(FORWARD)) {
                x += Long.parseLong(line.replace(FORWARD, ""));
            }
            if (line.contains(UP)) {
                depth += Long.parseLong(line.replace(UP, ""));
            }
            if (line.contains(DOWN)) {
                depth -= Long.parseLong(line.replace(DOWN, ""));
            }
        }

        System.out.println("x = " + x);
        System.out.println("depth = " + depth);

        System.out.println(x * depth);

    }

    private static void part2(String path) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var x = 0L;
        var depth = 0L;
        var aim = 0L;

        for (var line : rawDataList) {
            if (line.contains(FORWARD)) {
                x += Long.parseLong(line.replace(FORWARD, ""));
                depth += Long.parseLong(line.replace(FORWARD, "")) * aim;
            }
            if (line.contains(UP)) {
                aim += Long.parseLong(line.replace(UP, ""));
            }
            if (line.contains(DOWN)) {
                aim -= Long.parseLong(line.replace(DOWN, ""));
            }
        }

        System.out.println("x = " + x);
        System.out.println("depth = " + depth);

        System.out.println(x * depth);
    }
}
