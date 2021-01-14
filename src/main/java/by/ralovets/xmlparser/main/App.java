package by.ralovets.xmlparser.main;

import by.ralovets.xmlparser.document.builder.DocumentBuilder;
import by.ralovets.xmlparser.document.builder.DocumentBuilderFactory;
import by.ralovets.xmlparser.document.builder.exception.DocumentBuilderException;
import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.printer.DocumentPrinter;
import by.ralovets.xmlparser.document.structure.Document;

public class App
{
    public static void main( String[] args ) {
        // PrimitiveDocumentBuilder is used by default
        DocumentBuilder builder = DocumentBuilderFactory.newDocumentBuilder();

        Document document;
        try {
            document = builder.parse("C:\\test.xml");
        } catch (DocumentBuilderException e) {
            System.out.println("Oshibka kakaeto");
            return;
        }

        DocumentPrinter.printTree(document);
    }
}
