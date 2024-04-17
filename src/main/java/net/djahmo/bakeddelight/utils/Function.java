package net.djahmo.bakeddelight.utils;

import net.djahmo.bakeddelight.BakedDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class Function {
    public static ResourceLocation getResourceLocation(ItemLike item) {
        return new ResourceLocation(BakedDelight.MODID, item.asItem().getDescriptionId().split("\\.")[2]);
    }
    public static ResourceLocation getResourceLocation(ItemLike item, String postfix) {
        return new ResourceLocation(BakedDelight.MODID, item.asItem().getDescriptionId().split("\\.")[2] + postfix);
    }
}
