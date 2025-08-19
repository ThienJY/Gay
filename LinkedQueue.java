package adt;

/**
 * LinkedQueue<T>
 * Author: Thien (student) - Module 3 Consultation Management
 * Singly-linked queue implementation of QueueInterface<T>.
 */
public class LinkedQueue<T> implements QueueInterface<T> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void enqueue(T newEntry) {
        Node<T> node = new Node<>(newEntry);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) return null;
        T val = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return val;
    }

    @Override
    public T getFront() {
        return head == null ? null : head.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> cur = head;
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
