package Containers;

import kalah.Player;

public abstract class Container {
    protected int seedCount;
    protected Player owner;

    public Player getOwner() {
        return this.owner;
    }

    public int getSeedCount() {
        return this.seedCount;
    }

    public void incrementSeed() {
        this.seedCount++;
    }

    public void decrementSeed() {
        this.seedCount--;
    }

    public void addSeeds(int seeds) {
        this.seedCount += seeds;
    }

    public int emptyHouse() {
        int count = this.seedCount;
        this.seedCount = 0;
        return count;
    }

}
