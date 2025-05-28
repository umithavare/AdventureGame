package MaceraOyunu;

import Item.Potion; // Added import

public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Magaza");
    }

    // Static method to define potions
    public static Potion[] potions(){
        Potion[] potionList = new Potion[1];
        potionList[0] = new Potion(1, "Health Potion", 25, 15);
        return potionList;
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu){
            System.out.println("Magazaya hos geldiniz!!");
            System.out.println("1- Silahlar");
            System.out.println("2- Zirhlar");
            System.out.println("3- Iksirler"); // Added Potion option
            System.out.println("4- Cikis Yap"); // Adjusted exit option
            System.out.println("Lutfen bir secim yapiniz");
            int selectCase = Location.input.nextInt();
            while(selectCase < 1 || selectCase > 4){ // Adjusted range
                System.out.print("Lutfen gecerli bir deger giriniz:");
                selectCase = Location.input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3: // Added case for potions
                    printPotion();
                    buyPotion();
                    break;
                case 4: // Adjusted case for exit
                    System.out.println("Tekrardan bekleriz");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printPotion(){ // Method to print potions
        System.out.println("---------Iksirler---------");
        System.out.println();
        for (Potion p : potions()){
            System.out.println(p.getId() + " - " +
                    p.getName() + " < Para : " +
                    p.getCost() + " , Iyilesme : " +
                    p.getHealAmount() + ">" );
        }
        System.out.println("0 - Cikis yap");
    }

    public void buyPotion(){ // Method to buy potions
        System.out.println("Bir iksir seciniz");
        int selectPotionId = input.nextInt();
        while(selectPotionId < 0 || selectPotionId > potions().length){
            System.out.print("Lutfen gecerli bir deger giriniz:");
            selectPotionId = Location.input.nextInt();
        }

        if(selectPotionId != 0){
            Potion selectedPotion = null;
            for(Potion p : potions()){
                if(p.getId() == selectPotionId){
                    selectedPotion = p;
                    break;
                }
            }

            if (selectedPotion != null){
                if (selectedPotion.getCost() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiye bulunmamaktadir");
                }
                else {
                    System.out.println(selectedPotion.getName() + " iksirini satin aldiniz.");
                    int balance = this.getPlayer().getMoney() - selectedPotion.getCost();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiyeniz : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().addPotion(selectedPotion); // Add potion to inventory
                    System.out.println(selectedPotion.getName() + " envanterinize eklendi."); // Updated message
                }
            }
        }
    }

    public void printWeapon(){
        System.out.println("---------Silahlar---------");
        System.out.println();
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " +
                    w.getName() + " < Para : " +
                    w.getPrice() + " , Hasar : " +
                    w.getDamage() + ">" );
        }
        System.out.println("0 - Cikis yap");

    }
    public void buyWeapon(){
        System.out.println("Bir silah seciniz");
        int selectWeaponId = input.nextInt();
        // Corrected to use static access for weapons()
        while(selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){
            System.out.print("Lutfen gecerli bir deger giriniz:");
            selectWeaponId = Location.input.nextInt();
        }

        if(selectWeaponId != 0){
            // Corrected to use static access for getWeaponById()
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeaponId);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiye bulunmamaktadir");
                }
                else {
                    System.out.println(selectedWeapon.getName() + " silahini satin aldiniz.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.println("onceki silahiniz: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahiniz: " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }

    }
    public void buyArmor(){
        System.out.println("Bir zirh seciniz");
        int selectArmorId = input.nextInt();
        // Corrected to use static access for armors()
        while(selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.print("Lutfen gecerli bir deger giriniz:");
            selectArmorId = Location.input.nextInt();
        }
        if (selectArmorId != 0){
            // Corrected to use static access for getArmorById()
            Armor selectedArmor = Armor.getArmorById(selectArmorId);
            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiye bulunmamaktadir");
                }
                else {
                    System.out.println(selectedArmor.getName() + " zirhini satin aldiniz.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.println("onceki zirhiniz: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Yeni zirhiniz: " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
        }
    }
    public void printArmor(){
        System.out.println("---------Zirhlar---------");
        System.out.println();
        for (Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " +
                    a.getName() + " s< Para : " +
                    a.getPrice() + " , Zirh : " +
                    a.getBlock() + ">" );
        }
        System.out.println("0 - Cikis yap");
    }

}
