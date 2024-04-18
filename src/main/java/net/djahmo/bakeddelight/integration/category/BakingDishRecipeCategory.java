package net.djahmo.bakeddelight.integration.category;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.recipe.BakingDishRecipe;
import net.djahmo.bakeddelight.registry.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class BakingDishRecipeCategory implements IRecipeCategory<BakingDishRecipe> {

    public static final ResourceLocation UID;
    public static final ResourceLocation TEXTURE;
    public static  RecipeType<BakingDishRecipe> BAKING_DISH_TYPE;

    private final IDrawable background;
    private final IDrawable icon;


    public BakingDishRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 181, 77);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BAKING_DISH.get()));
    }

    @Override
    public RecipeType<BakingDishRecipe> getRecipeType() {
        return BAKING_DISH_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Baking Dish");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BakingDishRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 14).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 47).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 47, 14).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 47, 47).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 14).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 113, 14).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 113, 47).addItemStack(new ItemStack(ModBlocks.BAKING_DISH.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 159, 47).addItemStack(recipe.getResultItem(null));
    }

    static {
        UID  = new ResourceLocation(BakedDelight.MODID, "baking_dish");
        TEXTURE = new ResourceLocation(BakedDelight.MODID, "textures/gui/jei/baking_dish.png");
        BAKING_DISH_TYPE = new RecipeType<>(BakingDishRecipeCategory.UID, BakingDishRecipe.class);
    }
}
