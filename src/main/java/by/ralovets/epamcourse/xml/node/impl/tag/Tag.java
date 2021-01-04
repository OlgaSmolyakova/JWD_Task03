package by.ralovets.epamcourse.xml.node.impl.tag;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.node.NodeType;
import by.ralovets.epamcourse.xml.node.impl.tag.exception.TagBuilderException;
import by.ralovets.epamcourse.xml.node.impl.tag.exception.TagUsageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tag extends Node {

    private String name;
    private TagType type;
    private List<Node> children;
    private Map<String, String> attributes;

    public String getName() {
        return name;
    }

    public TagType getType() {
        return type;
    }

    private Tag(NodeType nodeType) {
        super(nodeType);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addChild(Node node) {
        // ToDo: illegal child addiction to tag

        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(node);
    }

    @Override
    public List<Node> getChildren() {
        if (type == TagType.DOUBLE_OPENING_TAG)
            return children;

        return null;
    }

    public static class Builder {

        private Tag tag;

        public Builder reset() {
            tag = new Tag(NodeType.TAG);
            return this;
        }

        public Tag build() throws TagBuilderException {
            if (tag.name == null) {
                throw new TagBuilderException();
            }

            if (tag.type == null) {
                throw new TagBuilderException();
            }

            return tag;
        }

        public Builder setName(String name) {
            tag.name = name;
            return this;
        }

        public Builder setAttributes(Map<String, String> attributes) throws TagBuilderException {
            if (tag.type == TagType.DOUBLE_CLOSING_TAG) {
                throw new TagBuilderException();
            }

            tag.attributes = attributes;
            return this;
        }

        public Builder addAttributes(Map<String, String> attributes) throws TagBuilderException {
            if (tag.type == TagType.DOUBLE_CLOSING_TAG) {
                throw new TagBuilderException();
            }

            if (attributes != null) tag.attributes.putAll(attributes);

            return this;
        }

        public Builder addAttribute(String key, String value) throws TagBuilderException {
            if (tag.type == TagType.DOUBLE_CLOSING_TAG) {
                throw new TagBuilderException();
            }

            tag.attributes.put(key, value);
            return this;
        }

        public Builder setType(TagType tagType) {
            tag.type = tagType;
            return this;
        }
    }
}
