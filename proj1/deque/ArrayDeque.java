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
        T[] newItems = (T[]) new Object[size*2];
        for(int i=0;i<size;i++){
            newItems[i] = items[(head + i) % size];
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    public void addFirst(T item){
        if(size == items.length){
            resize();
        }
        head=head-1+size;
        items[head] = item;
        size+=1;
    }

    public void addLast(T item){
        if(size == items.length){
            resize();
        }
        tail=tail+1;
        items[tail] = item;
        size+=1;
    }

    
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        head=head+1;
        size-=1;
        return items[head-1];
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        tail=tail-1;
        size-=1;
        return items[tail+1];
    }

    public T get(int index){
        if(size == 0){
            return null;
        }
        return items[head+index-1];
    }

    public boolean isEmpty(){
        return head==tail;
    }

    public void printDeque(){
        if(head<tail)
        for(int i=head;i<tail;i++) {
            System.out.print(items[i]+"");
        }
        else{
            for(int i=head;i<size;i++) {
                System.out.print(items[i]+"");
            }
            for(int i=0;i<tail;i++) {
                System.out.print(items[i]+"");
            }
        }
    }

    public int size(){
        return size;
    }

    public boolean equals(Object o){
        if(o instanceof ArrayDeque){
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if(size!=other.size){
                return false;
            }
            for(int i=0;i<size;i++){
                if(items[i]!=other.items[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayIterator();
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
