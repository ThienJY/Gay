package adt;

import java.io.Serializable;

public class List<T> implements ListInterface<T>, Serializable {
    private Node firstNode;
    private int numberOfEntries;

    private class Node implements Serializable {
        private T data;
        private Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public List() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if (newPosition < 1 || newPosition > numberOfEntries + 1) {
            return false;
        }
        Node newNode = new Node(newEntry);
        if (newPosition == 1) {
            newNode.next = firstNode;
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 1; i < newPosition - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            return null;
        }
        T result = null;
        if (givenPosition == 1) {
            result = firstNode.data;
            firstNode = firstNode.next;
        } else {
            Node currentNode = firstNode;
            for (int i = 1; i < givenPosition - 1; i++) {
                currentNode = currentNode.next;
            }
            result = currentNode.next.data;
            currentNode.next = currentNode.next.next;
        }
        numberOfEntries--;
        return result;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            return false;
        }
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }
        currentNode.data = newEntry;
        return true;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            return null;
        }
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
}
