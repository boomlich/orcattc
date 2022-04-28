package UI.UI.components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UITextBox extends UIComponentTemplate {

    private String text;
    private Font font = new Font("Calibri", Font.BOLD, 10);
    private final Graphics2D graphics;
    private Color color;

    public UITextBox(String text) {
        this(text, 10, Color.WHITE);
    }

    public UITextBox(String text, int size) {
        this(text, size, Color.WHITE);
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
        updateDimensions();
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
        updateDimensions();
    }

    public void updateDimensions() {
        setWidth(calculateStringWidth());
        setHeight(font.getSize());
    }

    @Override
    public void paint(Graphics2D g2D) {
        g2D.setColor(color);
        g2D.setFont(font);
        g2D.drawString(text, getX(), getY());
    }

    @Override
    public void setY(int y) {
        super.setY(y + getHeight() - getHeight() / 6);
    }

    @Override
    public void offsetPosition(int offsetX, int offsetY) {
        offsetY -= getHeight() + getHeight() / 2;
        super.offsetPosition(offsetX, offsetY);
    }
}
