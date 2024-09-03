package deque;

import java.util.Iterator;

public class ArrayDeque<T>{
    private T[] items;
    private int head;
    private int tail;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void resize(){
        T[] newItems = (T[]) new Object[items.length*2];
        for(int i=0;i<size;i++){
            newItems[i] = items[(head + i) % items.length];
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    public void addFirst(T item){
        if(size == items.length){
            resize();
        }
        items[head] = item;
        head = (head - 1 + items.length) % items.length;
        size+=1;
    }

    public void addLast(T item){
        if(size == items.length){
            resize();
        }
        items[tail] = item;
        tail=(tail+1) % items.length;
        size+=1;
    }


    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T removeditem =items[head];
        head=(head + 1 ) % items.length;
        size-=1;
        return removeditem;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        T removeditem =items[tail];
        tail=(tail-1 + items.length) % items.length;
        size-=1;
        return removeditem;
    }

    public T get(int index){
        if(size == 0){
            return null;
        }
        return items[(head+index)% items.length];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        for(int i = 0; i < size; i++) {
            System.out.print(items[(head + i) % items.length] + " ");
        }
        System.out.println();
    }


    public int size(){
        return size;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T item = this.items[(head + i) % items.length];
            Object otherItem = other.items[(other.head + i) % other.items.length];
            if (item == null ? otherItem != null : !item.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int wizPos;
        public ArrayIterator() {
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



