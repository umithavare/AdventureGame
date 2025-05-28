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
                System.out.println("7 - Iksir Kullan"); // Added Use Potion option
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
                        location = new SafeHouse(player); // Stay in SafeHouse if already awarded
                    }
                        break;
                    case 4: if(!player.getInventory().isWater()){
                        System.out.println("Nehire giriyorsun");
                        location = new River(player);
                    }
                    else {
                        System.out.println("Daha once bu odulu kazandigin icin bu haritaya girilemez");
                        location = new SafeHouse(player); // Stay in SafeHouse if already awarded
                    }
                        break;
                    case 5:if (!player.getInventory().isFireWood()){
                        location = new Forest(player);
                    }
                    else {
                        System.out.println("Daha once bu odulu kazandıgın icin bu haritaya girilemez");
                        location = new SafeHouse(player); // Stay in SafeHouse if already awarded
                    }
                        break;
                    case 6:
                        location = new Mine(player);
                        break;
                    case 7: // Added case for Use Potion
                        player.usePotion();
                        // No location change, loop will reprint menu.
                        // Ensure current location context is maintained if player was somewhere else.
                        // For now, it defaults to re-showing main menu.
                        // If player was in SafeHouse, new SafeHouse(player) is fine.
                        // If player was in a battle or other location, this needs more nuanced handling.
                        // For now, to prevent null location, we can re-assign to a default safe spot
                        // or simply skip the location.onLocation() call for this specific action.
                        continue; // Use continue to re-iterate the loop and show menu again
                    default:
                        System.out.println("Lutfen gecerli bir bolge giriniz");
                        location = new SafeHouse(player); // Default to SafeHouse for invalid input
                        break;
                }

                if (location == null){ // This handles game exit (case 0)
                    System.out.println("Oyun bitti Tekrardan bekleriz");
                    break;
                }

                // This block should only run if a location was selected and it's not the "Use Potion" action
                if (selectLocation != 7 && !location.onLocation()){
                    System.out.println("Oldunuz!! Oyun bitti");
                    break;
                }
        }
    }

}
