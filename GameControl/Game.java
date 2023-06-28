package MaceraOyunu;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera Oyununa Hos Geldiniz.");
        System.out.print("Lutfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hos Geldiniz");
        System.out.println("lutfen bir karakter seciniz: ");
        System.out.println("****************************");
        player.selectChar();
        Location location = null;
        while (true){
                player.printInfo();
                System.out.println();
                System.out.println("----------  Bolgeler  ----------");
                System.out.println();
                System.out.println("1 - Guvenli Ev >> Burasi guvenlidir. Dusman yok");
                System.out.println("2 - magaza >> Silah ve zirh satin alabilirsiniz");
                System.out.println("3 - Magara --> Odul <yemek> , dikkat Zombie Cikabilir!!");
                System.out.println("4 - Nehir --> Odul <su> , dikkat Ayi Cikabilir!!");
                System.out.println("5 - Orman --> Odul <odun> , dikkat Vampir Cikabilir!!");
                System.out.println("6 - Maden --> Odul <silah,zirh,para> , dikkat Yilan Cikabilir!!");
                System.out.println("0 - Cikis yap ve oyunu sonlandir");
                int selectLocation = input.nextInt();
                switch (selectLocation){
                    case 0:
                        location = null;
                        break;
                    case 1 :
                        location = new SafeHouse(player);
                        break;
                    case 2:
                        location = new ToolStore(player);
                        break;
                    case 3: if(!player.getInventory().isFood()){
                        System.out.println("Magaraya giriyorsun");
                        location = new Cave(player);
                    }
                    else {
                        System.out.println("Daha once bu odulu kazandıgın icin bu haritaya girilemez");
                        location = new SafeHouse(player);
                    }
                        break;
                    case 4: if(!player.getInventory().isWater()){
                        System.out.println("Nehire giriyorsun");
                        location = new River(player);
                    }
                    else {
                        System.out.println("Daha once bu odulu kazandigin icin bu haritaya girilemez");
                        location = new SafeHouse(player);
                    }
                        break;
                    case 5:if (!player.getInventory().isFireWood()){
                        location = new Forest(player);
                    }
                    else {
                        System.out.println("Daha once bu odulu kazandıgın icin bu haritaya girilemez");
                        location = new SafeHouse(player);
                    }
                        break;
                    case 6:
                        location = new Mine(player);
                        break;
                    default:
                        System.out.println("Lutfen gecerli bir bolge giriniz");
                        break;
                }
                if (location == null){
                    System.out.println("Oyun bitti Tekrardan bekleriz");
                    break;
                }
                if (!location.onLocation()){
                    System.out.println("Oldunuz!! Oyun bitti");
                    break;
                }
        }
    }

}
