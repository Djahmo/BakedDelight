package fr.djahmo.bakeddelight.registry;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.custom.recipe.BakingDishRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipe {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES;

    public static final RegistryObject<RecipeSerializer<BakingDishRecipe>> BAKING_DISH;
    public static void register(IEventBus eventBus) {RECIPES.register(eventBus);}

    static {
        RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BakedDelight.MODID);
        BAKING_DISH = RECIPES.register("baking_dish", () -> BakingDishRecipe.Serializer.INSTANCE);
    }
}
