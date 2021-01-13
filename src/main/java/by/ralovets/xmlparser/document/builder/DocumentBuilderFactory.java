package by.ralovets.xmlparser.document.builder;

import by.ralovets.xmlparser.document.builder.impl.PrimitiveDocumentBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DocumentBuilderFactory {

    private DocumentBuilderFactory() {}

    public static DocumentBuilder newDocumentBuilder() {
        return new PrimitiveDocumentBuilder();
    }

    public static DocumentBuilder newDocumentBuilder(String type) {
        // Searching builder of required type anywhere
        throw new NotImplementedException();
    }
}
