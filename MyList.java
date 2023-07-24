package com.company.oop;

public interface MyList<T> extends Iterable<T> {

    int size();

    int capacity();

    void add(T element);

    T get(int index);

    int indexOf(T element);

    int lastIndexOf(T element);

    boolean contains(T element);

    void removeAt(int index);

    boolean remove(T element);

    void clear();

    void swap(int from, int to);

    void print();
}
