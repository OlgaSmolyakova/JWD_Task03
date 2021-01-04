package by.ralovets.epamcourse.xml.node.impl;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.node.NodeType;

import java.util.List;

public class Comment extends Node {

    private String commentText;

    public Comment(String s) {
        super(NodeType.COMMENT);

        // ToDo:
        commentText = s;
    }

    @Override
    public String toString() {
        return "//" + commentText;
    }

    @Override
    public void addChild(Node node) {

    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
