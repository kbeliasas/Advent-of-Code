package com.advetofcode.main.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.advetofcode.main.CustomFileReader;

public class HandyHaversacks {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day7.txt";

    public static void main(String[] args) throws IOException {
        var input = customFileReader.readFile(path);

        var myBag = "shiny gold";

        var bags = input.stream()
                .map(Bag::createBag)
                .collect(Collectors.toList());

        var numberOfBags = countBags(bags, myBag);

        var allMyBags = countMyBags(bags, myBag);

        System.out.println(bags);
        System.out.println(numberOfBags);
        System.out.println(allMyBags);
    }

    private static Integer countMyBags(List<Bag> bags, String myBagName) {
        var count = 0;
        var myBag = bags.stream()
                .filter(bag -> bag.name.equals(myBagName))
                .findFirst().get();

        var myBags = new ArrayList<BagsNumbered>();

        myBags.add(new BagsNumbered(myBag, 1));
        var iterator = 0;
        var moreBags = true;
        while (moreBags) {
            var currentBagNumbered = myBags.get(iterator);
            var currentBag = currentBagNumbered.bag;
            for (Map.Entry<String, Integer> bag : currentBag.bags.entrySet()) {
                var numberedBag = new BagsNumbered(bags.stream()
                        .filter(bag1 -> bag1.name.equals(bag.getKey()))
                        .findFirst().get(), currentBagNumbered.numberOfBags * bag.getValue());
                myBags.add(numberedBag);
                count = count + numberedBag.numberOfBags;
            }
            iterator++;
            if (iterator >= myBags.size()) {
                moreBags = false;
            }
        }

        return count;

    }

    private static Integer countBags(List<Bag> bags, String myBag) {
        var bagsCount = new HashSet<String>();
        var moreBags = true;
        var searchBags = new ArrayList<String>();
        var foundBags = new ArrayList<String>();
        searchBags.add(myBag);
        while (moreBags) {
            for (var bag : bags) {
                for (var searchBag : searchBags) {
                    if (bag.bags.containsKey(searchBag)) {
                        foundBags.add(bag.name);
                        bagsCount.add(bag.name);
                    }
                }
            }
            if (foundBags.isEmpty()) {
                moreBags = false;
            }
            searchBags.clear();
            searchBags.addAll(foundBags);
            foundBags.clear();
        }
        return bagsCount.size();
    }

    static class Bag {
        String name;
        Map<String, Integer> bags;

        static Bag createBag(String input) {
            var bag = new Bag();

            bag.name = input.substring(0, input.indexOf("bags")).trim();

            var containedBags = new HashMap<String, Integer>();

            if (!input.contains("no other bags.")) {
                var currentIndex = 0;
                var moreBags = true;
                var containIndex = input.indexOf("contain ", currentIndex);
                var containLength = "contain ".length();
                currentIndex = containIndex;
                var numberString = input.substring(containIndex + containLength, input.indexOf("contain ") + containLength + 1);
                var number = Integer.parseInt(numberString);
                var bagName = input.substring(containIndex + containLength + 1, input.indexOf("bag", currentIndex)).trim();
                containedBags.put(bagName, number);
                var remainingInput = input.substring(input.indexOf(bagName) + bagName.length());
                while (moreBags) {
                    if (number == 1) {
                        if (remainingInput.equals(" bag.")) {
                            moreBags = false;
                        } else {
                            numberString = remainingInput.substring(remainingInput.indexOf("bag,") + 5, remainingInput.indexOf("bag,") + 6);
                            number = Integer.parseInt(numberString);
                            remainingInput = remainingInput.substring(remainingInput.indexOf(numberString) + 1);
                            bagName = remainingInput.substring(0, remainingInput.indexOf("bag")).trim();
                            remainingInput = remainingInput.substring(remainingInput.indexOf(bagName) + bagName.length());
                            containedBags.put(bagName, number);
                        }
                    } else {
                        if (remainingInput.equals(" bags.")) {
                            moreBags = false;
                        } else {
                            numberString = remainingInput.substring(remainingInput.indexOf("bags,") + 6, remainingInput.indexOf("bags,") + 7);
                            number = Integer.parseInt(numberString);
                            remainingInput = remainingInput.substring(remainingInput.indexOf(numberString) + 1);
                            bagName = remainingInput.substring(0, remainingInput.indexOf("bag")).trim();
                            remainingInput = remainingInput.substring(remainingInput.indexOf(bagName) + bagName.length());
                            containedBags.put(bagName, number);
                        }
                    }
                }
            }
            bag.bags = containedBags;
            return bag;
        }

        @Override
        public String toString() {
            return String.format("Name: '%s', Contains: '%s'%n", name, bags.toString());
        }
    }

    static class BagsNumbered {
        Bag bag;
        Integer numberOfBags;

        BagsNumbered(Bag bag, Integer numberOfBags) {
            this.bag = bag;
            this.numberOfBags = numberOfBags;
        }
    }
}
