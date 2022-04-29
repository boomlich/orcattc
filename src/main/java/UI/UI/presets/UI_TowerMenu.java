package UI.UI.presets;

import UI.UI.components.*;
import towerDefence.components.Targeting.TargetingMode;
import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import UI.Interaction.InteractCode;

import java.awt.*;
import java.awt.geom.Point2D;

public class UI_TowerMenu extends UIContainer {

    public final ITower tower;
    private final UITextBox statKills;
    private final UITextBox statDamage;
    private final UIButton upgradeButton;
    private final UITextBox upgradeCost;
    private final UIContainer[] rankDisplay = new UIContainer[3];
    private final UIButton[] targetingModes = new UIButton[4];
    private TargetingMode storedTargetingMode;


    public UI_TowerMenu(ITower tower) {
        super(225, 50);

        this.tower = tower;

        UIContainer portraitContainer = new UIContainer(50, 50);
        portraitContainer.setAlignment(UIAlignment.CENTER);
        portraitContainer.setLayoutManager(UILayout.VERTICAL);

        // Portrait and title
        UIContainer portrait = new UIContainer(32, 32);
        portrait.setBackgroundImage(tower.getPortraitPath());
        portrait.setAlignment(UIAlignment.CENTER);

        UITextBox portraitTitle = new UITextBox(tower.getName());
        portraitTitle.setAlignment(UIAlignment.CENTER);

        portraitContainer.add(portrait);
        portraitContainer.add(portraitTitle);


        // Tower stats
        UIContainer towerStatsAndOptions = new UIContainer(80, 50);
        towerStatsAndOptions.setAlignment(UIAlignment.CENTER);
        towerStatsAndOptions.setLayoutManager(UILayout.VERTICAL);

        UIContainer statsContainer = new UIContainer(80, 30);
        statsContainer.setAlignment(UIAlignment.NORTH);
        statsContainer.setLayoutManager(UILayout.VERTICAL);

        statKills = new UITextBox("Kills: " + tower.getTotalKills());
        statKills.setAlignment(UIAlignment.WEST);

        statDamage = new UITextBox("Damage: " + tower.getDamageDone());
        statDamage.setAlignment(UIAlignment.WEST);

        statsContainer.add(statKills);
        statsContainer.add(statDamage);

        int targetWidth = 20;
        int targetHeight = 12;

        // Targeting Options
        UIContainer targetingOptions = new UIContainer(targetWidth * 4, targetHeight);
        targetingOptions.setAlignment(UIAlignment.NORTH);

        int tooltipTargetOffset = -80;

        UIButton targetFirst = new UIButton(targetWidth, targetHeight);
        targetFirst.add(new UITextBox("First", 5));
        targetFirst.setInteractCode(InteractCode.TARGET_FIRST);
        targetFirst.setToolTip("Prioritize the target furthest on the path", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetLast = new UIButton(targetWidth, targetHeight);
        targetLast.setInteractCode(InteractCode.TARGET_LAST);
        targetLast.add(new UITextBox("Last", 5));
        targetLast.setToolTip("Prioritize the target last on the path", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetStrong = new UIButton(targetWidth, targetHeight);
        targetStrong.setInteractCode(InteractCode.TARGET_STRONG);
        targetStrong.add(new UITextBox("Strong", 5));
        targetStrong.setToolTip("Prioritize the enemy with the most health", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetClose = new UIButton(targetWidth, targetHeight);
        targetClose.setInteractCode(InteractCode.TARGET_CLOSE);
        targetClose.add(new UITextBox("Close", 5));
        targetClose.setToolTip("Prioritize the enemy closest in distance", new Point2D.Double(0, tooltipTargetOffset));

        targetingModes[0] = targetFirst;
        targetingModes[1] = targetLast;
        targetingModes[2] = targetStrong;
        targetingModes[3] = targetClose;

        for (UIButton targetModeButton: targetingModes) {
            setTargetingButtonActiveNotActive(targetModeButton, false);
            targetingOptions.add(targetModeButton);
        }

        towerStatsAndOptions.add(statsContainer);
        towerStatsAndOptions.add(targetingOptions);


        UIContainer upgradeContainer = new UIContainer(51, 38);
        upgradeContainer.setLayoutManager(UILayout.VERTICAL);
        upgradeContainer.setBackgroundImage("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Frame.png");

        upgradeButton = new UIButton("UPGRADE",49, 30);
        upgradeCost = new UITextBox(String.valueOf(tower.getCost(1)), 8);
        upgradeButton.setBorder(new ContainerBorder(3));
        upgradeCost.setAlignment(UIAlignment.SOUTH);
        upgradeButton.setButtonNormal("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Normal.png");
        upgradeButton.setButtonHover("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Hover.png");
        upgradeButton.setButtonClicked("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Normal.png");
        upgradeButton.setButtonDisabled("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Disabled.png");


        upgradeButton.setInteractCode(InteractCode.UPGRADE);
        upgradeButton.add(upgradeCost);

        UIContainer rankContainer = new UIContainer(49, 6);
        rankContainer.setBackground(new Color(115, 62, 57));
        rankContainer.setPadding(new ContainerPadding(2));

        // Add rank display
        for (int i = 0; i < 3; i++) {
            UIContainer rank = new UIContainer(13, 4);
            rank.setBackground(new Color(62, 39, 49));
            rankContainer.add(rank);
            rankDisplay[i] = rank;
        }

        upgradeContainer.add(upgradeButton);
        upgradeContainer.add(rankContainer);

        UIButton sell = new UIButton("SELL", 25, 38);
        sell.setButtonNormal("graphics/UI/TowerMenu/UI_TowerMenu_Sell_Normal.png");
        sell.setButtonHover("graphics/UI/TowerMenu/UI_TowerMenu_Sell_Hover.png");
        sell.setButtonClicked("graphics/UI/TowerMenu/UI_TowerMenu_Sell_Normal.png");

        sell.setInteractCode(InteractCode.SELL);

        // Add all elements to main container
        int frameEndWidth = 12;
        int framepadding = 5;

        UIContainer frameLeft = new UIContainer(12, 50);
        frameLeft.setBackgroundImage("graphics/UI/TowerMenu/UI_TowerMenu_Frame_Left.png");

        UIContainer frameRight = new UIContainer(12, 50);
        frameRight.setBackgroundImage("graphics/UI/TowerMenu/UI_TowerMenu_Frame_Right.png");

        UIContainer frameMain = new UIContainer(225 - 2 * frameEndWidth + framepadding * 4, 50);
        frameMain.setBackgroundImage("graphics/UI/TowerMenu/UI_TowerMenu_Frame_Main.png");

        frameMain.setPadding(new ContainerPadding(framepadding));
        frameMain.add(portraitContainer);
        frameMain.add(towerStatsAndOptions);
        frameMain.add(upgradeContainer);
        frameMain.add(sell);

        this.add(frameLeft);
        this.add(frameMain);
        this.add(frameRight);
    }

    private void updateRankDisplay() {
        int rank = tower.getRank();
        if (rank != 0) {
            for (int i = 0; i < tower.getRank(); i++) {
                rankDisplay[i].setBackgroundImage("graphics/UI/TowerMenu/UI_TowerMenu_Upgrade_Light.png");
            }
        }
        upgradeButton.setToolTip(tower.getUpgradeToolTip(), new Point2D.Double(0, -50));
    }

    private void updateMoney(GameRenderable gameModel) {

        int cost = tower.getCost(1);
        if (gameModel.hasSufficiantFunds(cost) && cost > 0) {
            upgradeButton.enableInteraction();
            upgradeCost.setText(String.valueOf(tower.getCost(1)));
        } else {
            upgradeButton.disableInteraction();
            upgradeButton.setText("MAX");
            upgradeCost.setText("");
        }
    }

    private void updateTargetingButtons() {

        if (storedTargetingMode == null) {
            updateOneTargetButton(tower.getTargetingMode(), true);
            storedTargetingMode = tower.getTargetingMode();
        } else {
            if (storedTargetingMode != tower.getTargetingMode()) {
                updateOneTargetButton(storedTargetingMode, false);
                updateOneTargetButton(tower.getTargetingMode(), true);
                storedTargetingMode = tower.getTargetingMode();
            }
        }
    }

    private void updateOneTargetButton(TargetingMode targetingMode, boolean activeButton) {
        switch (targetingMode) {
            case FIRST -> setTargetingButtonActiveNotActive(targetingModes[0], activeButton);
            case LAST -> setTargetingButtonActiveNotActive(targetingModes[1], activeButton);
            case STRONGEST -> setTargetingButtonActiveNotActive(targetingModes[2], activeButton);
            case CLOSEST -> setTargetingButtonActiveNotActive(targetingModes[3], activeButton);
        }
    }

    private void setTargetingButtonActiveNotActive(UIButton targetingButton, boolean enabled) {
        String mode = enabled ? "Enabled" : "Disabled";
        targetingButton.setButtonNormal("graphics/UI/TowerMenu/UI_TowerMenu_Targeting_" + mode +  ".png");
        targetingButton.setButtonHover("graphics/UI/TowerMenu/UI_TowerMenu_Targeting_" + mode +  ".png");
        targetingButton.setButtonClicked("graphics/UI/TowerMenu/UI_TowerMenu_Targeting_" + mode +  ".png");
    }

    public void update(GameRenderable gameModel) {
        statKills.setText("Kills: " + tower.getTotalKills());
        statDamage.setText("Damage: " + tower.getDamageDone());
        updateRankDisplay();
        updateMoney(gameModel);
        updateTargetingButtons();
    }
}
