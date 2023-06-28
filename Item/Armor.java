package MaceraOyunu;

public class Armor {
    private String name;
    private int id;
    private int price;
    private int block;

    public Armor(String name, int id, int price, int block) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.block = block;
    }
    public static Armor getArmorById(int id){
        for (Armor a : Armor.armors()){
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }
    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor("Hafif",1,15,1);
        armorList[1] = new Armor("Orta",2,25,3);
        armorList[2] = new Armor("Agir",3,45,5);
        return armorList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
}
