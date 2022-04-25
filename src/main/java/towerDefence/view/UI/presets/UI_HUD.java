package towerDefence.view.UI.presets;

import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_HUD;
import towerDefence.view.UI.presets.buttons.UI_Button_Options;

import java.awt.*;

public class UI_HUD extends UIContainer {

    GameRenderable gameModel;
    UI_TowerMenu towerMenu;
    UI_Button_HUD playButton;
    UITextBox waveNumber;

    public UI_HUD(int width, int height, GameRenderable gameModel) {
        super(width, height);
        this.gameModel = gameModel;

        setBorder(new ContainerBorder(20, 50, 20, 50));

        UI_Champ ui_champ = new UI_Champ(200, 200);
        ui_champ.setAlignment(UIAlignment.SOUTH_WEST);
        this.add(ui_champ);

        playButton = new UI_Button_HUD();
        playButton.setPlay();
        playButton.setAlignment(UIAlignment.SOUTH);
        this.add(playButton);

        UI_ResourceBar resourceBar = new UI_ResourceBar(125, 18);
        resourceBar.setBackground(Color.ORANGE);
        resourceBar.setAlignment(UIAlignment.NORTH_WEST);
        resourceBar.setPadding(new ContainerPadding(5, 0));
        this.add(resourceBar);

        UIButton options = new UI_Button_Options();
        options.setAlignment(UIAlignment.NORTH_EAST);
        this.add(options);

        UIContainer waveCounter = new UIContainer(100, 100);
        waveCounter.setLayoutManager(UILayout.VERTICAL);
        waveCounter.setAlignment(UIAlignment.NORTH);

        UITextBox waveTitle = new UITextBox("Wave");
        waveTitle.setAlignment(UIAlignment.NORTH);
        waveNumber = new UITextBox(gameModel.getCurrentWave() + " / " + gameModel.getMaxWaves());
        waveNumber.setAlignment(UIAlignment.NORTH);

        waveCounter.add(waveTitle);
        waveCounter.add(waveNumber);
        this.add(waveCounter);
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

    public void updateWaveCounter() {
        waveNumber.setText(gameModel.getCurrentWave() + " / " + gameModel.getMaxWaves());
    }

    public void startRound() {
        updateWaveCounter();
        playButton.toggleFastForward(gameModel.isFastForwarding());
    }

    public void toggleFastForward() {
        playButton.toggleFastForward(gameModel.isFastForwarding());
    }

    public void buildPhase() {
        playButton.setPlay();
    }
}

