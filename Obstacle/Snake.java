package MaceraOyunu;

import java.util.Random;

public class Snake extends Obstacle{
    Random rnd = new Random();
    public Snake() {
        super(3, "Yilan", 4, 12, 0);
        int randomDamage = rnd.nextInt(4) + 3;
        this.setDamage(randomDamage); // yilana 3-6 (dahil) arasinda random damage veriyoruz
    }
}
