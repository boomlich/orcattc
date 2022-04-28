package UI.UI.components;

public class ContainerBorder {

    public final int north, east, south, west;


    public ContainerBorder(int border) {
        north = border;
        east = border;
        south = border;
        west = border;
    }

    public ContainerBorder(int north, int east, int south, int west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
}
