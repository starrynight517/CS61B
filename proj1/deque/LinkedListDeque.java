package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private Node sentinel;
    private int size;

    private class Node{
        public T item;
        Node next;
        Node prev;
    }

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item){
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item){
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node p=sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item+"");
            p=p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index<0 || index >=size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i=0; i <index; i++) {
            p = p.next;
        }
        return p.item;
    }


    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> target = (Deque<T>) o;
            if (target.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!target.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }






    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current;

        public LinkedListIterator() {
            current = sentinel.next;  // 从第一个元素开始
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }





}
