//Problem 1: Create Item Class
public class Item {
    private final int id;
    private final double weight;
    private final double value;
    private double priorityFactor; // this is what the heap uses

    public Item(int id, double weight, double value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
        this.priorityFactor = 0.0;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public double getPriorityFactor() {
        return priorityFactor;
    }

    public void setPriorityFactor(double priorityFactor) {
        this.priorityFactor = priorityFactor;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Weight: " + weight + ", Value: " + value;
    }
}
