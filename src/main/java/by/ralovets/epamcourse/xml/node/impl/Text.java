package by.ralovets.epamcourse.xml.node.impl;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.node.NodeType;

import java.util.List;

public class Text extends Node {

    private String text;

    public Text(String text) {
        super(NodeType.TEXT);

        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public void addChild(Node node) {

    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
