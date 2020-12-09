package com.advetofcode.main.days;

import java.util.ArrayList;
import java.util.List;

public class ReportRepair {

    private static List<Integer> expenses = new ArrayList<>();

    public static void main(String[] args) {
        createList();

        var result = new Result();

        expenses.forEach(val1 -> expenses.forEach(val2 -> {
            if (!val1.equals(val2) && val1 + val2 == 6571) {
                result.val1 = val1;
                result.val2 = val2;
            }
        }));

/*        expenses.forEach(val1 -> expenses.forEach(val2 -> expenses.forEach(val3 ->{
            if (!val1.equals(val2) && !val2.equals(val3) && val1 + val2 + val3 == 6571) {
                result.val1 = val1;
                result.val2 = val2;
                result.val3 = val3;
            }
        })));*/


        System.out.println(result.toString());

        System.out.println("Result = " + result.val1 * result.val2 * result.val3);
    }

     static class Result {

         Integer val1;
         Integer val2;
         Integer val3;
         @Override
         public String toString() {
             return String.format("Val1 : '%s', Val2: '%s', Val3: '%s'", val1, val2, val3);
         }

     }

     private static void createTestList() {
        expenses.addAll(List.of(1721,
                979,
                366,
                299,
                675,
                1456));
     }

    private static void createList() {
        expenses.addAll(List.of(2238,
                2543,
                1789,
                1812,
                1842,
                1865,
                2143,
                2606,
                4241,
                3263,
                2901,
                5792,
                3563,
                4563,
                4268,
                3069,
                6464,
                2974,
                3084,
                3120,
                3199,
                3309,
                5041,
                3360,
                3487));
    }

}
