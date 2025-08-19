package adt;

/**
 * QueueInterface<T>
 * Author: Thien (student) - Module 3 Consultation Management
 * A minimal FIFO queue ADT (no use of Java Collections Framework).
 */
public interface QueueInterface<T> {
    void enqueue(T newEntry);
    T dequeue();              // returns null if empty
    T getFront();             // returns null if empty
    boolean isEmpty();
    int size();
    void clear();
}
