package by.ralovets.xmlparser.document.printer;

import by.ralovets.xmlparser.document.structure.Document;
import by.ralovets.xmlparser.document.structure.node.Node;

import java.util.List;

public class DocumentPrinter {

    public static void printTree(Document document) {
        print(document.getRootNode(), 0);
    }

    private static void print(Node node, int tab) {
        if (node != null) {
            printTabulation(tab);
            System.out.println(node);

            List<Node> children = node.getChildren();
            if (children != null) {
                for (Node n : children) {
                    print(n, tab + 1);
                }
            }
        }
    }

    private static void printTabulation(int tab) {
        for (int i = 0; i < tab; i++) {
            System.out.print('\t');
        }
    }
}
