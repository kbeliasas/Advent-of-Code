package com.advetofcode.main.days;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.advetofcode.main.CustomFileReader;

public class TobogganTrajectory {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day3.txt";

    public static void main(String[] args) throws IOException {
        var mapList = customFileReader.readFile(path);
        long[] trees = {0, 0, 0, 0 ,0};
        trees[0] = getTrees(mapList, 1, 1);
        trees[1] = getTrees(mapList, 3, 1);
        trees[2] = getTrees(mapList, 5, 1);
        trees[3] = getTrees(mapList, 7, 1);
        trees[4] = getTrees(mapList, 1, 2);
        var result = trees[0] * trees[1] * trees[2] * trees[3] * trees[4];
        System.out.println(Arrays.toString(trees));
        System.out.println(String.format("result: '%s'", result));
    }

    private static int getTrees(List<String> mapList, int right, int down) {
        final int[] rowCount = {0};
        final int[] position = {0};
        final int[] trees = {0};

        mapList.forEach(row -> {
            //System.out.println(rowCount[0]);
            if (rowCount[0] % down == 0) {
                var location = String.valueOf(row.charAt(position[0]));
                if ("#".equals(location)) {
                    trees[0]++;
                }
                position[0] += right;
                if (position[0] >= row.length()) {
                    position[0] = position[0] - row.length();
                }
            }
            rowCount[0]++;
        });
        return trees[0];
    }
}
