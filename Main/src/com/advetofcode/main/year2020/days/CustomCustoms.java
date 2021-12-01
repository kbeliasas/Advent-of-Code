package com.advetofcode.main.year2020.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.advetofcode.main.CustomFileReader;

public class CustomCustoms {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day6.txt";

    public static void main(String[] args) throws IOException {
        var input = customFileReader.readFile(path);

        var groups = processData(input);

        System.out.println(groups);

        var countDistinct = 0;
        var countAll = 0;

        for (var group : groups) {
            countDistinct += countDistinct(group);
            countAll += countAll(group);
        }

        System.out.println(countDistinct);
        System.out.println(countAll);

    }

    static class Group {
        List<String> persons;

        @Override
        public String toString() {
            return String.format("%s%n", persons.toString());
        }
    }

    private static List<Group> processData(List<String> input) {
        var groups = new ArrayList<Group>();
        var group = new Group();
        group.persons = new ArrayList<>();

        for (var line : input) {
            if (line.isEmpty()) {
                groups.add(group);
                group = new Group();
                group.persons = new ArrayList<>();
            } else {
                group.persons.add(line);
            }
        }
        groups.add(group);
        return groups;
    }

    private static Integer countDistinct(Group group) {
        var set = new HashSet();
        for (var person : group.persons) {
            for (var question : person.toCharArray()) {
                set.add(question);
            }
        }
        return set.size();
    }

    private static Integer countAll(Group group) {
        var set = new HashSet<>();
        for (var person : group.persons) {
            for (var question : person.toCharArray()) {
                var count = 0;
                for (var persons1 : group.persons) {
                    for (var question1 : persons1.toCharArray()) {
                        if (question == question1) {
                            count++;
                        }
                    }
                }
                if (count == group.persons.size()) {
                    set.add(question);
                }
            }
        }
        return set.size();
    }
}
