package net.djahmo.bakeddelight.registry;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.recipe.BakingDishRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES;

    public static final RegistryObject<RecipeSerializer<BakingDishRecipe>> BAKING_DISH;

    public static ArrayList<ArrayList<Item>> getAllRecipes(RecipeType recipeType) {
        ArrayList<ArrayList<Item>> recipes = new ArrayList<ArrayList<Item>>();
        AtomicInteger i = new AtomicInteger(-1);
        Objects.requireNonNull(Minecraft.getInstance().getConnection()).getRecipeManager().getRecipes().forEach((recipe) -> {
            if (recipe.getType() == recipeType) {
                i.getAndIncrement();
                recipes.add(new ArrayList<Item>());
                recipes.get(i.get()).add(recipe.getResultItem(null).getItem());
                recipe.getIngredients().forEach(ingredient -> recipes.get(i.get()).add(ingredient.getItems()[0].getItem()));
            }
        });
        return recipes;
    }
    public static void register(IEventBus eventBus) {RECIPES.register(eventBus);}

    static {
        RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BakedDelight.MODID);
        BAKING_DISH = RECIPES.register("baking_dish", () -> BakingDishRecipe.Serializer.INSTANCE);
    }
}
