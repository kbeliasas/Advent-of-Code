package com.advetofcode.main.year2021.days;

import java.io.IOException;

import com.advetofcode.main.CustomFileReader;

public class SonarSweep {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-Code/Main/src/com/advetofcode/main/year2021/resources/Day1_Par1.txt";

    public static void main(String[] args) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var count = 0;

        Long last = null;

        for (var line : rawDataList) {
            if (last != null && Long.parseLong(line) > last) {
                count++;
            }
            last = Long.parseLong(line);
        }

        System.out.println(count);
    }
}
