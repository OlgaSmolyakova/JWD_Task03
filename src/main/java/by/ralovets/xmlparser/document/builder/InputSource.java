package by.ralovets.xmlparser.document.builder;

import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.builder.util.XMLContentSplitter;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class InputSource {

    private final String content;
    private List<String> nodesList;

    public List<String> getNodesList() {
        if (nodesList != null) return nodesList;

        nodesList = new ArrayList<>();
        XMLContentSplitter splitter = new XMLContentSplitter(content);

        while (splitter.hasNext()) {
            String s = splitter.next();

            if (!s.trim().isEmpty()) {
                nodesList.add(s);
            }
        }

        return nodesList;
    }

    public InputSource(File file) throws InputSourceException {
        StringBuilder builder = new StringBuilder();

        try {
            Files.readAllLines(file.toPath()).forEach(builder::append);
        } catch (IOException e) {
            throw new InputSourceException();
        }

        content = builder.toString();
    }

    public InputSource(String uri) throws InputSourceException {
        StringBuilder builder = new StringBuilder();

        try {
            Files.readAllLines(new File(uri).toPath()).forEach(builder::append);
        } catch (IOException e) {
            throw new InputSourceException();
        }

        content = builder.toString();
    }

}
