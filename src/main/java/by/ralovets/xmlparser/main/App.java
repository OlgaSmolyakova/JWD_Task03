package by.ralovets.xmlparser.main;

import by.ralovets.xmlparser.document.builder.DocumentBuilder;
import by.ralovets.xmlparser.document.builder.DocumentBuilderFactory;
import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.printer.DocumentPrinter;
import by.ralovets.xmlparser.document.structure.Document;

public class App
{
    public static void main( String[] args ) throws InputSourceException {
        // PrimitiveDocumentBuilder is used by default
        DocumentBuilder builder = DocumentBuilderFactory.newDocumentBuilder();

        Document document = builder.parse("C:\\test.xml");

        DocumentPrinter.printTree(document);
    }
}
