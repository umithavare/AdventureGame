package MaceraOyunu;

public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(player, "Guvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Guvenli evdesiniz!");
        this.getPlayer().setHealth(getPlayer().getOriginalHealth());
        System.out.println("Caniniz Fullendi");
        gameCheck();
        return true;
    }
    public void gameCheck(){
        if (getPlayer().getInventory().isFireWood() &&
                getPlayer().getInventory().isWater()&&
                getPlayer().getInventory().isFood()){
            System.out.println("Tebrikler Butun odulleri kazanip oyunu bitirdiniz.");
            System.out.println("Bundan sonra sadece maden bolgesinde savasabilirsiniz.");
        }
    }
}
