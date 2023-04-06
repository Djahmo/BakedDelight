package fr.djahmo.bakeddelight.custom.entity.model;

import fr.djahmo.bakeddelight.custom.item.ChefSuitItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ChefSuitRenderer extends GeoArmorRenderer<ChefSuitItem> {
    public ChefSuitRenderer() {
        super(new ChefSuitModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
