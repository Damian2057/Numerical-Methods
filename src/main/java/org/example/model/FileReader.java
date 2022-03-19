package org.example.model;

import org.example.exception.FileOperationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader implements AutoCloseable{

    private int numberOfEquations = 0;
    private final List<Double> valueList = new ArrayList<>();

    public double[][] getSystemOfEquations(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            throw new FileOperationException("There was a problem with reading the file ");
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseData(line);
        }

        double[][] temp = new double[numberOfEquations][numberOfEquations+1];
        int index = 0;
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations+1; j++) {
                temp[i][j] = valueList.get(index);
                index ++;
            }
        }
        return temp;
    }

    private void parseData(String str) {
        Scanner lineScanner = new Scanner(str).useDelimiter(" ");
        if(numberOfEquations == 0) {
            this.numberOfEquations = Integer.parseInt(lineScanner.next());
        }
        while (lineScanner.hasNext()) {
            valueList.add(Double.parseDouble(lineScanner.next()));
        }
    }

    public int getNumberOfEquations() {
        return numberOfEquations;
    }


    @Override
    public void close() throws Exception {

    }
}
