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
public interface ListInterface<T> {
    void add(T item);
    T get(int index);
    boolean remove(T item);
    int size();
    boolean isEmpty();
    void clear();
    ListInterface<T> getIterator();
}
