package towerDefence.view.UI;

import org.junit.jupiter.api.Test;
import UI.UI.components.UITextBox;

public class UITextTest {

    @Test
    void textSizeTest() {
        UITextBox text = new UITextBox("This is my String");

        System.out.println(text.getHeight());

    }
}
