package by.ralovets.epamcourse.xml.tree;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.node.impl.tag.Tag;

import java.util.Stack;

public class XMLTreeBuilder {

    public XMLTreeBuilder() {
    }

    private Node rootNode;
    private Node currentNode;
    private final Stack<String> nodeNamesStack = new Stack<>();

    public XMLTreeBuilder reset() {
        rootNode = null;
        return this;
    }

    public Node build() throws XMLTreeBuilderException {
        if (!nodeNamesStack.empty()) throw new XMLTreeBuilderException();

        // ToDo
        return rootNode;
    }

    public XMLTreeBuilder addNode(Node node) throws XMLTreeBuilderException {
        switch (node.getNodeType()) {
            case COMMENT:
            case TEXT: {
                currentNode.addChild(node);
                break;
            }
            case TAG: {
                initializeTag((Tag) node);
                break;
            }
        }
        return this;
    }

    private void initializeTag(Tag tag) throws XMLTreeBuilderException {
        switch (tag.getType()) {
            case SINGLE_TAG: {
                currentNode.addChild(tag);
                return;
            }
            case DOUBLE_CLOSING_TAG: {
                if (nodeNamesStack.peek().equals(tag.getName())) {
                    nodeNamesStack.pop();
                    currentNode = currentNode.getParent();
                } else
                    throw new XMLTreeBuilderException();
                break;
            }
            case DOUBLE_OPENING_TAG: {
                nodeNamesStack.push(tag.getName());

                if (currentNode == null) {
                    rootNode = tag;
                } else {
                    tag.setParent(currentNode);
                    currentNode.addChild(tag);
                }

                currentNode = tag;
            }
        }
    }

}
