package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.custom.datagen.builder.BakingDishRecipeBuilder;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.djahmo.bakeddelight.registry.ModItems;
import net.djahmo.bakeddelight.registry.ModTags;
import net.djahmo.bakeddelight.utils.Function;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        simpleFoodSmelting(pWriter, ModItems.GROUND_MEAT.get(), ModItems.COOKED_GROUND_MEAT.get(), 0.35F, 200);
        simpleFoodSmelting(pWriter, Items.MILK_BUCKET, ModItems.CHEESE.get(), 0.35F, 200);
        simpleFoodSmelting(pWriter, ModItems.BACON_BITS.get(), ModItems.COOKED_BACON_BITS.get(), 0.35F, 200);

        simpleFoodSmelting(pWriter, ModBlocks.UNCOOKED_LASAGNA_DISH.get(), ModBlocks.COOKED_LASAGNA_DISH.get(), 0.35F, 200);
        simpleFoodSmelting(pWriter, ModBlocks.UNCOOKED_GRATIN_DISH.get(), ModBlocks.COOKED_GRATIN_DISH.get(), 0.35F, 200);
        simpleFoodSmelting(pWriter, ModBlocks.UNCOOKED_MOUSSAKA_DISH.get(), ModBlocks.COOKED_MOUSSAKA_DISH.get(), 0.35F, 200);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BAKING_DISH.get())
                .define('#', Items.TERRACOTTA)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(Items.TERRACOTTA), has(Items.TERRACOTTA))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ROLLING_PIN.get())
                .define('/', Items.STICK)
                .define('#', Items.OAK_SLAB)
                .pattern("/#/")
                .unlockedBy(getHasName(Items.OAK_SLAB), has(Items.OAK_SLAB))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHEFS_HAT.get())
                .define('#', Items.WHITE_WOOL)
                .define('$', Items.LIGHT_GRAY_WOOL)
                .pattern("#$#")
                .pattern("# #")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHEFS_JACKET.get())
                .define('#', Items.WHITE_WOOL)
                .define('%', Items.BLACK_WOOL)
                .pattern("# #")
                .pattern("#%#")
                .pattern("###")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHEFS_PANTS.get())
                .define('#', Items.WHITE_WOOL)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHEFS_SHOES.get())
                .define('%', Items.BLACK_WOOL)
                .pattern("% %")
                .pattern("% %")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GROUND_MEAT.get())
                .requires(ModItems.BACON_BITS.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.MINCED_BEEF.get())
                .unlockedBy(getHasName(ModItems.BACON_BITS.get()), has(ModItems.BACON_BITS.get()))
                .save(pWriter);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.BACON.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES) , ModItems.BACON_BITS.get(), 2).build(pWriter, Function.getResourceLocation(ModItems.BACON_BITS.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.WHEAT_DOUGH.get()),Ingredient.of(ModTags.ROLLING) , ModItems.LASAGNA_PASTA.get(), 2).build(pWriter, Function.getResourceLocation(ModItems.LASAGNA_PASTA.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.SLICED_POTATO.get()).build(pWriter, Function.getResourceLocation(ModItems.SLICED_POTATO.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.EGGPLANT.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES) , ModItems.SLICED_EGGPLANT.get()).build(pWriter, Function.getResourceLocation(ModItems.SLICED_EGGPLANT.get()));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BACON_CREAM.get(), 1, 200, 1.0F)
                .addIngredient(Ingredient.of(ForgeTags.MILK))
                .addIngredient(Ingredient.of(ModItems.BACON_BITS.get()))
                .unlockedBy(getHasName(ModItems.BACON_BITS.get()), has(ModItems.BACON_BITS.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(pWriter, Function.getResourceLocation(ModItems.BACON_CREAM.get()));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BOLOGNESE.get(), 1, 200, 1.0F)
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.TOMATO_SAUCE.get()))
                .addIngredient(Ingredient.of(ModItems.GROUND_MEAT.get()))
                .unlockedBy(getHasName(ModItems.BACON_BITS.get()), has(ModItems.BACON_BITS.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(pWriter, Function.getResourceLocation(ModItems.BOLOGNESE.get(), "1"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BOLOGNESE.get(), 1, 200, 1.0F)
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()))
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()))
                .addIngredient(Ingredient.of(ModItems.GROUND_MEAT.get()))
                .unlockedBy(getHasName(ModItems.BACON_BITS.get()), has(ModItems.BACON_BITS.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(pWriter, Function.getResourceLocation(ModItems.BOLOGNESE.get(), "2"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BOLOGNESE.get(), 1, 200, 1.0F)
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()))
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()))
                .addIngredient(Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.MINCED_BEEF.get()))
                .addIngredient(Ingredient.of(ModItems.BACON_BITS.get()))
                .unlockedBy(getHasName(ModItems.BACON_BITS.get()), has(ModItems.BACON_BITS.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(pWriter, Function.getResourceLocation(ModItems.BOLOGNESE.get(), "3"));

        BakingDishRecipeBuilder.bakindDishRecipe(ModBlocks.UNCOOKED_LASAGNA_DISH.get(), ModItems.LASAGNA_PASTA.get(), ModItems.BOLOGNESE.get(), ModItems.LASAGNA_PASTA.get(), ModItems.BOLOGNESE.get(), ModItems.LASAGNA_PASTA.get(), ModItems.CHEESE.get())
                .unlockedBy(getHasName(ModBlocks.BAKING_DISH.get()), ModBlocks.BAKING_DISH.get())
                .build(pWriter, Function.getResourceLocation(ModBlocks.UNCOOKED_LASAGNA_DISH.get()));

        BakingDishRecipeBuilder.bakindDishRecipe(ModBlocks.UNCOOKED_GRATIN_DISH.get(), ModItems.SLICED_POTATO.get(), ModItems.SLICED_POTATO.get(), ModItems.SLICED_POTATO.get(), ModItems.SLICED_POTATO.get(), ModItems.BACON_CREAM.get(), ModItems.CHEESE.get())
                .unlockedBy(getHasName(ModBlocks.BAKING_DISH.get()), ModBlocks.BAKING_DISH.get())
                .build(pWriter, Function.getResourceLocation(ModBlocks.UNCOOKED_GRATIN_DISH.get()));

        BakingDishRecipeBuilder.bakindDishRecipe(ModBlocks.UNCOOKED_MOUSSAKA_DISH.get(), ModItems.SLICED_POTATO.get(), ModItems.SLICED_EGGPLANT.get(), ModItems.BOLOGNESE.get(), ModItems.SLICED_EGGPLANT.get(), vectorwing.farmersdelight.common.registry.ModItems.TOMATO_SAUCE.get(), ModItems.CHEESE.get())
                .unlockedBy(getHasName(ModBlocks.BAKING_DISH.get()), ModBlocks.BAKING_DISH.get())
                .build(pWriter, Function.getResourceLocation(ModBlocks.UNCOOKED_MOUSSAKA_DISH.get()));
    }

    private void simpleFoodSmelting(Consumer<FinishedRecipe> pWriter, ItemLike pIngredient, ItemLike pResult, float pExperience, int pCookingTime) {
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(pIngredient), RecipeCategory.FOOD, pResult, pExperience, pCookingTime).unlockedBy("has_item", has(pIngredient)).save(pWriter, Function.getResourceLocation(pResult));
    }

}
