package towerDefence.model;

import towerDefence.view.GameRender;
import towerDefence.view.GameRenderable;
import towerDefence.view.ICanvas;

public class GameStateManager {


    private GameMode gameMode;

    private GameRenderable gameModel;
    private ICanvas gameUI;

    public GameStateManager(GameRenderable gameModel, ICanvas gameUI) {
        this.gameModel = gameModel;
        this.gameUI = gameUI;
    }

    public void setBuildPhase() {
        gameMode = GameMode.BUILD_PHASE;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setWinMode() {
        gameMode = GameMode.WIN;
        gameUI.displayWin();
    }

    public void setGameOver() {
        gameMode = GameMode.GAME_OVER;
        gameUI.displayGameOver();
    }
}
