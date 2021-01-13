package by.ralovets.xmlparser.document.builder;

import by.ralovets.xmlparser.document.builder.exception.InputSourceException;
import by.ralovets.xmlparser.document.structure.Document;

import java.io.File;

public abstract class DocumentBuilder {

    public abstract Document parse(InputSource inputSource);

    public Document parse(File file) throws InputSourceException {
        return parse(new InputSource(file));
    }

    public Document parse(String uri) throws InputSourceException {
        return parse(new InputSource(uri));
    }
}
