package MaceraOyunu;

import Item.Potion; // Import Potion
import java.util.ArrayList; // Import ArrayList

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean water;
    private boolean fireWood;
    private boolean food;
    private ArrayList<Potion> potions; // Add potions list

    public Inventory(){
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.armor = new Armor("basit",-1,0,0);
        this.potions = new ArrayList<>(); // Initialize potions list
    }

    public ArrayList<Potion> getPotions() { // Getter for potions
        return this.potions;
    }

    public void addPotion(Potion potion) { // Method to add a potion
        this.potions.add(potion);
    }

    public void removePotion(Potion potion) { // Method to remove a potion
        this.potions.remove(potion);
    }

    public boolean hasPotions() { // Method to check if inventory has any potions
        return !this.potions.isEmpty();
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFireWood() {
        return fireWood;
    }

    public void setFireWood(boolean fireWood) {
        this.fireWood = fireWood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }
}
