package adt;

public interface QueueInterface<T> {
    void enqueue(T newEntry);
    T dequeue();
    T getFront();
    boolean isEmpty();
    void clear();
    T[] getArray();
    int getSize();
}
