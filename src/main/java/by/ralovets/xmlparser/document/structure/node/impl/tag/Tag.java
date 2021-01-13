package by.ralovets.xmlparser.document.structure.node.impl.tag;

import by.ralovets.xmlparser.document.structure.Attribute;
import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;

import java.util.ArrayList;
import java.util.List;

public abstract class Tag extends Node {

    protected boolean isSingleTag;
    private String name;
    private List<Attribute> attributes;

    public Tag() {
        type = isSingleTag ? NodeType.SINGLE_TAG : NodeType.DOUBLE_TAG;
    }

    public boolean isSingleTag() {
        return isSingleTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attr) {
        if (attributes == null) attributes = new ArrayList<>();

        attributes.add(attr);
    }

    public void addChild(Node node) {
        if (node == null) throw new IllegalArgumentException();

        if (isSingleTag) isSingleTag = false;

        if (children == null) children = new ArrayList<>();

        children.add(node);
    }

    @Override
    public String toString() {
//        StringBuilder result = new StringBuilder(isSingleTag ? "SingleTag [" : "DoubleTag [");
//
//        for (Attribute a : attributes) {
//            result.append(a).append(',');
//        }
//
//        result.deleteCharAt(result.length() - 1).append(']');
//        return result.toString();
        return name;
    }


}
