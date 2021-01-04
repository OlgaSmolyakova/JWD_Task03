package by.ralovets.epamcourse.xml.parser;

import by.ralovets.epamcourse.xml.node.Node;
import by.ralovets.epamcourse.xml.node.impl.Comment;
import by.ralovets.epamcourse.xml.node.impl.Text;
import by.ralovets.epamcourse.xml.node.impl.tag.Tag;
import static by.ralovets.epamcourse.xml.node.impl.tag.TagType.*;

import by.ralovets.epamcourse.xml.node.impl.tag.TagType;
import by.ralovets.epamcourse.xml.node.impl.tag.exception.TagBuilderException;

import java.util.Map;

public class XMLNodeParser {

    public static Node parseNode(String s) throws XMLNodeParserException {
        if (s.startsWith("<")) {
            if (s.startsWith("<!--")) {
                return new Comment(parseCommentText(s));
            } else {
                TagType tagType;
                String tagName = parseTagName(s);
                Map<String, String> attributes;

                if (s.startsWith("</")) {
                    tagType = DOUBLE_CLOSING_TAG;

                    try {
                        return new Tag.Builder()
                                .reset()
                                .setName(tagName)
                                .setType(tagType)
                                .build();
                    } catch (TagBuilderException e) {
                        throw new XMLNodeParserException();
                    }
                } else {
                    tagType = s.endsWith("/>") ? SINGLE_TAG : DOUBLE_OPENING_TAG;
                    attributes = parseAttributes(s);

                    try {
                        return new Tag.Builder()
                                .reset()
                                .setName(tagName)
                                .setType(tagType)
                                .setAttributes(attributes)
                                .build();
                    } catch (TagBuilderException e) {
                        throw new XMLNodeParserException();
                    }
                }
            }
        } else {
            return new Text(s);
        }
    }

    private static String parseTagName(String s) throws XMLNodeParserException {
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

    private static Map<String, String> parseAttributes(String s) throws XMLNodeParserException {
        // ToDo
        return null;
    }

    private static String parseCommentText(String s) throws XMLNodeParserException {

        // <!-- Hello -->

        return s.substring(4, s.length() - 3);
    }
}
