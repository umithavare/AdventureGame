package MaceraOyunu;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obstacleNumber = this.randomObstacleNumber();

        System.out.println("Suan buradasiniz : " + this.getName());
        System.out.println("Tehlikeli olabilir dikkatli ol, burada " + obstacleNumber + " tane " +this.getObstacle().getName() + " Yasiyor");
        System.out.println("Savasmak icin 'S', Kacmak icin 'K' tusuna bas ");
        String selectChoice = input.nextLine().toUpperCase();
        if (selectChoice.equals("S") && combat(obstacleNumber)){
            System.out.println(this.getName() + " konumundaki tum dusmanlari yendiniz");
            if(this.award.equals("Water")){
                System.out.println("Su kazandiniz");
                getPlayer().getInventory().setWater(true);
            }else if (this.award.equals("FireWood")){
                System.out.println("Odun kazandiniz");
                getPlayer().getInventory().setFireWood(true);
            }else if (this.award.equals("food")) {
                System.out.println("Yemek kazandiniz");
                getPlayer().getInventory().setFood(true);
            }
            return true;
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Oldunuz!!");
            return false;
        }
        return true;
    }
    public boolean combat(int obstacleNumber){
        for(int i = 1 ; i <= obstacleNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            Random random = new Random();
            int randomChance = random.nextInt(2);
            if (randomChance == 0){
                System.out.println("Sans sizde. Ilk siz baslayacaksiniz");
                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){ // kullanicinin il baslama senaryosu
                    System.out.println("Fight 'F', Kacmak icin 'K' tusuna bas ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("F")){
                        System.out.println("Siz vurdunuz");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    if (this.getObstacle().getHealth() > 0){
                        System.out.println();
                        System.out.println(this.getObstacle().getName() + " size vurdu!!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    }
                else return false;
                }
            }
            else {
                System.out.println("Ilk once dusman baslayacak");
                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){ // Canavarin ilk baslama senaryosu
                    System.out.println("Fight icin 'F', Kacmak icin 'K' tusuna bas ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("F")){
                        System.out.println("Dusman vurdu");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        if (this.getPlayer().getHealth() > 0){
                            System.out.println();
                            System.out.println("siz vurdunuz!!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                    else return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                if (getObstacle().getName().equals("Yilan")){
                    System.out.println("Savasi kazandiniz.");
                    snakeAward();
                }
                else{
                System.out.println("Savasi kazandiniz");
                System.out.println(this.getObstacle().getAward() + " Para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Guncel paraniz : " + this.getPlayer().getMoney());
                }
            }
            else return false;
        }
        return true;
    }
    public void snakeAward(){
        Random random = new Random();
        int dropChance = random.nextInt(100) + 1;
        if (dropChance <= 15){ // Tufek kazanma ihtimalinin senaryosu
            int weaponDropChance = random.nextInt(100) + 1;
            if (weaponDropChance <= 20){
                System.out.println("Tebrikler Tufek Kazandiniz");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(3));
            }
            else if (weaponDropChance <= 50){
                System.out.println("Tebrikler Kilic Kazandiniz");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(2));
            }
            else if (weaponDropChance <= 100){
                System.out.println("Tebrikler Tabanca Kazandiniz");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(1));
            }
        }
        else if (dropChance <= 30){
            int armorDropChance = random.nextInt(100) + 1;
            if (armorDropChance <= 20){
                System.out.println("Tebrikler agir zirh kazandiniz");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(3));
            }
            else if (armorDropChance <= 50){
                System.out.println("Tebrikler orta zirh kazandiniz");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(2));
            }
            else if (armorDropChance <= 100){
                System.out.println("Tebrikler hafif zirh kazandiniz");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(1));
            }
        }
        else if (dropChance <= 55){
            int moneyDropChance = random.nextInt(100) + 1;
            if (moneyDropChance <= 20 ){
                System.out.println("Tebrikler 10 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            }
            if (moneyDropChance <= 50 ){
                System.out.println("Tebrikler 5 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            }
            if (moneyDropChance <= 100 ){
                System.out.println("Tebrikler 1 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        }
        else System.out.println("Odul olarak Hicbir Sey kazanmadiniz...");

    }
    public void afterHit(){
        System.out.println("Caniniz : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Cani : " + this.getObstacle().getHealth() );
        System.out.println("--------------------");
    }

    public void playerStats(){
        System.out.println("Oyuncu degerleri");
        System.out.println("-------------------");
        System.out.println("Saglik : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zirh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Bloklama : " +this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Bakiye : " + this.getPlayer().getMoney());
        System.out.println();
    }
    public void obstacleStats(int i){
        System.out.println(i + "." + this.getObstacle().getName() + " Degerleri");
        System.out.println("-------------------");
        System.out.println("Saglik : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Odulu : " + this.getObstacle().getAward());
        System.out.println();
    }
    public int randomObstacleNumber(){ //Random sayıda canavar üretmek için kullanıldı
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) +1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }


    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
