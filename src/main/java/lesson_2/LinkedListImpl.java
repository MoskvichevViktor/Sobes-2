package lesson_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListImpl<E> implements LinkedList<E> {

    protected int size;
    protected LinkedList.Node<E> firstElement;

    public void insertFirst(E value) {
        firstElement = new Node<E>(value, firstElement);
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        LinkedList.Node<E> removedNode = firstElement;
        firstElement = removedNode.next;

        removedNode.next = null;
        size--;
        return removedNode.item;
    }

    public boolean remove(E value) {
        LinkedList.Node<E> current = firstElement;
        LinkedList.Node<E> previous = null;

        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }

            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        } else {
            previous.next = current.next;
        }

        current.next = null;
        size--;
        return true;
    }

    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return firstElement == null;
    }

    public void printList() {
        System.out.println();
        Node<E> current = firstElement;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    public E getFirst() {
        return firstElement != null ? firstElement.item : null;
    }

    public Iterator<E> iterator() {
        Iterator<E> itr = new Iterator<E>() {

            private final LinkedListImpl<E> list = LinkedListImpl.this;
            private Node<E> current = list.firstElement;
            private Node<E> previous = null;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E nextValue = current.item;
                previous = current;
                current = current.next;
                return nextValue;
            }

            public void remove() {
                if (previous == null) {
                    list.firstElement = current.next;
                    reset();
                } else {
                    previous.next = current.next;
                    if (!hasNext()) {
                        reset();
                    } else {
                        current = current.next;
                    }
                }
            }

            public void reset() {
                current = list.firstElement;
                previous = null;
            }
        };
        return itr;
    }
}


