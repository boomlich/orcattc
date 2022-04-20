package towerDefence.view.UI.presets;

import towerDefence.tower.ITower;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;

import java.awt.*;

public class UI_TowerMenu extends UIContainer {

    public final ITower tower;
    private final UITextBox statKills;
    private final UITextBox statDamage;


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

        UIButton targetFirst = new UIButton(20, 16);
        targetFirst.setAlignment(UIAlignment.CENTER);
        targetFirst.setInteractCode(InteractCode.TARGET_FIRST);

        UIButton targetLast = new UIButton(20, 16);
        targetLast.setAlignment(UIAlignment.CENTER);
        targetLast.setInteractCode(InteractCode.TARGET_LAST);

        UIButton targetStrong = new UIButton(20, 16);
        targetStrong.setAlignment(UIAlignment.CENTER);
        targetStrong.setInteractCode(InteractCode.TARGET_STRONG);

        UIButton targetClose = new UIButton(20, 16);
        targetClose.setAlignment(UIAlignment.CENTER);
        targetClose.setInteractCode(InteractCode.TARGET_CLOSE);

        targetingOptions.add(targetFirst);
        targetingOptions.add(targetLast);
        targetingOptions.add(targetStrong);
        targetingOptions.add(targetClose);


        towerStatsAndOptions.add(statsContainer);
        towerStatsAndOptions.add(targetingOptions);

        UIButton upgrade = new UIButton("UPGRADE", 50, 50);
        upgrade.setAlignment(UIAlignment.CENTER);
        upgrade.setInteractCode(InteractCode.UPGRADE);

        UIButton sell = new UIButton("SELL", 25, 50);
        sell.setAlignment(UIAlignment.CENTER);
        sell.setInteractCode(InteractCode.SELL);



        this.add(portraitContainer);
        this.add(towerStatsAndOptions);
        this.add(upgrade);
        this.add(sell);
    }

    public void update() {
        statKills.setText("Kills: " + tower.getTotalKills());
        statDamage.setText("Damage: " + tower.getDamageDone());
    }
}
