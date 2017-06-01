package org.tirnak;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryNode<T extends Comparable<T>> extends AbstractNode<T> {

    { children = new ArrayList<>(); }

    public BinaryNode(T value) {super(value);}

    @Override
    public void addChild(Node<T> child) {
        if (children.size() < 2) {
            children.add(child);
        } else {
            throw new RuntimeException("There are already two children of " + this.toString());
        }
    }

    @Override
    public Iterator<Node<T>> getChildrenIterator() {
        return children.iterator();
    }

}
