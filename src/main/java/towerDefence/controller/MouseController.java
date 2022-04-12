package towerDefence.controller;

import towerDefence.view.GameRender;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseMotionListener, MouseListener {

    private Interactable currentInteractable;

    public MouseController(GameRender gameRender, GameControllable gameModel) {
    }

    private void checkInteraction(Point mousePosition) {

        for (Interactable object: InteractionManager.getIntractable()) {
            if (inBounds(mousePosition, object)){
                if (currentInteractable == null) {
                    object.toggleHover();
                    currentInteractable = object;
                    break;
                } else if (currentInteractable != object) {
                    currentInteractable.toggleHover();
                    currentInteractable = object;
                    break;
                }
            }
            else {
                if (currentInteractable != null) {
                    currentInteractable.toggleHover();
                    currentInteractable = null;
                }
            }
        }
    }

    private boolean inBounds(Point mousePoint, Interactable object) {
        return mousePoint.x > object.getX() && mousePoint.x < object.getX() + object.getWidth() &&
                mousePoint.y > object.getY() && mousePoint.y < object.getY() + object.getHeight();
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
    }

}
