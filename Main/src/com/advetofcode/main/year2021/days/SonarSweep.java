package com.advetofcode.main.year2021.days;

import static java.lang.Long.parseLong;

import java.io.IOException;
import java.util.ArrayList;

import com.advetofcode.main.CustomFileReader;

public class SonarSweep {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path1 = "/Users/nsj7529/repository/Advent-of-Code/Main/src/com/advetofcode/main/year2021/resources/Day1_Par1_test.txt";
    private static String path2 = "/Users/nsj7529/repository/Advent-of-Code/Main/src/com/advetofcode/main/year2021/resources/Day1_Par1.txt";

    // PART1
    public static void main(String[] args) throws IOException {
        //part1(path1);
        part2(path2);
    }
    // PART2
    private static void part1(String path) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var count = 0;

        Long last = null;

        for (var line : rawDataList) {
            if (last != null && parseLong(line) > last) {
                count++;
            }
            last = parseLong(line);
        }

        System.out.println(count);
    }

    private static void part2(String path) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var modifiedDataList = new ArrayList<Long>();

        for (int i = 2; i < rawDataList.size(); i++) {
            var newLong = parseLong(rawDataList.get(i-2)) + parseLong(rawDataList.get(i-1)) + parseLong(rawDataList.get(i));
            modifiedDataList.add(newLong);
        }

        //System.out.println(modifiedDataList);

        var count = 0;

        Long last = null;

        for (var line : modifiedDataList) {
            if (last != null && line > last) {
                count++;
            }
            last = line;
        }

        System.out.println(count);
    }
}
