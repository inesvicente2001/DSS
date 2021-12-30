package utils;

import java.util.HashSet;
import java.util.Set;

public class Pair<T, T1> {
    private T left;
    private T1 right;


    public Pair(T left, T1 right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T1 getRight() {
        return right;
    }
}
