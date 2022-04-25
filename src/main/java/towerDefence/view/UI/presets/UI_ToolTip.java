package towerDefence.view.UI.presets;

import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIContainer;
import towerDefence.view.UI.components.UILayout;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UI_ToolTip extends UIContainer {

    String[] toolTipLines;

    public UI_ToolTip(String text) {
        super(125, 35);
        this.setBackground(Color.BLACK);

        String[] splitString = text.split(" ");
        int totalLength = 0;

        List<String> lines = new ArrayList<>(List.of(""));
        int maxLineLength = 30;

        String lineText = "";
        for (int i = 0; i < splitString.length; i++) {
            String word = splitString[i];

            lineText += word + " ";

            if (i < splitString.length -1) {
                if (lineText.length() + splitString[i + 1].length() > maxLineLength) {
                    lines.add(lineText);
                    lineText = new String("");
                }
            } else {
                lines.add(lineText);
            }
        }
        this.setLayoutManager(UILayout.VERTICAL);

        for (String stringLine: lines) {
            this.add(new UITextBox(stringLine, 8));
        }

    }
}
