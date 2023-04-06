package fr.djahmo.bakeddelight.custom.entity.model;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.custom.item.ChefSuitItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChefSuitModel extends AnimatedGeoModel<ChefSuitItem> {
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
