//Problems 3-5

import java.util.*;
public class KnapsackDemo {
    private static final double CAPACITY = 67.0;


    public static void main(String[] args) {
        List<Item> items = createItems();

        System.out.println("Greedy 1: Highest value first");
        runGreedyByValue(items, CAPACITY);
        System.out.println();

        System.out.println("Greedy 2: Lightest items first");
        runGreedyByLightness(items, CAPACITY);
        System.out.println();

        System.out.println("Greedy 3: Highest value / weight ratio");
        runGreedyByValueToWeight(items, CAPACITY);
    }

    private static List<Item> createItems() {
        List<Item> items = new ArrayList<>();
        //From Assignment
        items.add(new Item(0, 23, 505));
        items.add(new Item(1, 26, 352));
        items.add(new Item(2, 20, 458));
        items.add(new Item(3, 18, 220));
        items.add(new Item(4, 32, 354));
        items.add(new Item(5, 27, 414));
        items.add(new Item(6, 29, 498));
        items.add(new Item(7, 26, 545));
        items.add(new Item(8, 30, 473));
        items.add(new Item(9, 27, 543));
        return items;
    }

    //Core Greedy Algo
    private static List<Item> greedyKnapsack(List<Item> items, double capacity) {
        MaxBinHeap heap = new MaxBinHeap(items);
        List<Item> selected = new ArrayList<>();
        double currentWeight = 0.0;
        double currentValue = 0.0;

        while (!heap.isEmpty()){
            Item item = heap.extractMax();
            double newWeight = item.getWeight() + currentWeight;

            if (newWeight <= capacity){
                selected.add(item);
                currentWeight = newWeight;
                currentValue += item.getValue();
            }
        }

        System.out.printf("Total weight: %.2f / %.2f%n", currentWeight, capacity);
        System.out.printf("Total value : %.2f%n", currentValue);
        return selected;
    }



    //Different Implementations for Problems
    //Problem 3: Consider the Highest Value (Priority = Value)
    public static void runGreedyByValue(List<Item> original, double capacity) {
        List <Item> items = copyItems(original);
        for (Item I : items) {
            I.setPriorityFactor(I.getValue());
        }
        List<Item> chosen = greedyKnapsack(items, capacity);
        printResult(chosen);
    }

    //Problem 4: Consider the Lightest (Priority = -Weight)
    public static void runGreedyByLightness(List<Item> original, double capacity) {
        List <Item> items = copyItems(original);
        for (Item I : items) {
            I.setPriorityFactor(-I.getValue());
        }
        List<Item> chosen = greedyKnapsack(items, capacity);
        printResult(chosen);
    }

    //Problem 5: Consider Ratio (Priority = value/weight)
    public static void runGreedyByValueToWeight(List<Item> original, double capacity) {
        List <Item> items = copyItems(original);
        for (Item I : items) {
            I.setPriorityFactor(I.getValue() / I.getWeight());
        }
        List<Item> chosen = greedyKnapsack(items, capacity);
        printResult(chosen);
    }

    // Helpers
    private static void printResult(List<Item> items) {
        System.out.println("Selected Items:");
        for (Item I : items) {
            System.out.println(" " + I);
        }
    }

    private static List<Item> copyItems(List<Item> original) {
        List<Item> copy = new ArrayList<>();
        for (Item I : original) {
            Item clone = new Item(I.getId(), I.getWeight(), I.getValue());
            clone.setPriorityFactor(I.getPriorityFactor());
            copy.add(clone);
        }
        return copy;
    }
}