package towerDefence.view.UI.presets;

import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;

import java.awt.*;
import java.awt.geom.Point2D;

public class UI_TowerMenu extends UIContainer {

    public final ITower tower;
    private final UITextBox statKills;
    private final UITextBox statDamage;
    private final UIButton upgradeButton;
    private final UIContainer[] rankDisplay = new UIContainer[3];





    public UI_TowerMenu(ITower tower) {
        super(225, 50);

        this.tower = tower;
        this.setBackground(Color.RED);
        this.setPadding(new ContainerPadding(5));

        UIContainer portraitContainer = new UIContainer(50, 50);
        portraitContainer.setAlignment(UIAlignment.CENTER);
        portraitContainer.setLayoutManager(UILayout.VERTICAL);
        portraitContainer.setBackground(Color.BLUE);

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
        towerStatsAndOptions.setBackground(Color.GREEN);

        UIContainer statsContainer = new UIContainer(80, 30);
        statsContainer.setAlignment(UIAlignment.NORTH);
        statsContainer.setLayoutManager(UILayout.VERTICAL);

        statKills = new UITextBox("Kills: " + tower.getTotalKills());
        statKills.setAlignment(UIAlignment.WEST);

        statDamage = new UITextBox("Damage: " + tower.getDamageDone());
        statDamage.setAlignment(UIAlignment.WEST);

        statsContainer.add(statKills);
        statsContainer.add(statDamage);

        // Targeting Options
        UIContainer targetingOptions = new UIContainer(80, 16);
        targetingOptions.setAlignment(UIAlignment.NORTH);

        int tooltipTargetOffset = -80;

        UIButton targetFirst = new UIButton(20, 16);
        targetFirst.setAlignment(UIAlignment.CENTER);
        targetFirst.setInteractCode(InteractCode.TARGET_FIRST);
        targetFirst.setToolTip("Prioritize the target furthest on the path", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetLast = new UIButton(20, 16);
        targetLast.setAlignment(UIAlignment.CENTER);
        targetLast.setInteractCode(InteractCode.TARGET_LAST);
        targetLast.setToolTip("Prioritize the target last on the path", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetStrong = new UIButton(20, 16);
        targetStrong.setAlignment(UIAlignment.CENTER);
        targetStrong.setInteractCode(InteractCode.TARGET_STRONG);
        targetStrong.setToolTip("Prioritize the enemy with the most health", new Point2D.Double(0, tooltipTargetOffset));

        UIButton targetClose = new UIButton(20, 16);
        targetClose.setAlignment(UIAlignment.CENTER);
        targetClose.setInteractCode(InteractCode.TARGET_CLOSE);
        targetClose.setToolTip("Prioritize the enemy closest in distance", new Point2D.Double(0, tooltipTargetOffset));

        targetingOptions.add(targetFirst);
        targetingOptions.add(targetLast);
        targetingOptions.add(targetStrong);
        targetingOptions.add(targetClose);


        towerStatsAndOptions.add(statsContainer);
        towerStatsAndOptions.add(targetingOptions);


        UIContainer upgradeContainer = new UIContainer(50, 50);
        upgradeContainer.setLayoutManager(UILayout.VERTICAL);
        upgradeContainer.setBackground(Color.BLACK);

        upgradeButton = new UIButton("UPGRADE",50, 35);
        upgradeButton.setInteractCode(InteractCode.UPGRADE);
        upgradeButton.setToolTip("Test", new Point2D.Double(0, -50));

        UIContainer rankContainer = new UIContainer(50, 15);
        rankContainer.setBackground(Color.RED);
        rankContainer.setPadding(new ContainerPadding(2));

        // Add rank display
        for (int i = 0; i < 3; i++) {
            UIContainer rank = new UIContainer(15, 11);
            rank.setBackground(Color.BLACK);
            rankContainer.add(rank);
            rankDisplay[i] = rank;
        }

        upgradeContainer.add(upgradeButton);
        upgradeContainer.add(rankContainer);

        UIButton sell = new UIButton("SELL", 25, 50);
        sell.setAlignment(UIAlignment.CENTER);
        sell.setInteractCode(InteractCode.SELL);

        // Add all elements to main container
        this.add(portraitContainer);
        this.add(towerStatsAndOptions);
        this.add(upgradeContainer);
        this.add(sell);
    }

    private void updateRankDisplay(ITower tower) {
        if (tower.getRank() != 0) {
            for (int i = 0; i < tower.getRank(); i++) {
                rankDisplay[i].setBackground(Color.GREEN);
            }
        }
    }

    private void updateMoney(GameRenderable gameModel) {
        if (gameModel.hasSufficiantFunds(tower.getCost())) {
//            upgradeButton.setColor(Color.WHITE);
            upgradeButton.enableInteraction();
        } else {
//            upgradeButton.setColor(Color.RED);
            upgradeButton.disableInteraction();
        }
    }

    public void update(GameRenderable gameModel) {
        statKills.setText("Kills: " + tower.getTotalKills());
        statDamage.setText("Damage: " + tower.getDamageDone());
        updateRankDisplay(tower);
        updateMoney(gameModel);
    }
}
