package lesson_2;

public interface LinkedList<E> extends Iterable<E> {

    void insertFirst(E value);
    E removeFirst();
    boolean remove(E value);
    boolean contains(E value);
    int size();
    boolean isEmpty();
    void printList();
    E getFirst();


    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

}