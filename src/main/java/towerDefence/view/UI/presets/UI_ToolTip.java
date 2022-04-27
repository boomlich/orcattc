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

        int frameEndWidth = 4;

        UIContainer frameLeft = new UIContainer(frameEndWidth, 35);
        frameLeft.setBackgroundImage("graphics/UI/ToolTip/UI_ToolTip_Frame_Left.png");

        UIContainer frameRight = new UIContainer(frameEndWidth, 35);
        frameRight.setBackgroundImage("graphics/UI/ToolTip/UI_ToolTip_Frame_Right.png");

        UIContainer frameMain = new UIContainer(125 - 2 * frameEndWidth, 35);
        frameMain.setBackgroundImage("graphics/UI/ToolTip/UI_ToolTip_Frame_Main.png");

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
        frameMain.setLayoutManager(UILayout.VERTICAL);

        for (String stringLine: lines) {
            frameMain.add(new UITextBox(stringLine, 8));
        }

        this.add(frameLeft);
        this.add(frameMain);
        this.add(frameRight);
    }
}
