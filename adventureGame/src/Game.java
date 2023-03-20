import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.println("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz !! burada yaşananların hepsi gerçektir !!");
        System.out.println("Lütfen bir karakter seçiniz :");
        System.out.println("---------------------------------------------------------");
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            if (player.getInventory().getWater().equals("water") && player.getInventory().getFirewood().equals("fireWood") && player.getInventory().getFood().equals("food") && player.getInventory().getSnake().equals("treasury")){
                System.out.println("------------TEBRİKLER------------");
                System.out.println();
                System.out.println("Tüm bölümleri tamamlayarak oyunu bitirdiniz !");
                break;
            }
            System.out.println();
            System.out.println("----------------Bölgeler----------------");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> burası sizin için güvenli bir ev, düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı --> Silah veya Zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara --> Ödül<Yemek>, dikkatli ol karşına zombi çıkabilir !");
            System.out.println("4 - Orman --> Ödül<Odun>, dikkatli ol karşına vampir çıkabilir !");
            System.out.println("5 - Nehir --> Ödül<Su>, dikkatli ol karşına ayı çıkabilir !");
            System.out.println("6 - Maden --> Dikkatli ol karşına yılan çıkabilir !");
            System.out.println("0 - Çıkış Yap --> Oyunu sonlandır !");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz :");
        int selectloc = input.nextInt();
        boolean isCompleted = true;
        switch (selectloc){
            case 0:
                location = null;
            case 1:
                location = new SafeHouse(player);
                break;
            case 2:
                location = new ToolStore(player);
                break;
            case 3:
                if (player.getInventory().getFood().equals("food")){
                    System.out.println("Zaten bu bölümü tamamladınız ve ödülü aldınız !");
                    isCompleted = false;
                    break;
                }
                location = new Cave(player);
                break;
            case 4:
                if (player.getInventory().getFood().equals("firewood")){
                    System.out.println("Zaten bu bölümü tamamladınız ve ödülü aldınız !");
                    isCompleted = false;
                    break;
                }
                location = new Forest(player);
                break;
            case 5:
                if (player.getInventory().getFood().equals("water")){
                    System.out.println("Zaten bu bölümü tamamladınız ve ödülü aldınız !");
                    isCompleted = false;
                    break;
                }
                location = new River(player);
                break;
            case 6:
                if (player.getInventory().getFood().equals("treasury")){
                    System.out.println("Zaten bu bölümü tamamladınız ve ödülü aldınız !");
                    isCompleted = false;
                    break;
                }
                location = new Mine(player);
                break;
            default:
                System.out.println("Lütfen geçerli bir bölge giriniz !");
        }
        if (location == null){
            System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin !");
            break;
        }

        if (isCompleted){
            if (!location.onLocation()){
                System.out.println("GAME OVER !");
                break;

                }
            }
        }
    }
}
