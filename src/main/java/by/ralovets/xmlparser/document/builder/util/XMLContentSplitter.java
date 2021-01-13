package by.ralovets.xmlparser.document.builder.util;

import java.util.Iterator;

public class XMLContentSplitter implements Iterator<String> {

    private final String content;
    private int currentCharIndex;

    public XMLContentSplitter(String content) {
        this.content = content;
    }

    @Override
    public boolean hasNext() {
        return currentCharIndex < content.length() - 2;
    }

    @Override
    public String next() {
        return readNextNode();
    }

    private String readNextNode() {
        StringBuilder result = new StringBuilder();

        char currentChar;
        int startIndex = currentCharIndex;
        char lastChar = content.charAt(currentCharIndex) == '<' ? '>' : '<';

        while (currentCharIndex < content.length()) {
            currentChar = content.charAt(currentCharIndex);
            if (currentChar == lastChar) {
                if (currentChar == '>') {
                    currentCharIndex++;
                }
                result.append(content, startIndex, currentCharIndex);
                break;
            }
            currentCharIndex++;
        }
        return result.toString();
    }
}
