package by.ralovets.epamcourse;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.parser.XMLNodeParser;
import by.ralovets.epamcourse.xml.parser.XMLNodeParserException;
import by.ralovets.epamcourse.xml.reader.XMLNodeReader;
import by.ralovets.epamcourse.xml.reader.XMLNodeReaderException;
import by.ralovets.epamcourse.xml.reader.impl.XMLNodeFileReader;
import by.ralovets.epamcourse.xml.tree.XMLTreeBuilder;
import by.ralovets.epamcourse.xml.tree.XMLTreeBuilderException;
import by.ralovets.epamcourse.xml.tree.XMLTreePrinter;

import java.io.File;

public class App {

    public static void main(String[] args) throws XMLNodeReaderException {

        File file = new File("C:\\test.xml");

        // Iterable list of tokens.
        // Tokens are tags, comments or text
        XMLNodeReader xmlTokens = new XMLNodeFileReader(file);

        XMLTreeBuilder treeBuilder = new XMLTreeBuilder().reset();
        Node tree;

        try {
            for (String token : xmlTokens) {
                treeBuilder.addNode(XMLNodeParser.parseNode(token));
            }
            tree = treeBuilder.build();
        } catch (XMLTreeBuilderException | XMLNodeParserException e) {
            System.out.println("Ну шото не так походу");
            return;
        }

        XMLTreePrinter.printTree(tree);
    }
}