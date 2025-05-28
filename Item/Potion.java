package Item;

public class Potion {
    private int id;
    private String name;
    private int healAmount;
    private int cost;

    public Potion(int id, String name, int healAmount, int cost) {
        this.id = id;
        this.name = name;
        this.healAmount = healAmount;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public int getCost() {
        return cost;
    }
}
