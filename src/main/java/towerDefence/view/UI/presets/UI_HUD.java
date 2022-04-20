package towerDefence.view.UI.presets;

import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_PlayRound;

public class UI_HUD extends UIContainer {

    GameRenderable gameModel;
    UI_TowerMenu towerMenu;

    public UI_HUD(int width, int height, GameRenderable gameModel) {
        super(width, height);
        this.gameModel = gameModel;

        setBorder(new ContainerBorder(20, 50, 20, 50));

        UI_Champ ui_champ = new UI_Champ(200, 200);
        ui_champ.setAlignment(UIAlignment.SOUTH_WEST);
        this.add(ui_champ);

        UIButton playRound = new UI_Button_PlayRound();
        playRound.setAlignment(UIAlignment.SOUTH);
        this.add(playRound);
    }

    public void addTowerMenu(ITower tower) {
        if (towerMenu == null) {
            createNewTowerMenu(tower);
        } else {
            // Check if targeting new tower
            if (towerMenu.tower != tower) {
                removeTowerMenu();
                createNewTowerMenu(tower);
            } else {
                towerMenu.update();
            }
        }
    }

    private void createNewTowerMenu(ITower tower) {
        towerMenu = new UI_TowerMenu(tower);
        towerMenu.setAlignment(UIAlignment.SOUTH_EAST);
        this.add(towerMenu);
    }

    public void removeTowerMenu() {
        if (towerMenu != null) {
            remove(towerMenu);
            towerMenu = null;
        }
    }
}
