public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("----- Mağazaya Hosgeldiniz ! -----");
        System.out.println("1 - Silahlar");
        System.out.println("2 - Zırhlar");
        System.out.println("3 - Çıkış Yap");
        System.out.print("Seçiminiz : ");
        int selectCase = input.nextInt();

        while(selectCase < 1 || selectCase > 3){
            System.out.print("Geçersiz değer, tekrar giriniz : ");
            selectCase = input.nextInt();
        }
        switch (selectCase){
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                break;
            case 3:
                System.out.println("Bir daha bekleriz !");
                return true;
        }

        return true;
    }
    public void printWeapon(){
        System.out.println("------ Silahlar ------");
        System.out.println();
        for (Weapon weapon : Weapon.weapons()){
            System.out.println(weapon.getId()+" - "
                    +weapon.getName() +
                    " <Para : "+ weapon.getPrice() +
                    ", Hasar : " + weapon.getDamage()+ ">");
        }


    }

    public void buyWeapon(){
        System.out.println("Bir silah seçiniz : ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 1 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz değer, tekrar giriniz:");
            selectWeaponID = input.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
        if (selectedWeapon != null){
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli Paranız Bulunmamaktadır !");
            }else {
                //satın alma
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan Paranız : " + this.getPlayer().getMoney());
                System.out.println("Önceki Silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("Yeni Silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
            }
        }
    }
    public void printArmor(){
        System.out.println("------ Zırhlar ------");
    }
}
