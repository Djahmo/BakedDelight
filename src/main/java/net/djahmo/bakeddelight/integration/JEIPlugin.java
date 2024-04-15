package net.djahmo.bakeddelight.integration;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.recipe.BakingDishRecipe;
import net.djahmo.bakeddelight.integration.category.BakingDishRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static RecipeType<BakingDishRecipe> BAKING_DISH_TYPE;
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(BakedDelight.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BakingDishRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<BakingDishRecipe> bakingdishrecipe = rm.getAllRecipesFor(BakingDishRecipe.Type.INSTANCE);
        registration.addRecipes(BAKING_DISH_TYPE, bakingdishrecipe);
    }

    static {
        BAKING_DISH_TYPE = new RecipeType<>(BakingDishRecipeCategory.UID, BakingDishRecipe.class);
    }
}
