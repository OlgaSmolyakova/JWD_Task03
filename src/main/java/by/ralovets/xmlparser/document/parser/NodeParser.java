package by.ralovets.xmlparser.document.parser;

import by.ralovets.xmlparser.document.structure.Attribute;
import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;
import by.ralovets.xmlparser.document.structure.node.impl.Comment;
import by.ralovets.xmlparser.document.structure.node.impl.Text;
import by.ralovets.xmlparser.document.structure.node.impl.tag.ClosingTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.DoubleTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.SingleTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.ralovets.xmlparser.document.structure.node.NodeType.*;

// константы в коде именуются, как бы не было лень
public class NodeParser {

    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '"';

    private NodeParser() {
    }

    public static Node parseNode(String s) throws NodeParserException {
        switch (determineNodeType(s)) {
            case TEXT:
                return parseText(s);
            case COMMENT:
                return parseComment(s);
            case SINGLE_TAG:
                return parseSingleTag(s);
            case DOUBLE_TAG:
                return parseDoubleTag(s);
            case CLOSING_TAG:
                return parseClosingTag(s);
        }
        throw new IllegalArgumentException();
    }

    private static NodeType determineNodeType(String node) {
        if (node.startsWith("<!--")) return COMMENT;
        if (node.startsWith("</")) return CLOSING_TAG;
        if (node.endsWith("/>")) return SINGLE_TAG;
        if (node.startsWith("<")) return DOUBLE_TAG;
        return TEXT;
    }

    private static SingleTag parseSingleTag(String s) throws NodeParserException {
        SingleTag tag = new SingleTag();
        tag.setName(parseTagName(s));
        tag.setAttributes(parseAttributes(s));
        return tag;
    }

    private static DoubleTag parseDoubleTag(String s) throws NodeParserException {
        DoubleTag tag = new DoubleTag();
        tag.setName(parseTagName(s));
        tag.setAttributes(parseAttributes(s));
        return tag;
    }

    private static ClosingTag parseClosingTag(String s) {
        ClosingTag tag = new ClosingTag();
        tag.setName(parseTagName(s));
        return tag;
    }

    private static Comment parseComment(String s) {
        return new Comment(s.substring(4, s.length() - 3));
    }

    private static Text parseText(String s) {
        return new Text(s);
    }

    private static String parseTagName(String s) {
        char[] chars = s.toCharArray();
        int startIndex = (s.charAt(1) == '/') ? 2 : 1;
        int endIndex = startIndex;

        for (int i = startIndex; i < chars.length; i++) {
            char c = chars[i];
            if (c == ' ' | c == '/' | c == '>') {
                endIndex = i;
                break;
            }
        }

        // <tag att="_">
        // <tag>
        // <tag att="_"/>
        // <tag/>
        // </tag>

        return s.substring(startIndex, endIndex);
    }

    private static List<Attribute> parseAttributes(String s) throws NodeParserException {
        if (s.startsWith("</")) return new ArrayList<>();

        int startIndex = s.indexOf(" ");

        if (startIndex == -1) return new ArrayList<>();

        int endIndex = s.endsWith("/>")
                ? s.lastIndexOf("/")
                : s.lastIndexOf(">");

        if (startIndex >= endIndex) return new ArrayList<>();

        List<Attribute> attributes = new ArrayList<>();

        s = s.substring(startIndex, endIndex);
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            String attributeName;
            String attributeValue;

            buffer.setLength(0);
            while (s.charAt(i) != '=') {
                buffer.append(s.charAt(i));
                i++;
            }
            attributeName = buffer.toString().trim();

            i++;
            while (s.charAt(i) == ' ') i++;
            if (s.charAt(i) != SINGLE_QUOTE && s.charAt(i) != DOUBLE_QUOTE) {
                throw new NodeParserException();
            }

            char quote = s.charAt(i);
            i++;
            buffer.setLength(0);
            while (s.charAt(i) != quote) {
                buffer.append(s.charAt(i));
                i++;
            }

            attributeValue = buffer.toString();

            Attribute attribute = new Attribute(attributeName, attributeValue);
            attributes.add(attribute);

            i++;
        }

        return attributes;
    }
}
