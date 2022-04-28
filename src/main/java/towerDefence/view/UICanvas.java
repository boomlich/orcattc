package towerDefence.view;

import towerDefence.model.GameMode;
import towerDefence.tower.ITower;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class UICanvas implements ICanvas {

    private final GameRenderable gameModel;
    private final UIContainer UIContainer;
    private UI_HUD HUD;
    private UI_PauseMenu pauseMenu;
    private UI_MainMenu mainMenu;
    private UI_LevelSelect levelSelect;
    private UI_Win winScreen;
    private int width, height;


    public UICanvas(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        this.width = width;
        this.height = height;
        UIContainer = new UIContainer(width, height);
        gameModel.setGameUI(this);
    }

    @Override
    public void paint(Graphics2D g2D) {

        if (gameModel.getGameMode() == GameMode.MAIN_MENU) {
            g2D.setColor(new Color(38, 92, 66));
            g2D.fill(new Rectangle(0, 0, width, height));
        }

        // Draw active/highlighted tower
        if (gameModel.hasActiveTower()) {
            ITower tower = gameModel.getActiveTower();

            double radius = tower.getSearchRadius().getRadius();
            double collisionX = tower.getSearchRadius().getPosition().getX() - radius;
            double collisionY = tower.getSearchRadius().getPosition().getY() - radius;

            if (tower.hasValidPlacement()){
                // Draw
                g2D.setColor(new Color(100, 100, 100, 100));
                g2D.fill(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
                g2D.setColor(Color.WHITE);
                g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
            } else {
                // Draw
                g2D.setColor(new Color(255, 0, 0, 40));
                g2D.fill(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
                g2D.setColor(new Color(255, 0, 0, 150));
                g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
            }
            DrawGraphics.drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());

            // Center of Collision
            g2D.draw(new Rectangle2D.Double(tower.getSearchRadius().getPosition().getX(), tower.getSearchRadius().getPosition().getY(), 1, 1));
        }
        UIContainer.paint(g2D);
    }

    private <T extends UIContainer> T removeUIComponent(T component){
        if (component != null) {
            UIContainer.remove(component);
        }
        return null;
    }

    public void startNewLevel() {
        levelSelect = removeUIComponent(levelSelect);
        pauseMenu = removeUIComponent(pauseMenu);
        HUD = removeUIComponent(HUD);

        HUD = new UI_HUD(width, height, gameModel);
        UIContainer.add(HUD);
    }

    @Override
    public void update(double deltaSteps) {

        if (HUD != null) {
            if (gameModel.hasActiveTower()) {
                if (!gameModel.getActiveTower().activeSpawnMode()) {
                    HUD.addTowerMenu(gameModel.getActiveTower());
                }
            } else {
                HUD.removeTowerMenu();
            }
            HUD.updateMoney();
        }
    }

    @Override
    public void togglePauseGame() {

        if (pauseMenu == null) {
            HUD = removeUIComponent(HUD);
            pauseMenu = new UI_PauseMenu(width, height, gameModel);
            UIContainer.add(pauseMenu);
        } else {
            pauseMenu = removeUIComponent(pauseMenu);
//            UIContainer.remove(HUD);
            HUD = new UI_HUD(width, height, gameModel);
            UIContainer.add(HUD);
        }
    }

    @Override
    public void startRound() {
        if (HUD != null) {
            HUD.startRound();
        }
    }

    @Override
    public void addTowerMenu() {

    }

    @Override
    public void displayWin() {
        HUD = removeUIComponent(HUD);
        winScreen = new UI_Win(width, height);
        UIContainer.add(winScreen);
    }

    @Override
    public void displayGameOver() {
        HUD = removeUIComponent(HUD);
        UIContainer.add(new UI_GameOver(width, height));
    }

    public void startBuildPhase() {
        if (HUD != null) {
            HUD.buildPhase();
        }
    }

    @Override
    public void displayLevelSelect() {
        mainMenu = removeUIComponent(mainMenu);
        levelSelect = new UI_LevelSelect(width, height);
        UIContainer.add(levelSelect);
    }

    public void displayMainMenu() {
        pauseMenu = removeUIComponent(pauseMenu);
        mainMenu = new UI_MainMenu(width, height);
        UIContainer.add(mainMenu);
    }

    public void toggleFastForward() {
        if (HUD != null) {
            HUD.toggleFastForward();
        }
    }
}
