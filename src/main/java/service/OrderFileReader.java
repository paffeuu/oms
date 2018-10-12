package service;

import exception.IncorrectFileFormatException;
import model.Order;
import service.parser.CsvParser;
import service.parser.Parser;
import service.parser.XmlParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderFileReader {
    private String[] fileNames;

    public OrderFileReader(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public ArrayList<Order> read() {
        ArrayList<Order> orders = new ArrayList<>();
        for (String file : fileNames) {
            try {
                InputStream input = getClass().getResourceAsStream("/" + file);
                Scanner scanner = new Scanner(input);
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