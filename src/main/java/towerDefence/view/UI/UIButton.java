package towerDefence.view.UI;


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

    public UIButton() {
        super(50, 50);
        InteractionManager.addIntractable(this);
    }

    public UIButton(String label) {
        this();
        add(new UITextBox(label));

        UIContainer test = new UIContainer(25, 10);
        test.setAlignment(UIAlignment.EAST);
        test.setBackground(Color.ORANGE);
        add(test);
    }

    public void setInteractCode(InteractCode interactCode) {
        this.interactCode = interactCode;
    }

    private BufferedImage load(String path) {
        try {
            return ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setButtonNormal(String path) {
        buttonNormal = load(path);
    }

    public void setButtonHover(String path) {
        buttonHover = load(path);
    }

    public void setButtonClicked(String path) {
        buttonClicked = load(path);
    }

    public void setButtonDisabled(String path) {
        buttonDisabled = load(path);
    }

    @Override
    public void paint(Graphics2D g2D) {

        switch (buttonState) {
            case NORMAL -> {
                g2D.setColor(normalColor);
                setBackgroundImage(buttonNormal);
            }
            case HOVER -> {
                g2D.setColor(hoverColor);
                setBackgroundImage(buttonHover);
            }
            case CLICKED -> {
                g2D.setColor(clickedColor);
                setBackgroundImage(buttonClicked);
            }
            case DISABLED -> {
                g2D.setColor(disabledColor);
                setBackgroundImage(buttonDisabled);
            }
        }
        g2D.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));

        super.paint(g2D);
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
