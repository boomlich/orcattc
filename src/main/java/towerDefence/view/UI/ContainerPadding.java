package towerDefence.view.UI;

public class ContainerPadding {

    int horizontal, vertical;

    public ContainerPadding(int padding) {
        horizontal = padding;
        vertical = padding;
    }

    public ContainerPadding(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
