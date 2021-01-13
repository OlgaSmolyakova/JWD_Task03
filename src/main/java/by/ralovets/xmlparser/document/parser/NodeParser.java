package by.ralovets.xmlparser.document.parser;

import by.ralovets.xmlparser.document.structure.Attribute;
import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;
import by.ralovets.xmlparser.document.structure.node.impl.Comment;
import by.ralovets.xmlparser.document.structure.node.impl.Text;
import by.ralovets.xmlparser.document.structure.node.impl.tag.ClosingTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.DoubleTag;
import by.ralovets.xmlparser.document.structure.node.impl.tag.SingleTag;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

import static by.ralovets.xmlparser.document.structure.node.NodeType.*;

public class NodeParser {

    private NodeParser() {}

    public static Node parseNode(String s) {
        switch (determineNodeType(s)) {
            case TEXT: return parseText(s);
            case COMMENT: return parseComment(s);
            case SINGLE_TAG: return parseSingleTag(s);
            case DOUBLE_TAG: return parseDoubleTag(s);
            case CLOSING_TAG: return parseClosingTag(s);
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

    private static SingleTag parseSingleTag(String s) {
        SingleTag tag = new SingleTag();
        tag.setName(parseTagName(s));
        tag.setAttributes(parseAttributes(s));
        return tag;
    }

    private static DoubleTag parseDoubleTag(String s) {
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

    private static List<Attribute> parseAttributes(String s) {
        return null;
    }
}
