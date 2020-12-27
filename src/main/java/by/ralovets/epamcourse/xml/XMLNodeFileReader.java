package by.ralovets.epamcourse.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class XMLNodeFileReader implements XMLNodeReader, Iterator<String> {

    Iterator<String> fileIterator;
    StringBuilder buffer = new StringBuilder();

    public XMLNodeFileReader(File file) throws XMLNodeReaderException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new XMLNodeReaderException();
        }

        fileIterator = new BufferedReader(fileReader).lines().iterator();
    }

    @Override
    public boolean hasNext() {
        return fileIterator.hasNext() || buffer.length() != 0;
    }

    @Override
    public String next() {
        String s = "";
        while (fileIterator.hasNext()) {
           s = fileIterator.next().trim();
            if (!s.isEmpty()) break;
        }

        // чекнуть конец

        buffer.append(s);
        if (buffer.charAt(0) == '<') {
            return readBufferUpTo('>', true);
        } else {
            return readBufferUpTo('<', false);
        }
    }

    /**
     * Function reads buffer from start up to specified character (excluding or
     * including this character). Function ignore specified character in quotes.
     * If the buffer is empty, the next line of the file is added to the buffer.
     * @param lastChar ending character.
     * @param includingLastChar if parameter is true, last char is included into
     *                          result string.
     * @return specified string from buffer.
     */
    private String readBufferUpTo(char lastChar, boolean includingLastChar) {
        int currentCharIndex = 0;
        boolean insideBraces = false;
        char currentChar;
        StringBuilder result = new StringBuilder();

        while (currentCharIndex < buffer.length()) {

            currentChar = buffer.charAt(currentCharIndex);

            if (currentChar == '"')
            {
                insideBraces = !insideBraces;
            } else if (currentChar == lastChar && !insideBraces) {
                result.append(buffer.substring(0, currentCharIndex));

                if (includingLastChar) {
                    result.append(lastChar);
                    currentCharIndex++;
                }

                correctBufferBounds(currentCharIndex);
                break;
            }

            // Increase the buffer if lastChar is not found
            if (currentCharIndex == buffer.length() - 1) {
                buffer.append(fileIterator.next());
            }

            currentCharIndex++;
        }
        return result.toString();
    }

    /**
     * Clears buffer up to bufferStartIndex (if bufferStartIndex is out of
     * bounds, the full buffer is cleared).
     * @param bufferStartIndex new buffer start index.
     */
    private void correctBufferBounds(int bufferStartIndex) {
        buffer.delete(0, Math.min(bufferStartIndex, buffer.length()));
    }
}
