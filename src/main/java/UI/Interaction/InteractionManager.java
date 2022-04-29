package UI.Interaction;

import java.util.ArrayList;
import java.util.List;

public class InteractionManager {

    private static final List<Interactable> intractable = new ArrayList<>();

    /**
     * @param object interactable object to be added
     */
    public static void addIntractable(Interactable object) {
        intractable.add(object);
    }

    /**
     * @return list of all currently active interactables
     */
    public static List<Interactable> getIntractable() {
        return intractable;
    }

    /**
     * Remove any interactables set to inactive
     */
    public static void removeInactive() {
        intractable.removeIf(object -> object.getInteractCode() == InteractCode.INACTIVE);
    }

    /**
     * Remove all interactables
     */
    public static void clearInteractables() {
        intractable.clear();
    }
}