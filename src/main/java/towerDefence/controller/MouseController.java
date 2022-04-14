package towerDefence.controller;

import towerDefence.tower.towerTypes.Rifleman;
import towerDefence.view.GameRender;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class MouseController implements MouseMotionListener, MouseListener {

    private Interactable currentInteractable;
    private final GameRender gameRender;
    private final GameControllable gameModel;

    public MouseController(GameRender gameRender, GameControllable gameModel) {
        this.gameRender = gameRender;
        this.gameModel = gameModel;
    }

    private void checkInteraction(Point mousePosition) {

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

    private boolean inBounds(Point mousePoint, Interactable object) {
        double scaleX = gameRender.getScaleX();
        double scaleY = gameRender.getScaleY();

        return mousePoint.x > object.getX() * scaleX && mousePoint.x < (object.getX() + object.getWidth()) * scaleX &&
                mousePoint.y > object.getY() * scaleY && mousePoint.y < (object.getY() + object.getHeight()) * scaleY;
    }

    private void toggleClick(Point mousePoint) {
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            currentInteractable = null;
            checkInteraction(mousePoint);
        }
    }

    private void clickPressed() {
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            interactAction(currentInteractable.getInteractCode());
        }
    }

    private void clickReleased(Point mousePoint) {
        if (currentInteractable != null) {
            currentInteractable.toggleClick();
            currentInteractable = null;
            checkInteraction(mousePoint);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        checkInteraction(e.getPoint());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickPressed();
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

    private void interactAction(InteractCode interactCode) {

        if (interactCode == InteractCode.DEFAULT) {
            System.out.println("DEFAULT");
        }
        else if (interactCode == InteractCode.SPAWN_A) {
            gameModel.addTower(new Rifleman(new Point2D.Double(100, 100)));
        }
        else if (interactCode == InteractCode.SPAWN_B) {
            gameModel.addTower(new Rifleman(new Point2D.Double(150, 150)));
        }
        else if (interactCode == InteractCode.PLAY) {
            gameModel.startRound();
        }
    }

}
