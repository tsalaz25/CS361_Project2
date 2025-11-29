//Problem 2: Implement the MaxBinHeap

import java.util.*;
public class MaxBinHeap {
    private final List<Item> heap;

    public MaxBinHeap(){
        this.heap = new ArrayList<>();
    }

    public MaxBinHeap(List<Item> items){
        this.heap = new ArrayList<>(items);
        buildHeap();
    }

    //Operations
    public boolean isEmpty(){return heap.isEmpty();}
    public int size(){return heap.size();}

    public void insert(Item item){
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public Item peekMax(){
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is Empty");
        }
        return heap.get(0);
    }

    public Item extractMax(){
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is Empty");
        }
        Item max = heap.get(0);
        Item last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()){
            heap.set(0,last);
            siftDown(0);
        }
        return max;
    }


    //Internal Helpers
    private void buildHeap(){
        for (int i = parentIndex(heap.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index){
        int size = heap.size();
        while (true){
            int left = leftChildIndex(index);
            int right = rightChildIndex(index);
            int largest = index;
            if (left < size &&
                    heap.get(left).getPriorityFactor() > heap.get(largest).getPriorityFactor()) {
                largest = left;
            }
            if (right < size &&
                    heap.get(right).getPriorityFactor() > heap.get(largest).getPriorityFactor()) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void siftUp(int index){
        while (index > 0) {
            int parent = parentIndex(index);
            if (heap.get(index).getPriorityFactor() > heap.get(parent).getPriorityFactor()) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j){
        Item temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int parentIndex(int index){ return (index - 1) / 2; }
    private int leftChildIndex(int index){ return (index * 2) + 1;}
    private int rightChildIndex(int index){ return (index * 2) + 2;}
}
