package by.ralovets.xmlparser.document.structure.node.impl;

import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;

public class Text extends Node {

    private String text;

    public Text(String text) {
        this.text = text;
        type = NodeType.TEXT;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Text)) return false;

        Text text = (Text) obj;

        return this.text.equals(text.text);
    }

    @Override
    public String toString() {
        return "Text [" + text + "]";
    }
}
