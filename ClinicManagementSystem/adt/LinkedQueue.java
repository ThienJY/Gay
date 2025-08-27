package adt;

import java.io.Serializable;

public class LinkedQueue<T> implements QueueInterface<T>, Serializable {
    private Node firstNode;
    private Node lastNode;
    private int size;

    private class Node implements Serializable {
        private final T data;
        private Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T result = firstNode.data;
        firstNode = firstNode.next;
        size--;
        if (isEmpty()) {
            lastNode = null;
        }
        return result;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            return null;
        }
        return firstNode.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public T[] getArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[size];
        Node currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            result[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }
}
