package net.djahmo.bakeddelight.custom.entity.model;

import net.djahmo.bakeddelight.custom.item.ChefSuitItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ChefSuitRenderer extends GeoArmorRenderer<ChefSuitItem> {
    public ChefSuitRenderer() {
        super(new ChefSuitModel());
    }
}
