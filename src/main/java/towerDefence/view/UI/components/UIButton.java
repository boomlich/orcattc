package towerDefence.view.UI.components;


import towerDefence.view.ImageLoader;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UIButton extends UIContainer implements Interactable {

    private BufferedImage buttonNormal;
    private BufferedImage buttonHover;
    private BufferedImage buttonClicked;
    private BufferedImage buttonDisabled;

    // Default colors
    private final Color normalColor = new Color(190, 190, 190);
    private final Color hoverColor = new Color(50, 100, 220);
    private final Color clickedColor = new Color(150, 150, 150);
    private final Color disabledColor = new Color(100, 100, 100);

    UIButtonState buttonState = UIButtonState.NORMAL;
    InteractCode interactCode = InteractCode.DEFAULT;

    public UIButton(int width, int height) {
        super(width, height);
        InteractionManager.addIntractable(this);
    }

    public UIButton(String label, int width, int height) {
        this(width, height);
        add(new UITextBox(label));
    }

    public void setInteractCode(InteractCode interactCode) {
        this.interactCode = interactCode;
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setButtonNormal(String path) {
        buttonNormal = loadImage(path);
    }

    public void setButtonHover(String path) {
        buttonHover = loadImage(path);
    }

    public void setButtonClicked(String path) {
        buttonClicked = loadImage(path);
    }

    public void setButtonDisabled(String path) {
        buttonDisabled = loadImage(path);
    }

    @Override
    public void paint(Graphics2D g2D) {

        boolean emptyImage = true;

        switch (buttonState) {
            case NORMAL -> {
                g2D.setColor(normalColor);
                emptyImage = isEmptyImage(buttonNormal);
            }
            case HOVER -> {
                g2D.setColor(hoverColor);
                emptyImage = isEmptyImage(buttonHover);
            }
            case CLICKED -> {
                g2D.setColor(clickedColor);
                emptyImage = isEmptyImage(buttonClicked);
            }
            case DISABLED -> {
                g2D.setColor(disabledColor);
                emptyImage = isEmptyImage(buttonDisabled);
            }
        }
        if (emptyImage) {
            g2D.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));
        }

        super.paint(g2D);
    }

    private boolean isEmptyImage(BufferedImage image) {
        if (image != null) {
            setBackgroundImage(image);
            return false;
        }
        return true;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public void toggleHover() {
        if (buttonState == UIButtonState.NORMAL) {
            buttonState = UIButtonState.HOVER;
        } else if (buttonState == UIButtonState.HOVER) {
            buttonState = UIButtonState.NORMAL;
        }
    }

    @Override
    public void toggleClick() {
        if (buttonState != UIButtonState.CLICKED) {
            buttonState = UIButtonState.CLICKED;
        } else {
            buttonState = UIButtonState.NORMAL;
        }
    }

    @Override
    public InteractCode getInteractCode() {
        return interactCode;
    }
}
