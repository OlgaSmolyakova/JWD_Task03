package by.ralovets.epamcourse.xml.node;

import by.ralovets.epamcourse.xml.node.impl.tag.TagType;
import by.ralovets.epamcourse.xml.node.impl.tag.exception.TagUsageException;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private final NodeType nodeType;
    private Node parent;

    public Node(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    @Override
    public abstract String toString();

    public abstract void addChild(Node node);

    public abstract List<Node> getChildren();

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
