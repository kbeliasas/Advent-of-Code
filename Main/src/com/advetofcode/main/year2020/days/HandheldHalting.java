package com.advetofcode.main.year2020.days;

import static com.advetofcode.main.year2020.days.HandheldHalting.Instructions.ACCUMULATOR;
import static com.advetofcode.main.year2020.days.HandheldHalting.Instructions.JUMP;
import static com.advetofcode.main.year2020.days.HandheldHalting.Instructions.NO_OPERATION;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import com.advetofcode.main.CustomFileReader;

public class HandheldHalting {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day8.txt";

    public static void main(String[] args) throws IOException {
        var inputList = customFileReader.readFile(path);

        var working = true;
        var iterator = 0;
        var accumulator = 0;
        var set = new HashSet<Integer>();
        while (working) {
            var input = inputList.get(iterator);
            if (set.contains(iterator)) {
                working = false;
            } else {
                set.add(iterator);
                if (input.contains(ACCUMULATOR.code)) {
                    accumulator = accumulator + Integer.parseInt(input.substring(input.indexOf(ACCUMULATOR.code) + ACCUMULATOR.code.length() + 1));
                    iterator++;
                }
                if (input.contains(Instructions.NO_OPERATION.code)) {
                    iterator++;
                }
                if (input.contains(JUMP.code)) {
                    iterator = iterator + Integer.parseInt(input.substring(input.indexOf(JUMP.code) + JUMP.code.length() + 1));
                }
            }
        }

        var maxIterator = set.stream()
                .max(Comparator.comparingInt(iterator1 -> iterator1))
                .get();

        System.out.println("Max iterator: " + maxIterator);
        System.out.println("Max command: " + inputList.get(maxIterator));

        var goodAccumulator = fixCode(inputList);

        System.out.println(accumulator);
        System.out.println(goodAccumulator);

    }

    private static Integer fixCode(List<String> inputList) {
        var looping = true;
        var finalAccumulator = 0;
        var triedInputs = new HashSet<Integer>();
        var iterator = 0;
        while (looping) {
            iterator = 0;
            var working = true;
            var accumulator = 0;
            var set = new HashSet<Integer>();
            var changed = false;
            while (working) {
                if (triedInputs.contains(iterator) || changed) {
                    if (iterator >= inputList.size()) {
                        looping = false;
                        finalAccumulator = accumulator;
                        break;
                    }
                    var input = inputList.get(iterator);
                    if (set.contains(iterator)) {
                        working = false;
                    } else {
                        set.add(iterator);
                        if (input.contains(ACCUMULATOR.code)) {
                            accumulator = accumulator + Integer.parseInt(input.substring(input.indexOf(ACCUMULATOR.code) + ACCUMULATOR.code.length() + 1));
                            iterator++;
                        }
                        if (input.contains(Instructions.NO_OPERATION.code)) {
                            iterator++;
                        }
                        if (input.contains(JUMP.code)) {
                            iterator = iterator + Integer.parseInt(input.substring(input.indexOf(JUMP.code) + JUMP.code.length() + 1));
                        }
                    }
                } else {
                    changed = true;
                    triedInputs.add(iterator);
                    var input = inputList.get(iterator);

                    if (input.contains(ACCUMULATOR.code)) {
                        accumulator = accumulator + Integer.parseInt(input.substring(input.indexOf(ACCUMULATOR.code) + ACCUMULATOR.code.length() + 1));
                        iterator++;
                    }

                    if (input.contains(NO_OPERATION.code)) {
                        iterator = iterator + Integer.parseInt(input.substring(input.indexOf(NO_OPERATION.code) + NO_OPERATION.code.length() + 1));
                    }
                    if (input.contains(JUMP.code)) {
                        iterator++;
                    }

                }
            }
        }
        return finalAccumulator;
    }

    enum Instructions {
        ACCUMULATOR("acc"),
        JUMP("jmp"),
        NO_OPERATION("nop");

        String code;

        Instructions(String code) {
            this.code = code;
        }
    }
}
