package by.ralovets.xmlparser.document.builder.impl;

import by.ralovets.xmlparser.document.builder.DocumentBuilder;
import by.ralovets.xmlparser.document.builder.InputSource;
import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.parser.NodeParser;
import by.ralovets.xmlparser.document.structure.Attribute;
import by.ralovets.xmlparser.document.structure.Document;
import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.impl.Comment;
import by.ralovets.xmlparser.document.structure.node.impl.Text;
import by.ralovets.xmlparser.document.structure.node.impl.tag.DoubleTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.SingleTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.Tag;

import java.util.List;
import java.util.Stack;

public class PrimitiveDocumentBuilder extends DocumentBuilder {

    private Node rootNode = new DoubleTag();
    private Node currentNode = rootNode;
    private final Stack<String> stack = new Stack<>();

    @Override
    public Document parse(InputSource inputSource) {

        Document document = new Document();
        document.setRootNode(rootNode);
        Node node;

        for (String stringNode : inputSource.getNodesList()) {
            node = NodeParser.parseNode(stringNode);
            dispatchNodeInsertion(node);
        }

        rootNode = rootNode.getChildren().get(0);
        return document;
    }

    private void dispatchNodeInsertion(Node n) {
        switch (n.getType()) {
            case COMMENT: dispatchComment(n); break;
            case TEXT: dispatchText(n); break;
            case SINGLE_TAG: dispatchSingleTag(n); break;
            case DOUBLE_TAG: dispatchDoubleTag(n); break;
            case CLOSING_TAG: dispatchClosingTag(n); break;
        }
    }

    private void dispatchComment(Node n) {
        n.setParent(currentNode);
        ((Tag) currentNode).addChild(n);
    }

    private void dispatchText(Node n) {
        n.setParent(currentNode);
        ((Tag) currentNode).addChild(n);
    }

    private void dispatchSingleTag(Node n) {
        n.setParent(currentNode);
        ((Tag) currentNode).addChild(n);
    }

    private void dispatchDoubleTag(Node n) {
        n.setParent(currentNode);
        ((Tag) currentNode).addChild(n);
        currentNode = n;

        stack.push(((Tag) currentNode).getName());
    }

    private void dispatchClosingTag(Node n) {
        if (stack.peek().equals(((Tag) n).getName())) {
            stack.pop();
            currentNode = currentNode.getParent();
        }
    }
}
