package UI.UI.presets;


import UI.UI.components.ContainerPadding;
import UI.UI.components.UIButton;
import UI.UI.components.UIContainer;
import UI.UI.components.UILayout;
import UI.UI.presets.buttons.UI_Button_MenuButton;
import UI.Interaction.InteractCode;


import java.util.ArrayList;
import java.util.List;

public class UI_MainMenu extends UIContainer {

    public UI_MainMenu(int width, int height) {
        super(width, height);
        this.setLayoutManager(UILayout.VERTICAL);
        this.setPadding(new ContainerPadding(10));

        List<UIButton> buttons = new ArrayList<>();
        buttons.add(new UI_Button_MenuButton("Play", 15, InteractCode.PLAY_GAME));
        buttons.add(new UI_Button_MenuButton("QUIT GAME", 15, InteractCode.QUIT));

        int buttonPadding = 10;
        int frameEndHeight = 13;
        int contentWidth = 158;
        int contentFrameHeight = buttons.get(0).getHeight() * buttons.size() + buttonPadding * (buttons.size() - 1);

        UIContainer frame = new UIContainer(contentWidth, contentFrameHeight + frameEndHeight * 2);
        frame.setLayoutManager(UILayout.VERTICAL);

        UIContainer title = new UIContainer(220, 50);
        title.setBackgroundImage("graphics/UI/MainMenu/UI_MainMenu_Title.png");

        UIContainer frameTop = new UIContainer(contentWidth, frameEndHeight);
        frameTop.setBackgroundImage("graphics/UI/MainMenu/UI_MainMenu_Frame_Top.png");

        UIContainer frameBottom = new UIContainer(contentWidth, frameEndHeight);
        frameBottom.setBackgroundImage("graphics/UI/MainMenu/UI_MainMenu_Frame_Bottom.png");

        UIContainer frameMain = new UIContainer(contentWidth, contentFrameHeight);
        frameMain.setBackgroundImage("graphics/UI/MainMenu/UI_MainMenu_Frame_Main.png");
        frameMain.setLayoutManager(UILayout.VERTICAL);
        frameMain.setPadding(new ContainerPadding(buttonPadding));

        for (UIButton button: buttons) {
            frameMain.add(button);
        }

        frame.add(frameTop);
        frame.add(frameMain);
        frame.add(frameBottom);

        this.add(title);
        this.add(frame);
    }
}
