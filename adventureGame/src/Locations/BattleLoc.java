package Locations;

import Obstacle.Obstacle;

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
    boolean onLocation() {

        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol ! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = Location.input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)){
            //savaş
            if (combat(obsNumber)){
                System.out.println(this.getName() + " tüm düşmanları yendiniz !");
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean combat (int obsNumber){
        for (int i = 1; i<= obsNumber; i++){
            int firstHit = firstHit();
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = Location.input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if (firstHit == 0){
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }else if (firstHit == 1){
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }

                }else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz !");
                if (this.getObstacle().getName().equals("Obstacle.Snake")){
                    snakeAward();
                }
                System.out.println(this.getObstacle().getAward() + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                locationAward();
                System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
            }else {
                return false;
            }
        }
        return true;
    }

    public void snakeAward(){
        Random r = new Random();
        int chance = r.nextInt(100);
        int itemChance = r.nextInt(100);
        int weaponChance = r.nextInt(100);
        int armorChance = r.nextInt(100);
        int moneyChance = r.nextInt(100);
        if (chance < 55){       //bir öge çıktıysa
            if (itemChance <= 30){
                if (weaponChance <= 20){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println(this.getPlayer().getInventory().getWeapon().getName() +" kazandınız !");
                } else if (weaponChance > 20 && weaponChance <= 50) {
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                    System.out.println(this.getPlayer().getInventory().getWeapon().getName() +" kazandınız !");
                } else if (weaponChance > 50) {
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                    System.out.println(this.getPlayer().getInventory().getWeapon().getName() +" kazandınız !");
                }
            } else if (itemChance > 30 && itemChance <=60) {
                if (armorChance <= 20){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " kazandınız !");
                } else if (armorChance > 20 && armorChance <= 50) {
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " kazandınız !");
                } else if (armorChance > 50) {
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " kazandınız !");
                }
            } else if (itemChance > 60) {
                if (moneyChance <= 20){
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    System.out.println("10 altın kazandınız !");
                } else if (moneyChance > 20 && moneyChance <= 50) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    System.out.println("5 altın kazandınız !");
                } else if (moneyChance > 50) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                    System.out.println("1 altın kazandınız !");
                }
            }
        }else {
            System.out.println("Ödül kazanamadınız !");
        }

    }

    public void locationAward(){
        if (this.getName().equals("Mağara")){
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Orman")){
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Nehir")){
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Maden")){
            this.getPlayer().getInventory().setFood(this.award);
        }
    }
    public int firstHit(){
        Random r = new Random();
        return r.nextInt(2);
    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("--------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int i){
        System.out.println(i + " - " + this.getObstacle().getName() + " Değerleri");
        System.out.println("--------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        // 0,1 => 1,2
        return r.nextInt(this.maxObstacle) + 1;
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
