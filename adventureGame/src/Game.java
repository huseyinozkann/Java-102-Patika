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
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("----------------Bölgeler----------------");
            System.out.println();
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Mağaza");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz :");
        int selectloc = input.nextInt();
        switch (selectloc){
            case 1:
                location = new SafeHouse(player);
                break;
            case 2:
                location = new ToolStore(player);
                break;
            default:
                location = new SafeHouse(player);
        }
        if (!location.onLocation()){
            System.out.println("GMAE OVER !");
            break;
        }
        }
    }
}
