package com.advetofcode.main.year2020.days;

import java.io.IOException;
import java.util.ArrayList;

import com.advetofcode.main.CustomFileReader;

public class PasswordPhilosophy {

    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day2.txt";

    public static void main(String[] args) throws IOException {
        var inputString = customFileReader.readFile(path);

        var passwords = new ArrayList<PasswordDto>();

        inputString.forEach(input -> passwords.add(PasswordDto.create(input)));

        System.out.println(passwords.stream().count());

        var result = newPolicy(passwords);

        System.out.println(result);
    }

    private static int newPolicy(ArrayList<PasswordDto> passwords) {
        final int[] result = {0};
        passwords.forEach(pass -> {
            var correct = String.valueOf(pass.password.charAt(pass.min - 1));
            var incorrect = String.valueOf(pass.password.charAt(pass.max - 1));
            if (correct.equals(pass.letter) && !incorrect.equals(pass.letter) || !correct.equals(pass.letter) && incorrect.equals(pass.letter)) {
                result[0]++;
            }
        });
        return result[0];
    }

    private static int oldPolicy(ArrayList<PasswordDto> passwords) {
        final int[] result = {0};
        passwords.forEach(pass -> {
            var lastIndex = 0;
            var count = 0;
            while (lastIndex != -1) {
                lastIndex = pass.password.indexOf(pass.letter, lastIndex);

                if(lastIndex != -1) {
                    count ++;
                    lastIndex += 1;
                }
            }
            if (count <= pass.max && count >= pass.min) {
                result[0]++;
            }
        });
        return result[0];
    }

    static class PasswordDto {
        Integer min;
        Integer max;
        String letter;
        String password;

        public static PasswordDto create(String input) {
            var passwordDto = new PasswordDto();
            passwordDto.min = Integer.parseInt(input.substring(0, input.indexOf('-')));
            passwordDto.max = Integer.parseInt(input.substring(input.indexOf('-') + 1, input.indexOf(' ')));
            passwordDto.letter = input.substring(input.indexOf(' ') + 1, input.indexOf(':'));
            passwordDto.password = input.substring(input.indexOf(':') + 2);
            return passwordDto;
        }

        @Override
        public String toString() {
            return String.format("Min = '%s', Max = '%s', Letter: '%s', Password: '%s' %n", min, max, letter, password);
        }
    }
}
