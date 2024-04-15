package net.djahmo.bakeddelight.custom.entity.model;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.item.ChefSuitItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChefSuitModel extends GeoModel<ChefSuitItem> {
    @Override
    public ResourceLocation getModelResource(ChefSuitItem object) {
        return new ResourceLocation(BakedDelight.MODID, "geo/chef_suit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChefSuitItem object) {
        return new ResourceLocation(BakedDelight.MODID, "textures/geo/chef_suit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChefSuitItem animatable) {
        return new ResourceLocation(BakedDelight.MODID, "animations/armor_animation.json");
    }
}
