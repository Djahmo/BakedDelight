package net.djahmo.bakeddelight.custom.datagen.serializer;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.recipe.BakingDishRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final RegistryObject<RecipeSerializer<?>> BAKING_DISH;

    public ModRecipeSerializers() {
    }

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BakedDelight.MODID);
        BAKING_DISH = RECIPE_SERIALIZERS.register("baking_dish", BakingDishRecipe.Serializer::new);
    }
}
