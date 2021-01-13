package by.ralovets.xmlparser.document.structure.node.impl;

import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;

public class Comment extends Node {

    private String text;

    public Comment(String text) {
        this.text = text;
        type = NodeType.COMMENT;
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

        if (!(obj instanceof Comment)) return false;

        Comment comment = (Comment) obj;

        return text.equals(comment.text);
    }

    @Override
    public String toString() {
        return "Comment [" + text + "]";
    }
}
