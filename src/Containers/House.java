package Containers;

import kalah.Player;

public class House extends Container {
    public House(Player owner, int startingSeedCount) {
        this.owner = owner;
        this.seedCount = startingSeedCount;
    }

}
