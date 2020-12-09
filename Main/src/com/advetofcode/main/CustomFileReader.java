package com.advetofcode.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

    public List<String> readFile(String path) throws IOException {
        var result = new ArrayList<String>();
        var reader = new BufferedReader(new FileReader(path));
        try {
            var line = reader.readLine();
            while (line != null) {
                result.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return result;
    }
}
