package towerDefence.view.Interaction;

import java.util.ArrayList;
import java.util.List;

public class InteractionManager {

    public static List<Interactable> intractable = new ArrayList<>();

    public static void addIntractable(Interactable object) {
        intractable.add(object);
    }

    public static List<Interactable> getIntractable() {
        return intractable;
    }
}