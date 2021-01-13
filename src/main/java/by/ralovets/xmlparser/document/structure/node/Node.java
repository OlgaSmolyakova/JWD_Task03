package by.ralovets.xmlparser.document.structure.node;

import java.util.List;

public abstract class Node {

    protected NodeType type;
    private Node parent;
    protected List<Node> children;

    public NodeType getType() {
        return type;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }
}
