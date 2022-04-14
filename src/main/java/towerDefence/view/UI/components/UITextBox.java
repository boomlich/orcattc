package towerDefence.view.UI.components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UITextBox extends UIComponentTemplate {

    private String text;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    private final Graphics2D graphics;
    private Color color = Color.BLACK;

    public UITextBox(String text) {
        this(text, 15, Color.DARK_GRAY);
    }

    public UITextBox(String text, int size) {
        this(text, size, Color.DARK_GRAY);
    }

    public UITextBox(String text, int size, Color color) {
        super(0, 0);
        this.text = text;
        graphics = generateGraphics();
        setSize(size);
        this.color = color;
    }



    private Graphics2D generateGraphics() {
        BufferedImage graphicsImage = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
        return (Graphics2D) graphicsImage.getGraphics();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    private int calculateStringWidth() {
        graphics.setFont(font);
        return graphics.getFontMetrics().stringWidth(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSize(int size) {
        font = new Font(font.getFontName(), font.getStyle(), size);
        setWidth(calculateStringWidth());
        setHeight(size);
    }

    @Override
    public void paint(Graphics2D g2D) {
        g2D.setColor(color);
        g2D.setFont(font);
        g2D.drawString(text, getX(), getY());
    }
}
