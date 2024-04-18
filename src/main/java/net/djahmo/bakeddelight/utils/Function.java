package net.djahmo.bakeddelight.utils;

import net.djahmo.bakeddelight.BakedDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class Function {

    public static ResourceLocation getResourceLocation(ItemLike item) {
        return new ResourceLocation(BakedDelight.MODID, getItemName(item));
    }

    public static ResourceLocation getResourceLocation(ItemLike item, String postfix) {
        return new ResourceLocation(BakedDelight.MODID, getItemName(item) + postfix);
    }

    public static String getItemName(ItemLike item) {
        return item.asItem().getDescriptionId().split("\\.")[2];
    }
}
