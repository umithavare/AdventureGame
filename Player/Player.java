package MaceraOyunu;

import Item.Potion; // Import Potion
import java.util.ArrayList; // For getPotions() - though it returns ArrayList, direct usage here is minimal
import java.util.Scanner;

public class Player  {
    private int damage;
    private int health;
    private int originalHealth;
    private int money ;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void printInfo(){
        System.out.println("Silahiniz : " + this.getInventory().getWeapon().getName() +
                ", Zirhiniz: " + this.getInventory().getArmor().getName() +
                ", Engelleme: " + this.getInventory().getArmor().getBlock() +
                ", Hasar : "+ this.getTotalDamage() +
                ", Saglik : " + this.getHealth() +
                ", Para : " + this.getMoney());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0 )
            health = 0;
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public void selectChar(){
        GameCharacter[] charList = {new Samurai(),new Archer(),new Knight()};
        System.out.println("Karakterler");
        for (GameCharacter gameCharacter:charList) {
            System.out.println("ID:" + gameCharacter.getId() +
                    "\tKarakter:" + gameCharacter.getName() +
                    "\tHasar:" + gameCharacter.getDamage() +
                    "\tSaglikk:" + gameCharacter.getHealth() +
                    "\tPara:"+ gameCharacter.getMoney());
        }
        System.out.println("****************************");
        System.out.print("Lutfen bir Karakter Seciniz: ");
         int selectChar = input.nextInt();
         switch (selectChar){
             case 1:
                 initPlayer(new Samurai());
                 break;
             case 2:
                 initPlayer(new Archer());
                 break;
             case 3:
                 initPlayer(new Knight());
                 break;
             default:
                 initPlayer(new Samurai());
         }
        System.out.println("Karakter: " + this.getCharName() +
                "\tHasar:"+ this.getDamage() +
                "\tSaglik:" + this.getHealth() +
                "\tPara:" + this.getMoney());

    }

    public void initPlayer(GameCharacter gameCharacter){
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setOriginalHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
        this.setCharName(gameCharacter.getName());

    }

    public void usePotion() {
        if (!this.getInventory().hasPotions()) {
            System.out.println("Envanterinizde hic iksir yok!");
            return;
        }

        // For now, we assume the player wants to use the first available "Health Potion"
        // If multiple potion types existed, a selection mechanism would be needed here.
        Potion potionToUse = null;

        ArrayList<Potion> currentPotions = this.getInventory().getPotions();
        for (int i = 0; i < currentPotions.size(); i++) {
            // Assuming the main Potion is named "Health Potion" as defined in ToolStore
            if (currentPotions.get(i).getName().equals("Health Potion")) {
                potionToUse = currentPotions.get(i);
                break;
            }
        }

        if (potionToUse != null) {
            if (this.getHealth() == this.getOriginalHealth()) {
                System.out.println("Sagliginiz zaten maksimumda, iksir kullanmaya gerek yok.");
                return;
            }

            int healAmount = potionToUse.getHealAmount();
            int currentHealth = this.getHealth();
            int maxHealth = this.getOriginalHealth();

            this.setHealth(Math.min(currentHealth + healAmount, maxHealth));
            this.getInventory().removePotion(potionToUse); // removePotion should handle object removal

            System.out.println(potionToUse.getName() + " kullandiniz. Caniniz " + healAmount + " artti.");
            System.out.println("Yeni Saglik: " + this.getHealth() + "/" + this.getOriginalHealth());
        } else {
            // This case might occur if inventory has potions, but none are "Health Potion"
            System.out.println("Kullanilacak uygun bir 'Health Potion' bulunamadi.");
        }
    }
}
