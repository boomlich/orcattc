package towerDefence.view.Interaction;

import java.util.ArrayList;
import java.util.List;

public class InteractionManager {

    private static List<Interactable> intractable = new ArrayList<>();

    public static void addIntractable(Interactable object) {
        intractable.add(object);
    }

    public static List<Interactable> getIntractable() {
        return intractable;
    }

    public static void removeInactive() {
        intractable.removeIf(object -> object.getInteractCode() == InteractCode.INACTIVE);
    }

    public static void clearInteractables() {
        intractable.clear();
    }
}