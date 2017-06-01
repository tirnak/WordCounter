package org.tirnak;

import java.io.PrintStream;
import java.util.List;

abstract class AbstractNode<T extends Comparable<T>> implements Node<T> {

    protected List<Node<T>> children;
    protected T value;

    public AbstractNode(T value) {this.value = value;}

    @Override
    public void print(PrintStream out) {
        print(out, "", true);
    }

    private static final String
        CURRENT         = "" + (char)0x251C + (char)0x2500 + (char)0x2500 + " ",
        CURRENT_LAST    = "" + (char)0x2514 + (char)0x2500 + (char)0x2500 + " ",
        PREFIX_USUAL    = "" + (char)0x2502 + "    ",
        PREFIX_LAST     = "      ";

    protected void print(PrintStream out, String prefix, boolean isLast) {
        out.println(prefix + (isLast ? CURRENT_LAST : CURRENT) + toStringShort());
        for (int i = 0; i < children.size() - 1; i++) {
            Node<T> child = children.get(i);
            ((AbstractNode) child).print(out, prefix + (isLast ? PREFIX_LAST : PREFIX_USUAL), false);
        }
        if (children.size() > 0) {
            ((AbstractNode)children.get(children.size() - 1))
                    .print(out, prefix + (isLast ? PREFIX_LAST : PREFIX_USUAL), true);
        }
    }

    protected String toStringShort() {return String.valueOf(value);}

    @Override
    public T getValue() { return value; }
}
