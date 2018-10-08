package service;

import exception.IncorrectFileFormatException;
import model.Order;
import service.parser.CsvParser;
import service.parser.Parser;
import service.parser.XmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderFileReader {
    private File[] files;
    private final static String PATH = "target/classes/";

    public OrderFileReader(String[] fileNames) {
        files = new File[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            files[i] = new File(PATH + fileNames[i]);
        }
    }

    public ArrayList<Order> read() {
        ArrayList<Order> orders = new ArrayList<>();
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<String> content = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    content.add(scanner.nextLine());
                }
                if (content.size() == 0) {
                    throw new IncorrectFileFormatException();
                }
                FileExtension fileExtension = checkFileExtension(content.get(0));
                Parser parser;
                if (fileExtension.equals(FileExtension.CSV)) {
                    parser = new CsvParser(content);
                } else if (fileExtension.equals(FileExtension.XML)) {
                    parser = new XmlParser(content);
                } else {
                    throw new IncorrectFileFormatException();
                }
                orders.addAll(parser.parse());
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
        return orders;
    }

    private static FileExtension checkFileExtension(String firstLine) {
        if (firstLine.equals("<requests>")) {
            return FileExtension.XML;
        }
        if (firstLine.contains(",")) {
            return FileExtension.CSV;
        }
        return FileExtension.NO_EXTENSION;
    }
}

enum FileExtension {
    CSV, XML, NO_EXTENSION
}