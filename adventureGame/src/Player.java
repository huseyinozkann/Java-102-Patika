import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name){

        this.name = name;
        this.inventory = new Inventory();
    }
    public void selectChar(){
        GameChar[] charlist = {new Samurai(),new Archer(),new Knight()};
        System.out.println("Karakterler");
        System.out.println("---------------------------------");
        for (GameChar gameChar : charlist){
            System.out.println("ID:"+ gameChar.getId()+
                    "\tKarakter: " +gameChar.getName() +
                    "\tHasar: " + gameChar.getDamage() +
                    "\tSağlık: " + gameChar.getHealth() +
                    "\tPara: " + gameChar.getMoney());
        }
        System.out.println("---------------------------------");
        System.out.println("Lütfen bir karakter giriniz !");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("Karakter : " + this.getCharName()+
                ", Hasar : " + this.getDamage()+
                ", Sağlık : "+ this.getHealth()+
                ", Para : "+this.getMoney());
    }

    public void selectLoc(){

    }
    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo(){
        System.out.println(
                "Silahınız : " + this.getInventory().getWeapon().getName()+
                ", Hasarınız : " + this.getDamage()+
                ", Sağlık : "+ this.getHealth()+
                ", Para : "+this.getMoney());
    }
    public int getDamage() {

        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
