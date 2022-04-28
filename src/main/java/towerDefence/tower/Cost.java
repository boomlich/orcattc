package towerDefence.tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cost {

    private final int buyCost;
    private final int rank1Cost;
    private final int rank2Cost;
    private final int rank3Cost;
    private final int[] allCost;

    public static Cost ARCHER = new Cost(100, 50, 200, 500);
    public static Cost RIFLEMAN = new Cost(250, 100, 350, 1000);
    public static Cost CANNON = new Cost(600, 200, 500, 1250);
    public static Cost WIZARD = new Cost(1000, 200, 750, 2000);

    private Cost(int buyCost, int rank1Cost, int rank2Cost, int rank3Cost) {
        this.buyCost = buyCost;
        this.rank1Cost = rank1Cost;
        this.rank2Cost = rank2Cost;
        this.rank3Cost = rank3Cost;
        allCost = new int[]{buyCost, rank1Cost, rank2Cost, rank3Cost};
    }

    public int getBuyCost() {
        return buyCost;
    }

    public int getRank1Cost() {
        return rank1Cost;
    }

    public int getRank2Cost() {
        return rank2Cost;
    }

    public int getRank3Cost() {
        return rank3Cost;
    }

    public int getCost(int rank) {
        return allCost[rank];
    }

    public int getSellValue(int rank) {
        int sellValue = 0;
        for (int i = 0; i < rank+1; i++) {
            sellValue += allCost[i];
        }
        return (int) (sellValue * 0.5);
    }
}
