package towerDefence.controller;

import towerDefence.components.Targeting.TargetingMode;
import towerDefence.level.Level;
import towerDefence.tower.ITower;
import towerDefence.tower.towerTypes.Archer;
import towerDefence.tower.towerTypes.Cannon;
import towerDefence.tower.towerTypes.Rifleman;
import towerDefence.tower.towerTypes.Wizard;
import towerDefence.view.GameRender;
import UI.Interaction.InteractCode;
import UI.Interaction.Interactable;
import UI.Interaction.InteractionManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class MouseController implements MouseMotionListener, MouseListener {

    private Interactable currentInteractable;
    private final GameRender gameRender;
    private final GameControllable gameModel;
    private Point2D mouseCoordinate;

    public MouseController(GameRender gameRender, GameControllable gameModel) {
        this.gameRender = gameRender;
        this.gameModel = gameModel;
    }

    private void checkInteraction(Point2D mousePosition) {

        for (Interactable object: InteractionManager.getIntractable()) {


            if (inBounds(mousePosition, object)){

                // Activate new object
                if (currentInteractable == null) {
                    object.toggleHover();
                    currentInteractable = object;
                }

                // Check if entered new object
                else if (currentInteractable != object) {
                    currentInteractable.toggleHover();
                    currentInteractable = object;
                }
                break;
            }
            // When not hovering anymore
            else {
                if (currentInteractable != null) {
                    currentInteractable.toggleHover();
                    currentInteractable = null;
                }
            }
        }
    }

    private boolean inBounds(Point2D mousePoint, Interactable object) {
        return mousePoint.getX() > object.getX() && mousePoint.getX() < (object.getX() + object.getWidth()) &&
                mousePoint.getY() > object.getY() && mousePoint.getY() < (object.getY() + object.getHeight());
    }

    private void toggleClick(Point mousePoint) {
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            currentInteractable = null;
            checkInteraction(mousePoint);
        }
    }

    private void clickPressed(Point2D mousePoint) {
        checkInteraction(mousePoint);
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            interactAction(currentInteractable.getInteractCode(), mousePoint);
        } else {
            interactEmptySpace();
        }
    }

    private void clickReleased(Point mousePoint) {
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            currentInteractable = null;
            checkInteraction(mousePoint);
        }
    }

    public Point2D getMouseCoordinate() {
        return mouseCoordinate;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point2D scaledMousePos = scaleMousePosition(e.getPoint());
        mouseCoordinate = scaledMousePos;

        if (gameModel.isActiveTowerInSpawnMode()) {
            gameModel.updateMousePosition(scaledMousePos);
        } else {
            checkInteraction(scaledMousePos);
        }
    }

    private Point2D scaleMousePosition(Point2D mousePosition) {
        double scaleX = gameRender.getScaleX();
        double scaleY = gameRender.getScaleY();

        return new Point2D.Double(mousePosition.getX() / scaleX, mousePosition.getY() / scaleY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D scaledMousePos = scaleMousePosition(e.getPoint());
        clickPressed(scaledMousePos);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clickReleased(e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void interactAction(InteractCode interactCode, Point2D mousePosition) {

        double mouseX = mousePosition.getX();
        double mouseY = mousePosition.getY();


        if (interactCode == InteractCode.DEFAULT) {
        }
        else if (interactCode == InteractCode.SPAWN_A) {
            gameModel.addTower(new Archer(new Point2D.Double(mouseX, mouseY)));
        }
        else if (interactCode == InteractCode.SPAWN_B) {
            gameModel.addTower(new Rifleman(new Point2D.Double(mouseX, mouseY)));
        }
        else if (interactCode == InteractCode.SPAWN_C) {
            gameModel.addTower(new Cannon(new Point2D.Double(mouseX, mouseY), this, gameModel));
        }
        else if (interactCode == InteractCode.SPAWN_D) {
            gameModel.addTower(new Wizard(new Point2D.Double(mouseX, mouseY)));
        }
        else if (interactCode == InteractCode.PLAY) {
            gameModel.startInvasionRound();
        }
        else if (interactCode == InteractCode.TARGET_Tower) {
            gameModel.selectTower((ITower) currentInteractable);
            gameRender.getGameUI().addTowerMenu();
        }
        else if (interactCode == InteractCode.TARGET_FIRST) {
            gameModel.setTowerTargetingMode(TargetingMode.FIRST);
        } else if (interactCode == InteractCode.TARGET_LAST) {
            gameModel.setTowerTargetingMode(TargetingMode.LAST);
        } else if (interactCode == InteractCode.TARGET_STRONG) {
            gameModel.setTowerTargetingMode(TargetingMode.STRONGEST);
        } else if (interactCode == InteractCode.TARGET_CLOSE) {
            gameModel.setTowerTargetingMode(TargetingMode.CLOSEST);
        } else if (interactCode == InteractCode.UPGRADE) {
            gameModel.upgradeTower();
        } else if (interactCode == InteractCode.SELL) {
            gameModel.sellTower();
        }
        else if (interactCode == InteractCode.PAUSE) {
            gameModel.togglePauseGame();
        } else if (interactCode == InteractCode.FAST_FORWARD) {
            gameModel.toggleFastForward();
        } else if (interactCode == InteractCode.RESTART) {
            gameModel.restartLevel();
        } else if (interactCode == InteractCode.PLAY_GAME) {
            gameRender.getGameUI().displayLevelSelect();
        } else if (interactCode == InteractCode.LEVEL_A) {
            gameModel.loadLevel(Level.A);
        } else if (interactCode == InteractCode.LEVEL_B) {
            gameModel.loadLevel(Level.B);
        } else if (interactCode == InteractCode.MAIN_MENU) {
            gameModel.loadMainMenu();
        } else if (interactCode == InteractCode.QUIT) {
            System.exit(0);
        }
    }

    private void interactEmptySpace() {
        if (gameModel.isActiveTowerInSpawnMode()) {
            gameModel.placeTower();
        } else {
            gameModel.selectTower(null);
        }
    }
}
