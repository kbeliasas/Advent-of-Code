package com.advetofcode.main.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.advetofcode.main.CustomFileReader;

public class EncodingError {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day9.txt";

    private static Integer preamble = 25;

    public static void main(String[] args) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var inputList = convertToInteger(rawDataList);

        var firstInvalid = getFirstInvalid(inputList);

        var result = getResult(inputList, firstInvalid);

        System.out.println(firstInvalid);

        System.out.println(result);

        var sum = result.min + result.max;

        System.out.println("Sum = " + sum);
    }

    private static Result getResult(List<Long> inputList, Long firstInvalid) {
        var iterator = 0;
        while (true) {
            var i = iterator;
            var count = 0L;
            var working = true;
            while (working) {
                count = count + inputList.get(i);
                if (count == firstInvalid) {
                    var sublist = inputList.subList(iterator, i);
                    var result = new Result();
                    result.min = sublist.stream()
                            .min(Comparator.comparingLong(input -> input))
                            .get();
                    result.max = sublist.stream()
                            .max(Comparator.comparingLong(input -> input))
                            .get();
                    return result;
                }
                if (count > firstInvalid) {
                    working = false;
                }
                i++;
            }
            iterator++;
        }
    }

    private static Long getFirstInvalid(List<Long> inputList) {
        var iterator = preamble + 1;
        while (true) {
            var input = inputList.get(iterator);
            var preambleStart = iterator - preamble;
            var preambleList = inputList.subList(preambleStart, preambleStart + preamble);
            var invalid = true;
            for (var preambleInput : preambleList) {
                for (var preambleInnerInput : preambleList) {
                    if (!preambleInput.equals(preambleInnerInput)) {
                        if (input == preambleInput + preambleInnerInput) {
                            invalid = false;
                        }
                    }
                }
            }
            if (invalid) {
                return input;
            }
            iterator++;
        }
    }

    private static List<Long> convertToInteger(List<String> inputList) {
        var resultList = new ArrayList<Long>();
        for (var input : inputList) {
            resultList.add(Long.parseLong(input));
        }
        return resultList;
    }

    static class Result {
        Long min;
        Long max;

        @Override
        public String toString() {
            return String.format("Result min: '%s', max: '%s'", min, max);
        }
    }
}
