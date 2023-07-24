package com.company.oop;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 4;
    private Object[] array;
    private int size;

    public MyListImpl() {
        this(DEFAULT_CAPACITY);
    }

    public MyListImpl(int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        array[size] = element;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void removeAt(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);

        if (index >= 0) {
            removeAt(index);
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public void swap(int from, int to) {
        checkIndex(from);
        checkIndex(to);

        Object temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = (T) array[currentIndex];
            currentIndex++;
            return element;
        }
    }
}