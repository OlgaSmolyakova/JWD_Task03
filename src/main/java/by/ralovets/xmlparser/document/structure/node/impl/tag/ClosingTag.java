package by.ralovets.xmlparser.document.structure.node.impl.tag;

import by.ralovets.xmlparser.document.structure.Attribute;
import by.ralovets.xmlparser.document.structure.node.Node;
import by.ralovets.xmlparser.document.structure.node.NodeType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class ClosingTag extends Tag {

    @Override
    public NodeType getType() {
        return NodeType.CLOSING_TAG;
    }

    @Override
    public boolean isSingleTag() {
        throw new NotImplementedException();
    }

    @Override
    public List<Attribute> getAttributes() {
        throw new NotImplementedException();
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        throw new NotImplementedException();
    }

    @Override
    public void addAttribute(Attribute attr) {
        throw new NotImplementedException();
    }

    @Override
    public void addChild(Node node) {
        throw new NotImplementedException();
    }
}
