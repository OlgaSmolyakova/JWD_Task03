package by.ralovets.xmlparser.document.structure;

import by.ralovets.xmlparser.document.structure.node.Node;

public class Document {

    public Document() {

    }

    private Node rootNode;

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}
