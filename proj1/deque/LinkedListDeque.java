package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    public class Node{
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
    public T get(int index){
        Node p=sentinel.next;
        while(p!=sentinel){
            if(p.item.equals(index)){
                return p.item;
            }
        }
        return null;
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
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque){
            LinkedListDeque l=(LinkedListDeque)o;
            if(size!=l.size){
                return false;
            }
            Node p=this.sentinel.next;
            Node q=l.sentinel.next;
            while(p!=sentinel){
                if(!p.item.equals(q.item)){
                    return false;
                }
                p=p.next;
                q=q.next;
            }
            }
        else{
            return false;

        }
        return true;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        public LinkedListIterator() {
            wizPos=0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;

        }
    }




}
