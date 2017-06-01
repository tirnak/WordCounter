package org.tirnak;

import java.io.PrintStream;
import java.util.Iterator;

public interface Node<T extends Comparable<T>> {
    void addChild(Node<T> child);
    Iterator<Node<T>> getChildrenIterator();
    void print(PrintStream out);
    T getValue();
}
