package by.ralovets.xmlparser.document.builder;

import by.ralovets.xmlparser.document.builder.exception.DocumentBuilderException;
import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.structure.Document;

import java.io.File;

public abstract class DocumentBuilder {

    public abstract Document parse(InputSource inputSource) throws DocumentBuilderException;

    public Document parse(File file) throws DocumentBuilderException {
        InputSource inputSource;
        try {
            inputSource = new InputSource(file);
        } catch (InputSourceException e) {
            throw new DocumentBuilderException();
        }
        return parse(inputSource);
    }

    public Document parse(String uri) throws DocumentBuilderException {
        InputSource inputSource;
        try {
            inputSource = new InputSource(uri);
        } catch (InputSourceException e) {
            throw new DocumentBuilderException();
        }
        return parse(inputSource);
    }
}
