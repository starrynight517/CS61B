package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
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

    private void resize(int resizeNumber){
        T[] newItems = (T[]) new Object[resizeNumber];
        for(int i=0;i<size;i++){
            newItems[i] = items[(head + i) % items.length];
        }
        items = newItems;
        head = 0;
        tail = size == 0 ? 0 : size-1;
    }

    @Override
    public void addFirst(T item){
        if(size == items.length){
            resize(items.length*2);
        }
        if(size!=0){
            head = (head - 1 + items.length) % items.length;
        }
        items[head] = item;
        size+=1;
    }

    @Override
    public void addLast(T item){
        if(size == items.length){
            resize(items.length*2);
        }
        if (size != 0) {
            tail = (tail + 1) % items.length;
        }
        items[tail] = item;
        size+=1;
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T removedItem =items[head];
        items[head] = null;
        if(size!=1){
            head=(head + 1 ) % items.length;
        }
        size-=1;
        if(size<items.length/4){
            resize(items.length/2);
        }
        return removedItem;
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T removeditem =items[tail];
        items[tail] = null;
        if(size!=1){
            tail=(tail-1 + items.length) % items.length;
        }
        size-=1;
        if(size<items.length/4){
            resize(items.length/2);
        }
        return removeditem;
    }

    @Override
    public T get(int index){
        if(size == 0){
            return null;
        }
        return items[(head+index)% items.length];
    }


    @Override
    public void printDeque(){
        for(int i = 0; i < size; i++) {
            System.out.print(items[(head + i) % items.length] + " ");
        }
        System.out.println();
    }

    @Override
    public int size(){
        return size;
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



