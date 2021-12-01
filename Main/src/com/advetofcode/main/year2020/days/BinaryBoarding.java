package com.advetofcode.main.year2020.days;

import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.advetofcode.main.CustomFileReader;

public class BinaryBoarding {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day5.txt";

    public static void main(String[] args) throws IOException {
        var input = customFileReader.readFile(path);

        var planeSeats = input.stream()
                .map(BinaryBoarding::getSeat)
                .sorted(Comparator.comparingInt(planeSeat -> planeSeat.seatID))
                .collect(Collectors.toList());

        System.out.println(planeSeats);

        var seatWithMaxID = planeSeats.stream()
                .max(Comparator.comparingInt(planeSeat -> planeSeat.seatID))
                .get();

        System.out.println("");
        System.out.println("Seat with max ID: " + seatWithMaxID);

        var i = 0;
        for (var seat: planeSeats) {
            if (seat.seatID + 1 != planeSeats.get(i + 1).seatID) {
                System.out.println("My seat ID: " + seat.seatID + 1);
            }
            i++;
        }

    }

    private static PlaneSeat getSeat(String binary) {
        var planeSeat = new PlaneSeat();
        var rowBinaries = binary.substring(0, 7);
        var columnBinaries = binary.substring(7);

        var minRow = 0;
        var maxRow = 127;
        var rows = 128;
        for (var partition : rowBinaries.toCharArray()) {
            rows = rows / 2;
            if ("F".equals(String.valueOf(partition))) {
                maxRow = maxRow - rows;
            } else {
                minRow = minRow + rows;
            }
        }

        var minColumn = 0;
        var maxColumn = 7;
        var columns = 8;
        for (var partition : columnBinaries.toCharArray()) {
            columns = columns / 2;
            if ("L".equals(String.valueOf(partition))) {
                maxColumn = maxColumn - columns;
            } else {
                minColumn = minColumn + columns;
            }
        }

        if (maxRow == minRow) {
            planeSeat.row = maxRow;
        }

        if (maxColumn == minColumn) {
            planeSeat.setColumn(maxColumn);
        }

        return planeSeat;
    }

    static class PlaneSeat {
        Integer row;
        Integer column;
        Integer seatID;

        public void setColumn(Integer column) {
            this.column = column;
            seatID = row * 8 + column;
        }

        @Override
        public String toString() {
            return String.format("Plane row: '%s', Column: '%s', SeatID: '%s'%n", row, column, seatID);
        }
    }
}
