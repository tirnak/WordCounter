package org.tirnak;

import java.util.Collections;
import java.util.Iterator;

public class NamedLeaf<T extends Comparable<T>> extends AbstractNode<T> {

    private String name;
    { children = Collections.emptyList(); }

    public NamedLeaf(String name, T value) {super(value); this.name = name; }

    @Override
    public void addChild(Node<T> child) { throw new UnsupportedOperationException(); }

    @Override
    public Iterator<Node<T>> getChildrenIterator() {
        return null;
    }

    protected String toStringShort() {return String.valueOf(value) + "(" + name + ")";}

    @Override
    public String toString() {
        return "NamedLeaf{" +
                "name='" + name + '\'' +
                "value='" + value + '\'' +
                '}';
    }
}
