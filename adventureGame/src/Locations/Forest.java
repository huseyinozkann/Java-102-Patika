package Locations;

import Locations.BattleLoc;
import Obstacle.Vampire;

public class Forest extends BattleLoc {
    public Forest(Player player) {
        super(player,"Orman",new Vampire(),"firewood",3);
    }
}
