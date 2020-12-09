package com.advetofcode.main.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.advetofcode.main.CustomFileReader;

public class PassportProcessing {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day4.txt";

    public static void main(String[] args) throws IOException {
        var input = customFileReader.readFile(path);

        var passports = processData(input);

        System.out.println(passports);

        var numberOfValid = passports.stream()
                .map(Passport::isValid)
                .filter(Boolean.TRUE::equals)
                .count();

        System.out.println(String.format("Number of valid passports: '%s'", numberOfValid));
    }

    private static List<Passport> processData(List<String> input) {
        var passports = new ArrayList<Passport>();
        var passport = new Passport();

        for (var line : input) {

            if (line.isEmpty()) {
                passports.add(passport);
                passport = new Passport();
            } else {
                for (var passportCode : PassportCodes.values()) {
                    switch (passportCode) {
                        case BIRTH_YEAR -> passport.birthYear = getValue(passportCode, line, passport.birthYear);
                        case ISSUE_YEAR -> passport.issueYear = getValue(passportCode, line, passport.issueYear);
                        case EXPIRATION_YEAR -> passport.expirationYear = getValue(passportCode, line, passport.expirationYear);
                        case HEIGHT -> passport.height = getValue(passportCode, line, passport.height);
                        case HAIR_COLOR -> passport.hairColor = getValue(passportCode, line, passport.hairColor);
                        case EYE_COLOR -> passport.eyeColor = getValue(passportCode, line, passport.eyeColor);
                        case PASSPORT_ID -> passport.passportId = getValue(passportCode, line, passport.passportId);
                        case COUNTRY_ID -> passport.countryID = getValue(passportCode, line, passport.countryID);
                        default -> System.out.println(String.format("Failure! No such enum type: '%s'%n", passportCode));
                    }
                }
            }
        }
        passports.add(passport);
        return passports;
    }

    private static String getValue(PassportCodes passportCode, String line, String currentValue) {
        if (line.contains(passportCode.code)) {
            var codeIndex = line.indexOf(passportCode.code);
            var endIndex = line.indexOf(' ', codeIndex);
            if (endIndex == -1) {
                endIndex = line.length();
            }
            return line.substring(codeIndex + passportCode.code.length(), endIndex);
        }
        return currentValue;
    }

    static class Passport {
        String birthYear;
        String issueYear;
        String expirationYear;
        String height;
        String hairColor;
        String eyeColor;
        String passportId;
        String countryID;

        boolean isValid() {
            if (birthYear == null) {
                return false;
            } else {
                var birthYearNumber = Integer.parseInt(birthYear);
                if (birthYearNumber > 2002 || birthYearNumber < 1920) {
                    System.out.println(String.format("Validation failed. For Birth Year: '%s'", birthYearNumber));
                    return false;
                }
            }

            if (issueYear == null) {
                return false;
            } else {
                var issueYearNumber = Integer.parseInt(issueYear);
                if (issueYearNumber > 2020 || issueYearNumber < 2010) {
                    System.out.println(String.format("Validation failed. For Issue Year: '%s'", issueYearNumber));
                    return false;
                }
            }

            if (expirationYear == null) {
                return false;
            } else {
                var expirationYearNumber = Integer.parseInt(expirationYear);
                if (expirationYearNumber > 2030 || expirationYearNumber < 2020) {
                    System.out.println(String.format("Validation failed. For Expiration Year: '%s'", expirationYearNumber));
                    return false;
                }
            }

            if (height == null) {
                return false;
            } else {
                if (!height.contains("cm") && !height.contains("in")) {
                    System.out.println(String.format("Validation failed. Missing cm or in in Height: '%s'", height));
                    return false;
                } else {
                    if (height.contains("cm")) {
                        var heightCm = Integer.parseInt(height.substring(0, height.indexOf("cm")));
                        if (heightCm > 193 || heightCm < 150) {
                            System.out.println(String.format("Validation failed. For Height: '%s'", height));
                            return false;
                        }
                    } else {
                        var heightIn = Integer.parseInt(height.substring(0, height.indexOf("in")));
                        if (heightIn > 76 || heightIn < 59) {
                            System.out.println(String.format("Validation failed. For Height: '%s'", height));
                            return false;
                        }
                    }
                }
            }

            if (hairColor == null) {
                return false;
            } else {
                var pattern = Pattern.compile("^(#){1}[0-9a-f]{6}$");
                var matcher = pattern.matcher(hairColor);
                if (!matcher.matches()) {
                    System.out.println(String.format("Validation failed. For Hair color: '%s'", hairColor));
                    return false;
                }
            }

            if (eyeColor == null) {
                return false;
            } else {
                var matches = Arrays.stream(EyeColor.values())
                        .anyMatch(eyeColorEnum -> eyeColorEnum.code.equalsIgnoreCase(eyeColor));
                if (!matches) {
                    System.out.println(String.format("Validation failed. For Eye color: '%s'", eyeColor));
                    return false;
                }
            }

            if (passportId == null) {
                return false;
            } else {
                var pattern = Pattern.compile("^[0-9]{9}$");
                var matcher = pattern.matcher(passportId);
                if (!matcher.matches()) {
                    System.out.println(String.format("Validation failed. For Passport ID: '%s'", passportId));
                    return false;
                }
            }

            return true;
        }

        @Override
        public String toString() {
            return String.format("Birth Year: '%s', Issue Year: '%s', Expiration Year: '%s', Height: '%s', Hair Color: '%s', Eye Color: '%s', " +
                    "PassportID: '%s', CountryID: '%s'%n", birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryID);
        }
    }

    enum PassportCodes {
        BIRTH_YEAR("byr:"),
        ISSUE_YEAR("iyr:"),
        EXPIRATION_YEAR("eyr:"),
        HEIGHT("hgt:"),
        HAIR_COLOR("hcl:"),
        EYE_COLOR("ecl:"),
        PASSPORT_ID("pid:"),
        COUNTRY_ID("cid:");

        private String code;

        PassportCodes(String code) {
            this.code = code;
        }
    }

    enum EyeColor {
        AMBER("amb"),
        BLUE("blu"),
        BROWN("brn"),
        GREY("gry"),
        GREEN("grn"),
        HAZEL("hzl"),
        OTHER("oth");

        private String code;

        EyeColor(String code) {
            this.code = code;
        }
    }
}
