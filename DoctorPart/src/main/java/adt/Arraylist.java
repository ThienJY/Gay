/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Ethan
 */
public class Arraylist<T> implements ListInterface<T> {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public Arraylist() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (size == array.length) {
            resize();
        }
        array[size++] = item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return array[index];
    }

    @Override
    public boolean remove(T item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public ListInterface<T> getIterator() {
        return new ArraylistIterator<>(this);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private static class ArraylistIterator<T> implements ListInterface<T> {
        private Arraylist<T> list;
        private int currentIndex;

        public ArraylistIterator(Arraylist<T> list) {
            this.list = list;
            this.currentIndex = 0;
        }

        @Override
        public void add(T item) {
            list.add(item);
        }

        @Override
        public T get(int index) {
            return list.get(index);
        }

        @Override
        public boolean remove(T item) {
            return list.remove(item);
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public void clear() {
            list.clear();
        }

        @Override
        public ListInterface<T> getIterator() {
            return this;
        }
    }
}