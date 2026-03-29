package MaceraOyunu;

import Item.Potion;
import java.util.ArrayList;

/**
 * Bu sınıf, mevcut Player sınıfının konsol bağımlılığından (System.out.println)
 * arındırılmış bir versiyonunu temsil eder. Mobil bir UI ile çalışmaya hazırdır.
 */
public class RefactoredPlayer {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private GameOutput output; // Konsol veya Mobil UI fark etmeksizin çıktı üretir.

    public RefactoredPlayer(String name, GameOutput output) {
        this.name = name;
        this.inventory = new Inventory();
        this.output = output;
    }

    public void printInfo() {
        // Doğrudan yazdırmak yerine çıktı arayüzünü kullanır.
        output.showPlayerStats(null); // Parametre olarak 'this' geçilebilir, burada basitleştirilmiştir.
    }

    public void usePotion() {
        if (!this.getInventory().hasPotions()) {
            output.showMessage("Envanterinizde hic iksir yok!");
            return;
        }

        Potion potionToUse = null;
        ArrayList<Potion> currentPotions = this.getInventory().getPotions();
        for (Potion p : currentPotions) {
            if (p.getName().equals("Health Potion")) {
                potionToUse = p;
                break;
            }
        }

        if (potionToUse != null) {
            if (this.getHealth() == this.getOriginalHealth()) {
                output.showMessage("Sagliginiz zaten maksimumda.");
                return;
            }

            int healAmount = potionToUse.getHealAmount();
            this.setHealth(Math.min(this.getHealth() + healAmount, this.getOriginalHealth()));
            this.getInventory().removePotion(potionToUse);

            output.showMessage(potionToUse.getName() + " kullandiniz. Yeni Saglik: " + this.getHealth());
        }
    }

    // Getter ve Setter metotları mevcut sınıfla aynı kalacaktır...
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.max(0, health); }
    public int getOriginalHealth() { return originalHealth; }
    public Inventory getInventory() { return inventory; }
    public void setOriginalHealth(int originalHealth) { this.originalHealth = originalHealth; }
}
