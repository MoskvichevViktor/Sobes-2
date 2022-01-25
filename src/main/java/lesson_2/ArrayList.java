package lesson_2;

import java.util.Arrays;

public class ArrayList<E>  {

    private E arr[];
    private int size;
    private int capacity;
    private int START_CAPACITY = 10;

    public ArrayList() {
        capacity = START_CAPACITY;
        arr = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) return i;
            }
        }
        return -1;
    }

    public boolean add(E value) {
        return add(size, value);
    }

    public boolean add(int index, E value) {
        try {
            checkForAdd(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            return false;
        }
        checkCapacity();
        if (index < size) {
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }
        arr[index] = value;
        size++;
        return true;
    }

    private void checkForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private E[] newCapacity(int capacity) {
        this.capacity = capacity;
        Object[] newArr = new Object[capacity];
        System.arraycopy(arr,0, newArr, 0, size);
        return (E[]) newArr;
    }

    private void checkCapacity() {
        if (capacity*3/4 <  size) {
            arr = newCapacity(capacity / 2 * 3 + 1);
        }
    }

    public void trimCapacity() {
        if (size > 0) {
            arr = newCapacity(size);
        } else {
            arr = newCapacity(START_CAPACITY);
        }
    }

    public boolean removeValue(Object o) {
        int i = indexOf(o);
        return remove(i).equals(o);
    }

    public E remove(int index) {
        checkForAdd(index);
        E removedElement = arr[index];
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(arr, index + 1, arr, index, newSize - index);
        arr[size = newSize] = null;
        return removedElement;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        checkForAdd(index);
        return arr[index];
    }

    public E set(int index, E value) {
        checkForAdd(index);
        arr[index] = value;
        return arr[index];
    }

    public String toStringFullData() {
        return "ArrayListImpl{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}

